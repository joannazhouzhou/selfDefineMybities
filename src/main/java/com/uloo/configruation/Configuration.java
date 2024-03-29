package com.uloo.configruation;

import com.uloo.mapping.Mapper;

import java.util.Map;

public class Configuration {

    private String username; //用户名
    private String password;//密码
    private String url;//地址
    private String driver;//驱动

    //map 集合 Map<唯一标识，Mapper> 用于保存映射文件中的 sql 标识及 sql 语句
    private Map<String, Mapper> mappers;


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getDriver() {
        return driver;
    }
    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Map<String, Mapper> getMappers() {
        return mappers;
    }
    public void setMappers(Map<String, Mapper> mappers) {
        this.mappers = mappers;
    }
}
