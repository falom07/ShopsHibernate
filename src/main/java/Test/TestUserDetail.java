package Test;

import Entity.User;
import Entity.User_details;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class TestUserDetail {
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
    public void createUserDetailTest(){
        User_details userDetails = new User_details();
        User user = session.get(User.class,60);
        userDetails.setId(user);
        userDetails.setDetail_information("lalas");
        session.beginTransaction();
        session.merge(userDetails);
        session.getTransaction().commit();
    }//here update and create
    @Test
    public void deleteUserDetailTest(){
        session.beginTransaction();
        User user = new User();
        user.setFirst_name("Vita");
        user.setAge(12);
        session.persist(user);
        System.out.println("Create user successfully");
        session.getTransaction().commit();

        User_details userDetails = new User_details();
        userDetails.setId(user);
        userDetails.setDetail_information("df");
        session.beginTransaction();
        session.persist(userDetails);
        session.getTransaction().commit();

        session.beginTransaction();
        session.remove(userDetails);
        session.getTransaction().commit();

        session.beginTransaction();
        session.remove(user);
        session.getTransaction().commit();
        System.out.println("Delete user successfully");
    }
    @Test
    public void getUserDetailTest(){
        Query query = session.createNativeQuery("select detail_information from user_details");
        List<String> list = query.getResultList();
        Assert.assertEquals(list.get(1),session.get(User_details.class,70).getDetail_information());
    }
    @Test
    public void getAllUserDetailTest(){
        Query query = session.createNativeQuery("select detail_information from user_details");
        List<String> list = query.getResultList();
        Assert.assertEquals(list.get(1),session.get(User_details.class,70).getDetail_information());
        Assert.assertEquals(list.get(2),session.get(User_details.class,71).getDetail_information());
    }

}
