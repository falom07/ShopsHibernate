package DAO;

import Entity.User;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    void create(Session session, User user);
    void delete (Session session,int id);
    User read_by_index(Session session,int id) throws SQLException;
    List<User> read_all(Session session) throws SQLException;
    void update(Session session,User user) throws SQLException;

}
