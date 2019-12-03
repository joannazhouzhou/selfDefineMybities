package com.uloo.executor;

import com.uloo.connection.DataSourceUtils;
import com.uloo.mapping.Mapper;
import com.uloo.configruation.Configuration;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class Executor {
    private Connection connection;
    public Executor(Configuration configuration) {
        this.connection= DataSourceUtils.getConnection(configuration);
    }

    public <E> List<E> executeQuery(Mapper mapper){
        PreparedStatement pstm=null;
        ResultSet rs=null;
        try {
            String querySql = mapper.getQuerySql();
            String resultType = mapper.getResultType();
            Class entityClass = Class.forName(resultType);

            pstm = connection.prepareStatement(querySql);
            rs=pstm.executeQuery();

            ArrayList<E> list = new ArrayList<E>();

            while (rs.next()){
                E obj=(E) entityClass.newInstance();

                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                //列名序号从1开始
                for (int i = 1; i < columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = rs.getObject(columnName);

                    String[] split = columnName.split("_");
                    String name=split[0];
                    for (int i1 = 1; i1 < split.length; i1++) {
                        String s = split[i1].toUpperCase().toCharArray()[0] + split[i1].substring(1, split[i1].length());
                        name+=s;
                    }
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(name, entityClass);
                    Method writeMethod = propertyDescriptor.getWriteMethod();
                    writeMethod.invoke(obj,columnValue);
                }
                list.add(obj);
            }
            return list;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            //关闭资源
            release(pstm,rs);
        }

    }
    public <E> List<E> executeQuery(String querySql,String resultType){
        PreparedStatement pstm=null;
        ResultSet rs=null;
        try {
            Class entityClass = Class.forName(resultType);
            pstm = connection.prepareStatement(querySql);
            rs=pstm.executeQuery();
            ArrayList<E> list = new ArrayList<E>();
            while (rs.next()){
                E obj=(E) entityClass.newInstance();
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                //列名序号从1开始
                for (int i = 1; i < columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = rs.getObject(columnName);
                    String[] split = columnName.split("_");
                    String name=split[0];
                    for (int i1 = 1; i1 < split.length; i1++) {
                        String s = split[i1].toUpperCase().toCharArray()[0] + split[i1].substring(1, split[i1].length());
                        name+=s;
                    }
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(name, entityClass);
                    Method writeMethod = propertyDescriptor.getWriteMethod();
                    writeMethod.invoke(obj,columnValue);
                }
                list.add(obj);
            }
            return list;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            //关闭资源
            release(pstm,rs);
        }

    }
    private void release(PreparedStatement pstm,ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if(pstm != null){
            try {
                pstm.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}
