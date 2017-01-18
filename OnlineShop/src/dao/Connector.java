package dao;

import org.apache.tomcat.jdbc.pool.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by laonen on 05.01.2017.
 */
class Connector {
    private static final String URL = "jdbc:mysql://localhost/onlineshop?autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static DataSource source;
    private static boolean testEnvironment;
    private static boolean isInit;

    static void setTestEnvironment(boolean option) {
        testEnvironment = option;
    }

    private static void init() {
        if (!testEnvironment) {
            PoolProperties poolProperties = new PoolProperties();
            poolProperties.setUrl(URL);
            poolProperties.setUsername(USER);
            poolProperties.setPassword(PASSWORD);
            poolProperties.setDriverClassName(DRIVER);
            source = new DataSource(poolProperties);
        } else {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    static Connection connect() {
        if (!isInit) {
            init();
        }
        if (testEnvironment) {
            try {
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            try {
                return source.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    static void disConnect(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
