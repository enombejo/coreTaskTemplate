package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    // реализуйте настройку соеденения с БД
    private static String URL = "jdbc:mysql://localhost:3307/test?useSSL=false&serverTimezone=UTC";
    private static String username = "root";
    private static String password = "MySQLfad1";
    private static SessionFactory sessionFactory;

    public Util() {

    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, username, password);
    }

    public static SessionFactory getSessionHibernate() {
        if (sessionFactory == null) {
            Configuration cfg = new Configuration()
                    .addAnnotatedClass(User.class)
                    //.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect")
                    .setProperty("hibernate.connection.url", URL)
                    .setProperty("hibernate.connection.username", username)
                    .setProperty("hibernate.connection.password", password)
                    .setProperty("show_sql", "true")
                    .setProperty("hibernate.c3p0.min_size", "5")
                    .setProperty("hibernate.c3p0.max_size", "20");

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
            sessionFactory = cfg.buildSessionFactory(builder.build());

        }
        return sessionFactory;
    }
}
