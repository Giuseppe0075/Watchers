package storage.Beans;

import user.SignupDataForm;

import java.sql.Date;
import java.sql.ResultSet;

/*
CREATE TABLE `User`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `email` VARCHAR(255) NOT NULL UNIQUE,
    `psw` VARCHAR(255) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `surname` VARCHAR(255) NOT NULL,
    `birthday` DATE NULL,
    `road` VARCHAR(255) NOT NULL,
    `civic_number` VARCHAR(255) NOT NULL,
    `city` VARCHAR(255) NOT NULL,
    `CAP` CHAR(5) NOT NULL
);
 */
public class UserBean extends Bean{
    private Long id;
    private String email;
    private String psw;
    private Boolean admin;
    private String name;
    private String surname;
    private Date birthday;
    private String road;
    private String civic_number;
    private String city;
    private String CAP;

    public UserBean(ResultSet rs) {
        super(rs);
    }

    public UserBean(Long id, String email, String psw,Boolean admin, String name, String surname, Date birthday, String road, String civic_number, String city, String CAP) {
        this.id = id;
        this.email = email;
        this.psw = psw;
        this.admin = admin;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.road = road;
        this.civic_number = civic_number;
        this.city = city;
        this.CAP = CAP;
    }

    public UserBean(Long id, String email, String psw, String name, String surname, Date birthday, String road, String civic_number, String city, String CAP) {
        this.id = id;
        this.email = email;
        this.psw = psw;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.road = road;
        this.civic_number = civic_number;
        this.city = city;
        this.CAP = CAP;
    }

    public UserBean(SignupDataForm data){
        this.email = data.email;
        this.psw = data.password;
        this.name = data.name;
        this.surname = data.surname;
        this.birthday = data.birthday;
        this.road = data.address;
        this.civic_number = data.civicNumber;
        this.city = data.city;
        this.CAP = data.CAP;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getCivic_number() {
        return civic_number;
    }

    public void setCivic_number(String civic_number) {
        this.civic_number = civic_number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCAP() {
        return CAP;
    }

    public void setCAP(String CAP) {
        this.CAP = CAP;
    }

    public Boolean getAdmin() {
        return this.admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", psw='" + psw + '\'' +
                ", isAdmin=" + admin +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserBean)) return false;

        UserBean userBean = (UserBean) o;

        if (!getId().equals(userBean.getId())) return false;
        if (!getEmail().equals(userBean.getEmail())) return false;
        if (!getPsw().equals(userBean.getPsw())) return false;
        if (!getAdmin().equals(userBean.getAdmin())) return false;
        if (getName() != null ? !getName().equals(userBean.getName()) : userBean.getName() != null) return false;
        return getSurname() != null ? getSurname().equals(userBean.getSurname()) : userBean.getSurname() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getPsw().hashCode();
        result = 31 * result + getAdmin().hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        return result;
    }
}
