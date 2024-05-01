package storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import database.*;
/*
CREATE TABLE `User`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
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

public class UserModel implements DAO<UserBean>{
    private static final String TABLE = "User";
    private static final List<String> columns = List.of("email", "psw", "name", "surname", "birthday", "road", "civic_number", "city", "CAP");
    @Override
    public void doSave(UserBean user) throws Exception{
        List<Object> values = List.of(user.getEmail(), user.getPsw(), user.getName(), user.getSurname(), user.getBirthday(),
                user.getRoad(), user.getCivic_number(), user.getCity(), user.getCAP());
        try {
            Model.doSave(TABLE,values, columns);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage() + " | user: " + user);
        }
    }

    @Override
    public void doDelete(UserBean user) throws Exception {

    }

    @Override
    public void doDeleteByCond(String cond) throws Exception {
        Model.doDeleteByCond(TABLE, cond);
    }

    @Override
    public UserBean doRetrieveByKey(List<Object> keys) throws Exception {
        //Check the number of keys
        if(keys.size() != 1) throw new SQLException("User | doRetrieveByKey: Failed | The number of keys is not 1");
        ResultSet rs = Model.doRetrieveByKey(TABLE,List.of("id"), keys);

        return new UserBean(rs);
    }

    @Override
    public Collection<UserBean> doRetrieveByCond(String cond) throws Exception {
        List<UserBean> users = new ArrayList<>();
        if(cond == null) throw new SQLException("User | doRetrieveByCond: Failed | condition is null");
        ResultSet rs = null;
        try (database.Connection connection = DatabaseConnectionPool.getInstance().getConnection()){

            rs = connection.executeQuery("SELECT * FROM User WHERE " + cond );

            if(rs == null){
                throw new SQLException("User | doRetrieveByCond: Failed | condition:" + cond);
            }
        }
        while(rs.next())
            users.add(new UserBean(rs));
        return users;
    }

    @Override
    public Collection<UserBean> doRetrieveAll() throws Exception {
        List<UserBean> users = new ArrayList<>();
        try( database.Connection connection = DatabaseConnectionPool.getInstance().getConnection()) {
            ResultSet rs = connection.executeQuery("SELECT * FROM User");
            if(rs == null){
                throw new SQLException("User | doRetrieveAll: Failed |");
            }
            while(rs.next())
                users.add(new UserBean(rs));
        }
        return users;
    }
    @Override
    public void doSaveOrUpdate(UserBean user) throws Exception {
        if(user.getId() == 0){
            this.doSave(user);
            return;
        }
        try (database.Connection connection = DatabaseConnectionPool.getInstance().getConnection()){
            int rs = connection.executeUpdate("UPDATE User SET email = ?, psw = ?, name = ?, surname = ?, birthday = ?, road = ?, civic_number = ?, city = ?, CAP = ? WHERE id = ?",
                        List.of(user.getEmail(),user.getPsw(),user.getName(),user.getSurname(),user.getBirthday(),user.getRoad(),user.getCivic_number(),user.getCity(),user.getCAP(), user.getId()));

            if(rs == 0){
                throw new SQLException("User | doSaveOrUpdate: Failed | user: " + user);
            }
        }
    }
}