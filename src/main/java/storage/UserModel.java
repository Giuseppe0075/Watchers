package storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import database.*;
import java.sql.Connection;
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

    List<UserModel> users;
    private static final String TABLE = "User";

    @Override
    public void doSave(UserBean user) throws SQLException, Exception {

        PreparedStatement preparedStatement = null;

        try (database.Connection connection = DatabaseConnectionPool.getInstance().getConnection()){
            ResultSet rs = connection.executeQuery("INSERT INTO User"+"(email, psw, name, surname, birthday, road, civic_number, city, CAP) values (?,?,?,?,?,?,?,?,?)",
                    List.of(user.getEmail(), user.getPsw(), user.getName(), user.getSurname(), user.getBirthday(),
                            user.getRoad(), user.getCivic_number(), user.getCity(), user.getCAP()));

            if(rs == null){
                throw new SQLException("User | Inserimento non eseguito | 0 righe modificate | User: "+ user.toString());
            }
        }

    }

    @Override
    public void doDelete(UserBean user) throws SQLException, Exception {

        try (database.Connection connection = DatabaseConnectionPool.getInstance().getConnection()){
            ResultSet rs = connection.executeQuery("DELETE FROM User WHERE id = ?",List.of(user.getId()));

            if(rs == null){
                throw new SQLException("User | Eliminazione non eseguita | 0 righe modificate | User: "+ user.toString());
            }
        }
    }

    @Override
    public UserBean doRetrieveByKey(Object... key) throws SQLException, Exception {
        if(key.length != 1) throw new Exception("UserBean::doRetrieveByKey: Il numero di chiavi deve essere 1. Numero chiavi passate: " + key.length);


        UserBean user = null;

        try (database.Connection connection = DatabaseConnectionPool.getInstance().getConnection()){

            ResultSet rs = connection.executeQuery("SELECT * FROM User WHERE email = ?", List.of(key[0]));

            if(rs == null){
                throw new SQLException("User | Query non riuscita. | id:"+ key[0]);
            }

            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setPsw(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setBirthday(rs.getString("birthday"));
            user.setRoad(rs.getString("road"));
            user.setCivic_number(rs.getString("civic_number"));
            user.setCity(rs.getString("city"));
            user.setCAP(rs.getString("CAP"));
        }
        return user;
    }

    @Override
    public Collection<UserBean> doRetrieveByCond(String cond) throws SQLException, Exception {
        List<UserBean> users = null;
        if(cond == null) throw new Exception("UserBean::doRetrieveByCond: cond non può essere null");

        try (database.Connection connection = DatabaseConnectionPool.getInstance().getConnection()){

            ResultSet rs = connection.executeQuery("SELECT * FROM User WHERE"+ cond );// verificareeeee

            if(rs == null){
                throw new SQLException("User | Query non riuscita. | id:"+ cond);
            }

            while (rs.next()){
                UserBean user = new UserBean(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("birthday"),
                        rs.getString("road"),
                        rs.getString("civic_number"),
                        rs.getString("city"),
                        rs.getString("CAP")
                );
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public Collection<UserBean> doRetrieveAll() throws SQLException, Exception {
        List<UserBean> users = null;

        try( database.Connection connection = DatabaseConnectionPool.getInstance().getConnection()) {

            ResultSet rs = connection.executeQuery("SELECT * FROM User");
            if(rs == null){
                throw new SQLException("User | doRetrieveAll | Query non riuscita.");
            }

            while (rs.next()){
                UserBean user = new UserBean(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("birthday"),
                        rs.getString("road"),
                        rs.getString("civic_number"),
                        rs.getString("city"),
                        rs.getString("CAP")
                );
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public void doSaveOrUpdate(UserBean user) throws SQLException, Exception {
        try (database.Connection connection = DatabaseConnectionPool.getInstance().getConnection()){
            ResultSet rs = connection.executeQuery("INSERT INTO User"+"(email, psw, name, surname, birthday, road, civic_number, city, CAP) values (?,?,?,?,?,?,?,?,?)",
                    List.of(user.getEmail(),user.getPsw(),user.getName(),user.getSurname(),user.getBirthday(),user.getRoad(),user.getCivic_number(),user.getCity(),user.getCAP()));

            if(rs == null){
                throw new SQLException("User | Inserimento non eseguito | 0 righe modificate | User: "+ user.toString());
            }
        }
    }
}
