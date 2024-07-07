package Model.Models;

import Model.DAO;
import Model.Beans.UserBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/*
CREATE TABLE `User`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `email` VARCHAR(255) NOT NULL UNIQUE,
    `psw` VARCHAR(255) NOT NULL,
    `admin` TINYINT NOT NULL DEFAULT(1),
    `name` VARCHAR(255) NOT NULL,
    `surname` VARCHAR(255) NOT NULL,
    `birthday` DATE NULL,
    `road` VARCHAR(255) NOT NULL,
    `civic_number` VARCHAR(255) NOT NULL,
    `city` VARCHAR(255) NOT NULL,
    `CAP` CHAR(5) NOT NULL
);
 */

public class UserModel implements DAO<UserBean> {
    private static final String TABLE = "User";
    private static final List<String> COLUMNS = List.of("email", "psw", "name", "surname", "birthday", "road", "civic_number", "city", "CAP","admin");
    private static final List<String> KEYS = List.of("id");
    @Override
    public void doSave(UserBean userBean) throws Exception{
        List<Object> values = List.of(userBean.getEmail(), userBean.getPsw(), userBean.getName(), userBean.getSurname(), userBean.getBirthday(),
                userBean.getRoad(), userBean.getCivic_number(), userBean.getCity(), userBean.getCAP(), userBean.getAdmin());
        try {
            Model.doSave(TABLE, COLUMNS, values);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage() + " | userBean: " + userBean);
        }
    }

    @Override
    public void doDelete(UserBean userBean) throws Exception {
        Model.doDeleteByCond(TABLE, "WHERE id = ?", List.of(userBean.getId()));
    }

    @Override
    public void doDeleteByCond(String cond, List<Object> values) throws Exception {
        Model.doDeleteByCond(TABLE, cond, values);
    }

    @Override
    public UserBean doRetrieveByKey(List<Object> keys) throws Exception {
        //Check the number of keys
        if(keys.size() != 1) throw new SQLException("User | doRetrieveByKey: Failed | The number of keys is not 1");
        ResultSet rs = Model.doRetrieveByKey(TABLE,KEYS, keys);
        if(!rs.next()) return null;
        return new UserBean(rs);
    }

    @Override
    public Collection<UserBean> doRetrieveByCond(String cond, List<Object> values) throws Exception {
        List<UserBean> users = new ArrayList<>();
        ResultSet rs = Model.doRetrieveByCond(TABLE, cond, values);
        while(rs.next()) {
            users.add(new UserBean(rs));
        }
        return users;
    }

    @Override
    public Collection<UserBean> doRetrieveAll() throws Exception {
        List<UserBean> users = new ArrayList<>();
        ResultSet rs = Model.doRetrieveAll(TABLE);
        while(rs.next()) {
            users.add(new UserBean(rs));
        }
        return users;
    }
    @Override
    public void doSaveOrUpdate(UserBean userBean) throws Exception {
        if(userBean.getId() == 0){
            this.doSave(userBean);
            return;
        }
        List<Object> values = List.of(userBean.getEmail(), userBean.getPsw(), userBean.getName(), userBean.getSurname(), userBean.getBirthday(),
                userBean.getRoad(), userBean.getCivic_number(), userBean.getCity(), userBean.getCAP(),userBean.getAdmin(),userBean.getId());
        Model.doUpdate(TABLE, COLUMNS, values, KEYS);
    }
}