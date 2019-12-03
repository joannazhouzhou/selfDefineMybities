package com.uloo.session;

import com.uloo.configruation.Configuration;

public class SqlSessionFactory {
    private Configuration cfg ;
    public SqlSessionFactory(Configuration cfg) {
        this.cfg = cfg;
    }
    //获取 SqlSession
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
