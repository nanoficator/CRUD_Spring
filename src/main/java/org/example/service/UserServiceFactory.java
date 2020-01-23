package org.example.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserServiceFactory {

    public static UserService getUserService() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = loader.getResourceAsStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            String isSpring = properties.getProperty("ServiceType");

            if (isSpring.equalsIgnoreCase("spring")) {
                return new UserServiceSpring();
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}