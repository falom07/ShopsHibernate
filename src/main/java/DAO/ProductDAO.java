package DAO;

import Entity.Products;
import Entity.User;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
    void create(Session session, Products products);
    void delete (Session session,int id);
    Products read_by_index(Session session,int id) throws SQLException;
    List<Products> read_all(Session session) throws SQLException;
    void update(Session session,Products products) throws SQLException;
}
