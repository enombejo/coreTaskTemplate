package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Util {
    // реализуйте настройку соеденения с БД
    private static String URL = "jdbc:mysql://localhost:3307/test?useSSL=false&serverTimezone=UTC";

    public Util() {

    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,"root", "MySQLfad1");
    }
}
