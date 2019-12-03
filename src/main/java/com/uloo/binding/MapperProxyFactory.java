package com.uloo.binding;

import com.uloo.annotations.Select;
import com.uloo.session.DefaultSqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class MapperProxyFactory implements InvocationHandler {

    private DefaultSqlSession sqlSession;

    public MapperProxyFactory(DefaultSqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    /**
     * proxy:代理对象的引用
     * 获取代理对象这个实现类的接口类型
     * 获取当前接口的全限定类名
     * method：当前执行的方法对象
     * 当前执行的方法名称
     *
     * mapperId = 获取当前接口的全限定类名 + “.” + 当前执行的方法名称
     * args：当前参数
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        //判断当前方法上是否存在 select 注解
        boolean present = method.isAnnotationPresent(Select.class);
        if(present) {//存在 select 注解
            //1.获取 sql 语句
            Select select = method.getAnnotation(Select.class);//获取当前方法上的 select 注解
            String sql = select.value();
            String returnType = method.getGenericReturnType().toString();
            if(returnType.startsWith(List.class.getName())){
                ParameterizedType pt =
                        (ParameterizedType)method.getGenericReturnType();
                Class clz = (Class)pt.getActualTypeArguments()[0];
                returnType = clz.getName();
            }
            //2.得到返回值类型
            return sqlSession.getExecutor().executeQuery(sql, returnType);
        }else {//没有注解
            //invoke 方法：就是在查询数据库
            //获取当前接口的全限定类名
            //getGenericInterfaces：获取实体类的所有接口
            String className = method.getDeclaringClass().getName();
            //获取当前执行的方法名称
            String methodName = method.getName();
            String mapperId = className + "." + methodName;
            return sqlSession.selectList(mapperId);
        }
    }
}
