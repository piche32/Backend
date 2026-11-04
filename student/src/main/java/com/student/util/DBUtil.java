package com.student.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    //jdbc:mysql => https 대신 씀, 127.0.0.1:3306 => 처음에 mysql 다운 받으면서 셋팅했던 주소, ssafyb3 => 데이터베이스 생성했던 이름!!!! 뒤에는 뭐 몰라도 됨
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/ssafyb3?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
    private static final String USER = "ssafy";
    private static final String PASSWORD = "ssafy";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void close(AutoCloseable... closeables) {
        for (AutoCloseable c : closeables) {
            if (c != null) {
                try {
                    c.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}