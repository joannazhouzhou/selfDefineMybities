package com.uloo.mapping;

public class Mapper {

    private String querySql;//sql 语句
    private String resultType;//全限定类名

    public String getQuerySql() {
        return querySql;
    }
    public void setQuerySql(String querySql) {
        this.querySql = querySql;
    }
    public String getResultType() {
        return resultType;
    }
    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

}
