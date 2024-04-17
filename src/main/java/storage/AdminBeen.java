package storage;

public class AdminBeen {

    private Integer id;
    private String email;
    private String password;

    private AdminBeen() {
    }

    public AdminBeen(Integer id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AdminBeen{" + "id=" + id + ", email=" + email + ", password=" + password + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdminBeen)) return false;

        AdminBeen adminBeen = (AdminBeen) o;

        if (!getId().equals(adminBeen.getId())) return false;
        if (!getEmail().equals(adminBeen.getEmail())) return false;
        return getPassword().equals(adminBeen.getPassword());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getPassword().hashCode();
        return result;
    }
}
