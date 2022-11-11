package DAO;

import Entity.Products;
import Entity.User;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public interface ChoppingCartDao {
    void add_product(Session session,int id_product,int id_user);
    void delete_one (Session session,int id_product,int id_user);
    void delete_all (Session session,int id_user);
    List<Products> read_all(Session session, int id_user) throws SQLException;
}
