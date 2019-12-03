package com.uloo.parsing;

import java.io.InputStream;

public class Resources {
    public static InputStream getResourceAsStreamer(String xmlPath){
        return Resources.class.getClassLoader().getResourceAsStream(xmlPath);
    }
}
