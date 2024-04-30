package storage;

import java.sql.PreparedStatement;
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
    @Override
    public void doSave(UserBean user) throws SQLException, Exception {

        PreparedStatement preparedStatement = null;

        try (database.Connection connection = DatabaseConnectionPool.getInstance().getConnection()){
            int rs = connection.executeUpdate("INSERT INTO User"+"(email, psw, name, surname, birthday, road, civic_number, city, CAP) values (?,?,?,?,?,?,?,?,?)",
                    List.of(user.getEmail(), user.getPsw(), user.getName(), user.getSurname(), user.getBirthday(),
                            user.getRoad(), user.getCivic_number(), user.getCity(), user.getCAP()));

            if(rs == 0){
                throw new SQLException("User | Inserimento non eseguito | 0 righe modificate | User: "+ user.toString());
            }
        }

    }

    @Override
    public void doDelete(UserBean user) throws SQLException, Exception {

        try (database.Connection connection = DatabaseConnectionPool.getInstance().getConnection()){
            int rs = connection.executeUpdate("DELETE FROM User WHERE id = ?",List.of(user.getId()));

            if(rs == 0){
                throw new SQLException("User | Eliminazione non eseguita | 0 righe modificate | User: "+ user.toString());
            }
        }
    }

    @Override
    public void doDeleteByCond(String cond) throws SQLException, Exception {
        try (database.Connection connection = DatabaseConnectionPool.getInstance().getConnection()){
            int rs = connection.executeUpdate("DELETE FROM User " + cond);

            if(rs == 0){
                throw new SQLException("User | Eliminazione non eseguita | 0 righe modificate ");
            }
        }
    }

    @Override
    public UserBean doRetrieveByKey(Object... key) throws SQLException, Exception {
        //Check the number of keys
        if(key.length != 1) throw new Exception("UserBean::doRetrieveByKey: Il numero di chiavi deve essere 1. Numero chiavi passate: " + key.length);
        UserBean user = null;

        //try connection
        try (database.Connection connection = DatabaseConnectionPool.getInstance().getConnection()){
            ResultSet rs = connection.executeQuery("SELECT * FROM User WHERE id = ?", List.of(key[0]));

            if(rs == null){
                throw new SQLException("User | Query non riuscita. | id:"+ key[0]);
            }
            rs.next();
            user = new UserBean(rs);
        }
        return user;
    }

    @Override
    public Collection<UserBean> doRetrieveByCond(String cond) throws SQLException, Exception {
        List<UserBean> users = new ArrayList<>();
        if(cond == null) throw new Exception("UserBean::doRetrieveByCond: cond non può essere null");
        ResultSet rs = null;
        try (database.Connection connection = DatabaseConnectionPool.getInstance().getConnection()){

            rs = connection.executeQuery("SELECT * FROM User WHERE "+ cond );

            if(rs == null){
                throw new SQLException("User | Query non riuscita. | id:"+ cond);
            }
        }
        while(rs.next())
            users.add(new UserBean(rs));
        return users;
    }

    @Override
    public Collection<UserBean> doRetrieveAll() throws SQLException, Exception {
        List<UserBean> users = new ArrayList<>();

        try( database.Connection connection = DatabaseConnectionPool.getInstance().getConnection()) {

            ResultSet rs = connection.executeQuery("SELECT * FROM User");
            if(rs == null){
                throw new SQLException("User | doRetrieveAll | Query non riuscita.");
            }
            while(rs.next())
                users.add(new UserBean(rs));
        }
        return users;
    }


    @Override
    public void doSaveOrUpdate(UserBean user) throws SQLException, Exception {

        if(user.getId() == 0){
            this.doSave(user);
            return;
        }

        try (database.Connection connection = DatabaseConnectionPool.getInstance().getConnection()){
            int rs = connection.executeUpdate("UPDATE User SET email = ?, psw = ?, name = ?, surname = ?, birthday = ?, road = ?, civic_number = ?, city = ?, CAP = ? WHERE id = ?",
                        List.of(user.getEmail(),user.getPsw(),user.getName(),user.getSurname(),user.getBirthday(),user.getRoad(),user.getCivic_number(),user.getCity(),user.getCAP(), user.getId()));

            if(rs == 0){
                throw new SQLException("User | Inserimento non eseguito | 0 righe modificate | User: "+ user.toString());
            }
        }
    }
}
