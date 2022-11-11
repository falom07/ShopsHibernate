package Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user_details")
public class User_details {
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_details",referencedColumnName = "id_user")
    private User id;
    @Column(name = "detail_information")
    private String detail_information;

    public User getId() {
        return id;
    }

    public void setId(User id) {
        this.id = id;
    }

    public String getDetail_information() {
        return detail_information;
    }

    public void setDetail_information(String detail_information) {
        this.detail_information = detail_information;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, detail_information);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User_details that = (User_details) o;
        return id == that.id && detail_information.equals(that.detail_information);
    }

    @Override
    public String toString() {
        return "name = " + id.getFirst_name() +
                "| detail_information = " + detail_information;
    }
}
