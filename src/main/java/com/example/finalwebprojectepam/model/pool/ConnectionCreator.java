package com.example.finalwebprojectepam.model.pool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreator {
    private static final Properties properties = new Properties();
    private static final String CONFIG_FILE_NAME = "db.properties";
    private static final String DRIVER_PROPERTY_NAME = "db.driver";
    private static final String URL_PROPERTY_NAME = "db.url";
    private static final String PASSWORD_PROPERTY_NAME = "db.password";
    private static final String LOGIN_PROPERTY_NAME = "db.login";
    private static final String DATABASE_DRIVER;
    private static final String DATABASE_URL;
    private static final String DATABASE_PASSWORD;
    private static final String DATABASE_LOGIN;

    static {
        try (InputStream input = ConnectionCreator.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME)) {
            properties.load(input);
            DATABASE_DRIVER = properties.getProperty(DRIVER_PROPERTY_NAME);
            DATABASE_URL = properties.getProperty(URL_PROPERTY_NAME);
            DATABASE_LOGIN = properties.getProperty(LOGIN_PROPERTY_NAME);
            DATABASE_PASSWORD = properties.getProperty(PASSWORD_PROPERTY_NAME);
            Class.forName(DATABASE_DRIVER);
        } catch (ClassNotFoundException | NumberFormatException | IOException e) {
            throw new ExceptionInInitializerError(e.getMessage());
        }
    }

    private ConnectionCreator() {
    }

    public static Connection getConnection() {
        try{
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, DATABASE_PASSWORD);
            return connection;
        } catch(SQLException e) {
            throw new RuntimeException();
        }
    }

    public static void deregisterDriver() {
        try {
            Driver driver = DriverManager.getDriver(DATABASE_URL);
            DriverManager.deregisterDriver(driver);
        } catch(SQLException e) {
            throw new RuntimeException();
        }
    }
}
