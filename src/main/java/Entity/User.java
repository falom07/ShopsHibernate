package Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.Table;

import java.util.Objects;

@Entity
@Table(name = "users")
public class users {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    private int age;
    private String first_name;
    private String last_name;

    public users() {
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
        users users = (users) o;
        return id == users.id && age == users.age && Objects.equals(first_name, users.first_name) && Objects.equals(last_name, users.last_name);
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
        return "users{" +
                "id=" + id +
                ", age=" + age +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }
}
