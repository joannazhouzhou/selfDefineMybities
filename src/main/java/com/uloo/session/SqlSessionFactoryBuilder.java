package com.uloo.session;



import com.uloo.builder.XMLconfigBuilder;
import com.uloo.configruation.Configuration;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream is) throws Exception {

        //1.XMLConfigBuilder 解析配置文件  构造 Configuration
        Configuration cfg = XMLconfigBuilder.buildConfiguration(is);

        return new SqlSessionFactory(cfg);
    }
}
