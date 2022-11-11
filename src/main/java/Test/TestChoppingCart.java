package Test;

import Entity.Products;
import Entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class TestChoppingCart {
    static Session session;
    static SessionFactory sessionFactory;
    @BeforeClass
    public static void openConnection() {
        sessionFactory = new Configuration().configure("hibernate.cfg_for_test.xml").buildSessionFactory();
        session = sessionFactory.openSession();
        System.out.println("Connection successfully open");
    }
    @AfterClass
    public static void closeConnection(){
        session.close();
        sessionFactory.close();
        System.out.println("Connection successful close");
    }
    @Test
    public void addProduct(){
        User user = session.get(User.class,4);
        Products products = session.get(Products.class,2);
        user.getListProducts().add(products);
        products.getUserList().add(user);
        session.beginTransaction();
        session.persist(user);
        session.persist(products);
        session.getTransaction().commit();
        session.beginTransaction();
        user.getListProducts().remove(products);
        session.getTransaction().commit();
    }//the same
    @Test
    public void removeProduct(){
        User user = session.get(User.class,1);
        Products products = session.get(Products.class,1);
        user.getListProducts().add(products);
        products.getUserList().add(user);
        session.beginTransaction();
        session.persist(user);
        session.persist(products);
        session.getTransaction().commit();
        session.beginTransaction();
        user.getListProducts().remove(products);
        session.getTransaction().commit();
    }//the same
    @Test
    public void seeOrder(){
        User user = session.get(User.class,2);
        List<Products> list = user.getListProducts();
        Assert.assertEquals(list.size(),15);
    }
    @Test
    public void deleteOrder(){
        User user = session.get(User.class,3);
        Products products = session.get(Products.class,4);

        user.getListProducts().add(products);
        user.getListProducts().add(products);
        user.getListProducts().add(products);
        user.getListProducts().add(products);

        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();

        user.getListProducts().clear();
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
    }
}
