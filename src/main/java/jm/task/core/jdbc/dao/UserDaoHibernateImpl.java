package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory = Util.getSession();
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(
                    "create table users (id int not null auto_increment, " +
                            "age tinyint not null, lastName varchar(255) not null, " +
                            "name varchar(255) not null, primary key (id))")
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (sessionFactory.getCurrentSession().getTransaction() != null) {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            }
        }
    }
    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("drop table users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
                if (sessionFactory.getCurrentSession().getTransaction() != null) {
                    sessionFactory.getCurrentSession().getTransaction().rollback();
               }
        }
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.printf("User с именем %s успешно добавлен\n", name);
        } catch (Exception e) {

            if (sessionFactory.getCurrentSession().getTransaction() != null) {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (sessionFactory.getCurrentSession().getTransaction() != null) {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return  session.createQuery("from User").list();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (sessionFactory.getCurrentSession().getTransaction() != null) {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            }
        }
    }
}
