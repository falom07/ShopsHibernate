package Test;

import Entity.User;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class TestUserServise {
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
    public void createUserTest(){
        session.beginTransaction();
        User user = new User();
        user.setAge(12);
        user.setFirst_name("Misha");
        session.persist(user);
        int id = user.getId();
        session.getTransaction().commit();
        Assert.assertTrue(id > 0);
    }
    @Test
    public void deleteUserTest(){
        session.beginTransaction();
        User user = new User();
        user.setFirst_name("Vita");
        user.setAge(12);
        session.persist(user);
        System.out.println("Create user successfully");
        session.getTransaction().commit();
        session.beginTransaction();
        session.remove(user);
        session.getTransaction().commit();
        System.out.println("Delete user successfully");
    }
    @Test
    public void getUserTest(){
        User user = session.get(User.class,1);
        Assert.assertEquals(user.getFirst_name(),"Саша");
    }
    @Test
    public void getAllUserTest(){
        Query query = session.createNativeQuery("select id_user from users");
        List<Integer> list = query.getResultList();
        for(int i = 1;i<5;i++){
           Assert.assertEquals(list.get(i-1),(Integer) session.find(User.class,i).getId());
        }

    }
    @Test
    public void updateUserTest(){
        User user = new User();
        user.setFirst_name("Toha");
        user.setAge(47);

        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();

        user.setFirst_name("Loha");

        session.beginTransaction();
        session.merge(user);
        session.getTransaction().commit();

        Assert.assertEquals(user, user);
    }

}
