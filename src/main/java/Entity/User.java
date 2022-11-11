package Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(name = "user_seq",
            sequenceName = "user_seqs", allocationSize = 1)
    @GeneratedValue (strategy = GenerationType.SEQUENCE,generator = "user_seq")
    @Column(name = "id_user")
    private int id;


    @OneToOne(mappedBy = "id",cascade = CascadeType.ALL)
    private User_details users_id_details;
    @OneToMany(mappedBy = "id_users")
    private List<Orders> listOrders = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL )
    @JoinTable(
            name = "chopping_cart",
            joinColumns = @JoinColumn(name = "id_user") ,
            inverseJoinColumns =  @JoinColumn(name = "id_product")
    )
    private List<Products> listProducts = new ArrayList<>();


    @Column(name = "age")
    private int age;
    @Column(name = "first_name")
    private String first_name;

    public User() {}
    public void removeProduct(Products products){
        listProducts.add(products);
        products.getUserList().add(this);
    }


    public List<Orders> getListOrders() {
        return listOrders;
    }

    public void setListOrders(List<Orders> listOrders) {
        this.listOrders = listOrders;
    }

    public List<Products> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Products> listProducts) {
        this.listProducts = listProducts;
    }

    public User_details getUsers_id_details() {
        return users_id_details;
    }

    public void setUsers_id_details(User_details users_id) {
        this.users_id_details = users_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User users = (User) o;
        return id == users.id && age == users.age && Objects.equals(first_name, users.first_name);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + id;
        result = 31 * result + age;
        result = 31 * result + (first_name == null ? 0 : first_name.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return  "id = " + id +
                " | age = " + age +
                " | first_name = " + first_name ;
    }
}
