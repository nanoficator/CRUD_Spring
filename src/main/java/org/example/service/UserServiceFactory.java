package org.example.service;

import org.example.exception.DBException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserServiceFactory {

    public static UserService getUserService() throws DBException {
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
            throw new DBException(e);

        }
    }
}