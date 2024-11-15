package com.javaweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {

    public static Connection getMySQLConnection(String DB_URL, String USER, 
                                                    String PASS) 
                                                    throws SQLException {
        // Tạo URL kết nối đúng
        String connectionURL = DB_URL + "?user=" + USER + "&password=" + PASS;
        
        // Kết nối đến MySQL
        return DriverManager.getConnection(connectionURL);
    }
}
