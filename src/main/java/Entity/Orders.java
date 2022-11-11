package Entity;

import jakarta.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @SequenceGenerator(name = "order_seq",sequenceName = "orders_seqs",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "order_seq")
    @Column(name = "number_order")
    private int number_order;
    @ManyToOne()
    @JoinColumn(name = "id_user_from_order")
    private User id_users;
    @Column(name = "products_from_order")
    private String products_from_orders;
    @Column(name = "sum_of_order")
    private int sum_of_orders;

    public int getNumber_order() {
        return number_order;
    }

    public void setNumber_order(int number_order) {
        this.number_order = number_order;
    }

    public User getId_users() {
        return id_users;
    }

    public void setId_users(User id_users) {
        this.id_users = id_users;
    }

    public String getProducts_from_orders() {
        return products_from_orders;
    }

    public void setProducts_from_orders(String products_from_orders) {
        this.products_from_orders = products_from_orders;
    }
    public int getSum_of_orders() {
        return sum_of_orders;
    }

    public void setSum_of_orders(int sum_of_orders) {
        this.sum_of_orders = sum_of_orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return id_users == orders.id_users && sum_of_orders == orders.sum_of_orders && Objects.equals(products_from_orders, orders.products_from_orders);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + sum_of_orders;
        result = 31 * result + (products_from_orders == null ? 0: products_from_orders.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "номер замовлення " + number_order +
                ") імя = " + id_users.getFirst_name() +
                " || продукти = " + products_from_orders +
                " || сума = " + sum_of_orders;
    }
}
