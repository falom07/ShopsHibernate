package DAO;

import Entity.User;
import Entity.User_details;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public interface UserDetailsDAO {
    void create(Session session, User_details userDetails);
    void delete (Session session,int id);
    User_details read_by_index(Session session,int id) throws SQLException;
    List<User_details> read_all(Session session) throws SQLException;
    void update(Session session,User_details userDetails) throws SQLException;
}
