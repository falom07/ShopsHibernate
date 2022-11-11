package Second_task.Entity;

import java.util.Objects;

public class Orders {
    private int id_users;
    private String products_from_orders;
    private int sum_of_orders;

    public int getId_users() {
        return id_users;
    }

    public void setId_users(int id_users) {
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
        result = 31 * result + id_users;
        result = 31 * result + sum_of_orders;
        result = 31 * result + (products_from_orders == null ? 0: products_from_orders.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "orders{" +
                "id_users=" + id_users +
                ", products_from_orders='" + products_from_orders + '\'' +
                ", sum_of_orders=" + sum_of_orders +
                '}';
    }
}
