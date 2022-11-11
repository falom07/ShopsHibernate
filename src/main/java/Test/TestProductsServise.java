package Test;

import Entity.Orders;
import Entity.Products;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class TestProductsServise {
    static SessionFactory sessionFactory;
    static Session session;

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
    public void createProsuctsTest(){
        session.beginTransaction();
        Products products = new Products();
        products.setPrice(12);
        products.setProduct("конфета");
        products.setInformation("Зачет");
        session.persist(products);
        int id = products.getId();
        session.getTransaction().commit();
        Assert.assertTrue(id > 0);
    }
    @Test
    public void deleteProsuctsTest(){
        session.beginTransaction();
        Products products = new Products();
        products.setPrice(12);
        products.setProduct("конфета");
        products.setInformation("Зачет");
        session.persist(products);
        System.out.println("Create user successfully");
        session.getTransaction().commit();
        session.beginTransaction();
        session.remove(products);
        session.getTransaction().commit();
        System.out.println("Delete user successfully");
    }
    @Test
    public void getProsuctsTest(){
        Products products = session.get(Products.class,1);
        Assert.assertEquals(products.getProduct(),"нічого");
    }
    @Test
    public void getAllProsuctsTest(){
        Query query = session.createNativeQuery("select id_products from products");
        List<Integer> list = query.getResultList();
        for(int i = 1;i<5;i++){
            Assert.assertEquals(list.get(i-1),(Integer) session.find(Products.class,i).getId());
        }

    }
    @Test
    public void updateProsuctsTest(){
        Products products = new Products();
        products.setPrice(12);
        products.setProduct("конфета");
        products.setInformation("Зачет");

        session.beginTransaction();
        session.persist(products);
        session.getTransaction().commit();
        Products products2 = products;
        products2.setPrice(13);

        session.beginTransaction();
        session.merge(products2);
        session.getTransaction().commit();
        boolean equalsProduct = products.getProduct().equals(products2.getProduct());
        Assert.assertNotEquals(false,equalsProduct);
        Orders orders = new Orders();
    }
}
