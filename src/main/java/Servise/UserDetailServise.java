package Servise;

import DAO.UserDetailsDAO;
import Entity.User_details;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class UserDetailServise implements UserDetailsDAO {
    @Override
    public void create(Session session, User_details userDetails) {
        session.beginTransaction();
        session.persist(userDetails);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Session session, int id)  {
        session.beginTransaction();
        User_details userDetails = session.get(User_details.class,id);
        session.remove(userDetails);
        session.getTransaction().commit();
    }

    @Override
    public User_details read_by_index(Session session, int id) throws SQLException {
        session.beginTransaction();
        User_details userDetails = session.get(User_details.class,id);
        session.getTransaction().commit();
        return userDetails;
    }

    @Override
    public List<User_details> read_all(Session session) throws SQLException {
        session.beginTransaction();

        CriteriaBuilder create = session.getCriteriaBuilder();
        CriteriaQuery<User_details> collection = create.createQuery(User_details.class);
        Root<User_details> root = collection.from(User_details.class);
        CriteriaQuery<User_details> allInfo = collection.select(root);
        TypedQuery<User_details> allQuery = session.createQuery(allInfo);
        return allQuery.getResultList();
    }

    @Override
    public void update(Session session, User_details userDetails) throws SQLException {
        session.beginTransaction();
        session.merge(userDetails);
        session.getTransaction().commit();
        session.close();
    }
}
