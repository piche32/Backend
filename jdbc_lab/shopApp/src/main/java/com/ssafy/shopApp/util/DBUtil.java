package com.ssafy.shopApp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/ssafydb";    
    private static final String USER = "ssafy";
    private static final String PASSWORD = "ssafy";
    private static Properties properties = new Properties();
    
    static {
        properties.setProperty("user", USER);
        properties.setProperty("password", PASSWORD);
        properties.setProperty("profileSQL", "true");    	
    }
    
    public static Connection getConnection() throws SQLException {    
        return DriverManager.getConnection(URL, properties);
    }
    
    public static void close(AutoCloseable... closeables) {
    	for (AutoCloseable res : closeables) {
			try {
				res.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }
}