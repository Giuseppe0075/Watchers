package storage;

import storage.model.DatabaseKey;
import storage.model.DatabaseObject;
import storage.model.DatabaseTable;

import java.sql.ResultSet;


@DatabaseTable(tableName = "Admin")
public class AdminBean extends Bean {
    @DatabaseKey(keyName = "id")
    private Long id;
    private String email;
    private String psw;

    public AdminBean() {
        this.id = 0L;
    }

    public AdminBean(ResultSet rs) {
        super(rs);
    }

    public AdminBean(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.psw = password;
    }

    public AdminBean(String email, String password) {
        this.email = email;
        this.psw = password;
    }

    public Long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPsw() {
        return psw;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPsw(String password) {
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
        return getPsw().equals(adminBean.getPsw());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getPsw().hashCode();
        return result;
    }
}
