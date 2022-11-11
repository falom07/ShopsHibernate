package Entity;


public class user_details {
    private int id;
    private String detail_information;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetail_information() {
        return detail_information;
    }

    public void setDetail_information(String detail_information) {
        this.detail_information = detail_information;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        user_details that = (user_details) o;
        return id == that.id && detail_information.equals(that.detail_information);
    }

    @Override
    public int hashCode() {
        int result = 31 + id;
        result = 31 * result + (detail_information == null ? 0 :detail_information.hashCode());
        return result;
    }
    @Override
    public String toString() {
        return "user_details{" +
                "id=" + id +
                ", detail_information='" + detail_information + '\'' +
                '}';
    }
}
