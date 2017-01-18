package dao;

import org.apache.tomcat.jdbc.pool.*;

import java.sql.Connection;
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

    static void pool() {
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setUrl(URL);
        poolProperties.setUsername(USER);
        poolProperties.setPassword(PASSWORD);
        poolProperties.setDriverClassName(DRIVER);
        source = new DataSource(poolProperties);
    }

    static Connection connect() {
        try {
            return source.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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
