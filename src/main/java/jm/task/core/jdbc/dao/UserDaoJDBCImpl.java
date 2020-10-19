package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Connection conn = Util.getConnection()) {
            conn.createStatement().execute("CREATE TABLE IF NOT EXISTS user (id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(45) NULL, lastName VARCHAR(45) NULL, age INT NULL, PRIMARY KEY (id), UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Connection conn = Util.getConnection()) {
            conn.createStatement().execute("DROP TABLE IF EXISTS user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection conn = Util.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)")) {
                pstmt.setString(1, name);
                pstmt.setString(2, lastName);
                pstmt.setByte(3, age);
                pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(Connection conn = Util.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM user WHERE id = ?")) {
                pstmt.setLong(1, id);
                pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList();
        try(Connection conn = Util.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM user");
            ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User(resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getByte("age"));
                    user.setId(resultSet.getLong("id"));
                    list.add(user);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try(Connection conn = Util.getConnection()) {
            conn.createStatement().execute("TRUNCATE TABLE User");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
