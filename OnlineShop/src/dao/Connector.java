package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by laonen on 05.01.2017.
 */
public class Connector {
    private static final String URL = "jdbc:mysql://localhost/onlineshop?autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    static Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println("Driver loaded successfully!");
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
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
