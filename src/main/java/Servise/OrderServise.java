package Servise;

import DAO.OrderDAO;
import Entity.Orders;
import Entity.Products;
import Entity.User;
import FactorySection.Main;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.*;
import org.hibernate.Session;

import java.util.List;


public class OrderServise implements OrderDAO {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    @Override
    public void keep_order(Session session, int id) {
        LOGGER.log(Level.INFO,"method for keep order to order(BD)");
        LOGGER.log(Level.INFO,"Create objects...");

        User user = session.get(User.class,id);
        Orders orders = new Orders();
        ChoppingCartServise choppingCart = new ChoppingCartServise();
        StringBuilder listProduct = new StringBuilder();
        int sumOrder = 0;

        LOGGER.log(Level.INFO,"Take list product and sum...");

        for(Products products : user.getListProducts()){
            listProduct.append(products.getProduct()).append(", ");
            sumOrder += products.getPrice();

        }
        LOGGER.log(Level.INFO,"Show list product and sum...");
        listProduct.deleteCharAt(listProduct.length()-2);
        System.out.println("Продукти : " + listProduct);
        System.out.println("Сума : " + sumOrder);

        LOGGER.log(Level.INFO,"Add list product and sum to order...");

        orders.setSum_of_orders(sumOrder);
        orders.setId_users(user);
        orders.setProducts_from_orders(listProduct.toString());
        LOGGER.log(Level.INFO,"Delete list product and sum from chopping cart of user...");

        choppingCart.delete_all(session,id);
        LOGGER.log(Level.INFO,"Save order...");

        session.beginTransaction();

        session.persist(orders);

        session.getTransaction().commit();

    }

    @Override
    public List<Orders> take_all_order(Session session) {
        LOGGER.log(Level.INFO,"method take all order...");
        LOGGER.log(Level.INFO,"take all order...");

        CriteriaBuilder create = session.getCriteriaBuilder();
        CriteriaQuery<Orders> collection = create.createQuery(Orders.class);
        Root<Orders> root = collection.from(Orders.class);
        CriteriaQuery<Orders> allInfo = collection.select(root);
        TypedQuery<Orders> allQuery = session.createQuery(allInfo);

        LOGGER.log(Level.INFO,"return all order...");

        return  allQuery.getResultList();
    }

    @Override
    public List<Orders> take_order_one_user(Session session, int id) {
        LOGGER.log(Level.INFO,"method take order one user...");
        LOGGER.log(Level.INFO,"get user...");

        User user = session.get(User.class,id);
        LOGGER.log(Level.INFO,"do query...");
        Query query = session.createNativeQuery("select * from orders where id_user_from_order = ?",Orders.class);
        query.setParameter(1,user.getId());
        LOGGER.log(Level.INFO,"return result...");
        return query.getResultList();
    }
}
