package dao;

import org.apache.tomcat.jdbc.pool.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by laonen on 05.01.2017.
 */
public class Connector {
    private static final Logger log = Logger.getLogger(Connector.class.getName());
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
            poolProperties.setJmxEnabled(true);
            poolProperties.setTestWhileIdle(false);
            poolProperties.setTestOnBorrow(true);
            poolProperties.setValidationQuery("SELECT 1");
            poolProperties.setTestOnReturn(false);
            poolProperties.setValidationInterval(30000);
            poolProperties.setTimeBetweenEvictionRunsMillis(30000);
            poolProperties.setMaxActive(100);
            poolProperties.setInitialSize(10);
            poolProperties.setMaxWait(10000);
            poolProperties.setRemoveAbandonedTimeout(60);
            poolProperties.setMinEvictableIdleTimeMillis(30000);
            poolProperties.setMinIdle(10);
            poolProperties.setLogAbandoned(true);
            poolProperties.setRemoveAbandoned(true);
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
            System.out.println("DriverManager");
            try {
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                log.log(Level.SEVERE, "Was unable to retrieve DB connection", e);
                throw new RuntimeException("jdbc layer error", e);
            }
        } else {
            System.out.println("ConnectionPool open");
            try {
                return source.getConnection();
            } catch (SQLException e) {
                log.log(Level.SEVERE, "Was unable to retrieve DB connection", e);
                throw new RuntimeException("jdbc layer error", e);
            }
        }
    }

    static void disConnect(Connection connection) {
        silentClose(connection);
    }

    private static void silentClose(Connection closable) {
        if (closable != null) {
            try {
                closable.close();
                System.out.println("ConnectionPool close");
            } catch (SQLException e) {
                log.log(Level.WARNING, String.format("Was unable to close: %s%n", closable), e);
            }
        }
    }
}