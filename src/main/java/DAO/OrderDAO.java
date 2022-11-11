package DAO;

import Entity.Orders;
import org.hibernate.Session;

import java.util.List;

public interface OrderDAO {
    void keep_order(Session session,int id);
    List<Orders> take_all_order(Session session);
    List<Orders> take_order_one_user(Session session,int id);
}
