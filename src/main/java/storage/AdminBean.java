package storage;

import storage.model.DatabaseKey;
import storage.model.DatabaseObject;
import storage.model.DatabaseTable;



@DatabaseTable(tableName = "Admin")
public class AdminBean extends DatabaseObject {
    @DatabaseKey(keyName = "id")
    private Long id;
    private String email;
    private String psw;

    public AdminBean() {
    }

    public AdminBean(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.psw = password;
    }

    public Long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return psw;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.psw = password;
    }

    @Override
    public String toString() {
        return "AdminBeen{" + "id=" + id + ", email=" + email + ", password=" + psw + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdminBean)) return false;

        AdminBean adminBean = (AdminBean) o;

        if (!getId().equals(adminBean.getId())) return false;
        if (!getEmail().equals(adminBean.getEmail())) return false;
        return getPassword().equals(adminBean.getPassword());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getPassword().hashCode();
        return result;
    }
}
