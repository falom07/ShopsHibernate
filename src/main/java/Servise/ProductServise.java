package Servise;

import DAO.ProductDAO;
import Entity.Products;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class ProductServise implements ProductDAO {
    @Override
    public void create(Session session, Products products) {
        session.beginTransaction();
        session.persist(products);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Session session,int id) {
        Products products = new Products();
        products.setId(id);
        session.beginTransaction();
        session.remove(products);
        session.getTransaction().commit();
    }

    @Override
    public Products read_by_index(Session session,int id) throws SQLException {
        session.beginTransaction();
        Products products = session.get(Products.class,id);
        session.getTransaction().commit();
        return products;
    }

    @Override
    public List<Products> read_all(Session session) throws SQLException {

        CriteriaBuilder create = session.getCriteriaBuilder();
        CriteriaQuery<Products> collection = create.createQuery(Products.class);
        Root<Products> root = collection.from(Products.class);
        CriteriaQuery<Products> allInfo = collection.select(root);
        TypedQuery<Products> allQuery = session.createQuery(allInfo);
        return allQuery.getResultList();

    }
    @Override
    public void update(Session session, Products products) throws SQLException {
        session.beginTransaction();
        session.merge(products);
        session.getTransaction().commit();
        session.close();
    }
}
