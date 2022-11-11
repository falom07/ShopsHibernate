package Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
@Table(name = "products")
public class Products {
    @Id
    @SequenceGenerator(name = "seq_product",sequenceName = "seq_products",allocationSize = 1,initialValue = 15)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_product")
    @Column(name = "id_products")
    private int id;
    @Column(name = "product")
    private String product;
    @Column(name = "information")
    private String information;
    @Column(name = "price")
    private int price;
    @ManyToMany(mappedBy = "listProducts")
    private List<User> userList = new ArrayList<>();



    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Products products = (Products) o;
        return id == products.id && price == products.price && Objects.equals(product, products.product) && Objects.equals(information, products.information) ;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + id;
        result = 31 * result + price;
        result = 31 * result + (product == null ? 0:product.hashCode());
        result = 31 * result + (information == null ? 0:information.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "products{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", information='" + information + '\'' +
                ", price=" + price +
                '}';
    }
}
