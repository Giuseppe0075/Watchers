package storage;

import storage.model.DatabaseKey;
import storage.model.DatabaseObject;
import storage.model.DatabaseTable;

import java.math.BigInteger;

@DatabaseTable(tableName = "Admin")
public class AdminBeen extends DatabaseObject {
    @DatabaseKey(keyName = "id")
    private Long id;
    private String email;
    private String psw;

    public AdminBeen() {
    }

    public AdminBeen(Long id, String email, String password) {
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
