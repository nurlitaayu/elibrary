/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author windows_10
 */
public class koneksi{
    private static String username;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        koneksi.username = username;
    }
    
    private static final String DB_NAME = "db_library";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    
    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new Driver());
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }
}

