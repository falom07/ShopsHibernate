package Servise;

import DAO.UserDAO;
import Entity.User;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class UserServise implements UserDAO {
    @Override
    public void create(Session session, User user) {
        session.beginTransaction();
        session.persist(user);
        session.remove(user);
        user.setFirst_name("df");
        session.persist(user);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Session session,int id) {
        User user = new User();
        user.setId(id);
        session.beginTransaction();
        session.remove(user);
        session.getTransaction().commit();
    }

    @Override
    public User read_by_index(Session session,int id) throws SQLException {
        session.beginTransaction();
        User user = session.get(User.class,id);
        session.getTransaction().commit();
        return user;
    }

    @Override
    public List<User> read_all(Session session) throws SQLException {
        session.beginTransaction();

        CriteriaBuilder create = session.getCriteriaBuilder();
        CriteriaQuery<User> collection = create.createQuery(User.class);
        Root<User> root = collection.from(User.class);
        CriteriaQuery<User> allInfo = collection.select(root);
        TypedQuery<User> allQuery = session.createQuery(allInfo);
        return allQuery.getResultList();

    }

    @Override
    public void update(Session session, User user) throws SQLException {
        session.beginTransaction();
        session.merge(user);
        session.getTransaction().commit();
        session.close();
    }
}
