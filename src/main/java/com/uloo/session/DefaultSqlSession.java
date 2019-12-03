package com.uloo.session;


import com.uloo.executor.Executor;
import com.uloo.mapping.Mapper;
import com.uloo.binding.MapperProxyFactory;
import com.uloo.configruation.Configuration;


import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.List;


public class DefaultSqlSession implements SqlSession {
    private Configuration cfg;//封装 mybatis 配置文件及映射文件
    private Executor executor;//SQL 语句执行器
    private Connection connection;
    public DefaultSqlSession(Configuration cfg) {
        this.cfg = cfg;
        this.executor = new Executor(this.cfg);
    }
    /**
     * 生成 mapper 的代理对象
     */
    public <E> E getMapper(Class clazz) throws Exception {
         //创建 invocationHandler
        MapperProxyFactory proxy = new MapperProxyFactory(this);
        //创建动态代理对象
        return (E) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]
                {clazz}, proxy);
    }

    public void close() {
        if(connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 查询列表
     * 定位到 sql 语句
     */
    public <E> List<E> selectList(String mapperId) throws Exception {
        //通过 id 获取 Mapper 对象
        Mapper mapper = cfg.getMappers().get(mapperId);
        //调用 SQL 语句的 Executor 执行器来执行 sql 语句
        return executor.executeQuery(mapper);
    }

    public Executor getExecutor() {
        return executor;
    }
}
