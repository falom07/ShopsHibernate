package Servise;

import DAO.ChoppingCartDao;
import Entity.Products;
import Entity.User;
import FactorySection.Main;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChoppingCartServise implements ChoppingCartDao {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    @Override
    public void add_product(Session session, int id_product, int id_user) {
        LOGGER.log(Level.INFO,"method for add product to chopping cart");
        LOGGER.log(Level.INFO,"Create List for chopping cart...");

        User user = session.get(User.class,id_user);
        Products product = session.get(Products.class,id_product);
        user.getListProducts().add(product);
        product.getUserList().add(user);

        LOGGER.log(Level.INFO,"Save List for DB(chopping cart) ...");

        session.beginTransaction();
        session.persist(user);
        session.persist(product);
        session.getTransaction().commit();
        LOGGER.log(Level.INFO,"All saved");
    }

    @Override

    public void delete_one(Session session, int id_product, int id_user) {
        LOGGER.log(Level.INFO,"method for delete one product from chopping cart");
        LOGGER.log(Level.INFO,"Take products...");

        User user = session.get(User.class,id_user);
        Products product = session.get(Products.class,id_product);

        LOGGER.log(Level.INFO,"Delete from DB");

        session.beginTransaction();
        user.getListProducts().remove(product);
        session.getTransaction().commit();
    }


    @Override
    public void delete_all(Session session, int id_user) {
        LOGGER.log(Level.INFO,"method for delete all product to chopping cart");
        LOGGER.log(Level.INFO,"Take user...");
        User user = session.get(User.class,id_user);

        LOGGER.log(Level.INFO,"Clear order from DB...");

        session.beginTransaction();
        user.getListProducts().clear();
        session.getTransaction().commit();
    }

    @Override
    public List<Products> read_all(Session session, int id_user) throws SQLException {
        LOGGER.log(Level.INFO,"method for read product from chopping cart bu id_user to chopping cart");
        LOGGER.log(Level.INFO,"Take user...");

        User user = session.get(User.class,id_user);

        LOGGER.log(Level.INFO,"Take list...");

        session.beginTransaction();
        List<Products> list = new ArrayList<>(user.getListProducts());
        return list;
    }
}
