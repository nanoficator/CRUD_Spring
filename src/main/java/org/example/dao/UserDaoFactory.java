package org.example.dao;

import org.example.util.DBHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory {

    public static UserDao getDao() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = loader.getResourceAsStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            String daoType = properties.getProperty("DAOType");

            if (daoType.equalsIgnoreCase("hibernate")) {
                return new UserDaoHibernate(DBHelper.getInstance().getConfiguration());
            }

            return null;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}