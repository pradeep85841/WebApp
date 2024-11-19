package com.swag;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;


public class Utility {

    private static Properties properties = new Properties();

    static {
        try (InputStream inputStream = Utility.class.getClassLoader().getResourceAsStream("file.properties")) {
            if (inputStream == null) {
                throw new IOException("property file not found");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }


}
