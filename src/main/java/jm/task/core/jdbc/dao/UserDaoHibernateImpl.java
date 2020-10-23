package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionHibernate().openSession();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS user (id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(45) NULL, lastName VARCHAR(45) NULL, age INT NULL, PRIMARY KEY (id), UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE)").executeUpdate();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionHibernate().openSession();
        session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionHibernate().openSession();
        Transaction tx = session.beginTransaction();
        session.save("user", new User(name, lastName, age));
        tx.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionHibernate().openSession();
        session.createQuery("DELETE User WHERE id = :id").setParameter("id", id).executeUpdate();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session =  Util.getSessionHibernate().openSession();
        List<User> list = (List<User>) session.createQuery("From User").list();
        session.close();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionHibernate().openSession();
        session.createSQLQuery("TRUNCATE TABLE User").executeUpdate();
        session.close();
    }
}
