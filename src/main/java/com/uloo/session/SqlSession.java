package com.uloo.session;

import java.util.List;

public interface  SqlSession {
    public <E> List<E> selectList(String mapperId) throws Exception;
    /**
     * 创建动态代理对象
     * clazz ： 接口的字节码
     */
    public <E> E getMapper(Class clazz) throws Exception;

    public void close();
}
