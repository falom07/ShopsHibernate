package Test;

import Entity.Products;
import Entity.User;
import Servise.OrderServise;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class TestOrderServise {
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
    public void keppOrderTest(){
        User user = session.get(User.class,6);
        Products product = session.get(Products.class,3);
        Products product2 = session.get(Products.class,2);
        user.getListProducts().add(product);
        user.getListProducts().add(product2);
        product.getUserList().add(user);
        session.beginTransaction();
        session.persist(user);
        session.persist(product);
        session.getTransaction().commit();

        OrderServise orderServise = new OrderServise();
        orderServise.keep_order(session,6);
    }
    @Test
    public void takeOrderTest(){
        Query query = session.createNativeQuery("select sum_of_order from orders where id_user_from_order = 1 order by sum_of_order");
        List<Integer> listSum = query.getResultList();
        int a = 14063;
        int b = listSum.get(7);
        Assert.assertEquals(a,b);
        a = 0;
        b = listSum.get(0);
        Assert.assertEquals(a,b);
    }
    @Test
    public void takeAllOrderTest(){
        Query query = session.createNativeQuery("select sum_of_order from orders order by sum_of_order");
        List<Integer> listSum = query.getResultList();int i = 1;
        int a = 14063;
        int b = listSum.get(listSum.size()-1);
        Assert.assertEquals(a,b);
        a =0;
        b = listSum.get(0);
        Assert.assertEquals(a,b);

    }

}
