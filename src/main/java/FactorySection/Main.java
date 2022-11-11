package FactorySection;

import Entity.User;
import Servise.UserServise;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import java.sql.SQLException;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) throws SQLException {
        LOGGER.log(Level.INFO,"Start");
        Session session = HibernateUtil.getSessionFactry().openSession();
        LOGGER.log(Level.INFO,"Seccussfully connect to date base");

        User user = new User();
        user.setAge(132);
        user.setFirst_name("sodfhs");
        UserServise userServise = new UserServise();
        userServise.create(session,user);




        session.close();
        LOGGER.log(Level.INFO,"Seccussfully finish to date base");
    }

}
