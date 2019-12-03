package com.uloo.connection;

import com.uloo.configruation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataSourceUtils {
    public static Connection getConnection(Configuration cfg){
        try {
            Class.forName(cfg.getDriver());
            Connection connection = DriverManager.getConnection(cfg.getUrl(), cfg.getUsername(), cfg.getPassword());
            return connection;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }
}
