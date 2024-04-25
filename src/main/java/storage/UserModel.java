package storage;

import java.sql.PreparedStatement;
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
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO User"+"(email, psw, name, surname, birthday, road, civic_number, city, CAP) values (?,?,?,?,?,?,?,?,?)");

            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2,user.getPsw());
            preparedStatement.setString(3,user.getName());
            preparedStatement.setString(4,user.getSurname());
            preparedStatement.setString(5,user.getBirthday());
            preparedStatement.setString(6,user.getRoad());
            preparedStatement.setString(7,user.getCivic_number());
            preparedStatement.setString(8,user.getCity());
            preparedStatement.setString(9,user.getCAP());

            int rs = preparedStatement.executeUpdate();
            if(rs == 0){
                throw new SQLException("User | Inserimento non eseguito | 0 righe modificate | User: "+ user.toString());
            }
        }
        finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            DatabaseConnectionPool.getInstance().releaseConnection(connection);
        }

    }

    @Override
    public void doDelete(UserBean user) throws SQLException, Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = DatabaseConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM User WHERE id = ?");

            preparedStatement.setString(1, user.getEmail());

            int rs = preparedStatement.executeUpdate();
            if(rs == 0){
                throw new SQLException("User | Eliminazione non eseguita | 0 righe modificate | User: "+ user.toString());
            }
        }
        finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            DatabaseConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public UserBean doRetrieveByKey(Object... key) throws SQLException, Exception {
        if(key.length != 1) throw new Exception("UserBean::doRetrieveByKey: Il numero di chiavi deve essere 1. Numero chiavi passate: " + key.length);


        UserBean user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = DatabaseConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM User WHERE email = ?");

            preparedStatement.setString(1, (String) key[0]);

            var rs = preparedStatement.executeQuery();
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
        }finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            DatabaseConnectionPool.getInstance().releaseConnection(connection);
        }
        return user;
    }

    @Override
    public Collection<UserBean> doRetrieveByCond(String cond) throws SQLException, Exception {
        List<UserBean> users = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        if(cond == null) throw new Exception("UserBean::doRetrieveByCond: cond non può essere null");

        try{
            connection = DatabaseConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM User WHERE"+ cond );// verificareeeee

            var rs = preparedStatement.executeQuery();
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
        }finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            DatabaseConnectionPool.getInstance().releaseConnection(connection);
        }
        return users;
    }

    @Override
    public Collection<UserBean> doRetrieveAll() throws SQLException, Exception {
        List<UserBean> users = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM User");

            var rs = preparedStatement.executeQuery();
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
        }finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            DatabaseConnectionPool.getInstance().releaseConnection(connection);
        }
        return users;
    }

    @Override
    public void doSaveOrUpdate(UserBean user) throws SQLException, Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO User"+"(email, psw, name, surname, birthday, road, civic_number, city, CAP) values (?,?,?,?,?,?,?,?,?)");

            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2,user.getPsw());
            preparedStatement.setString(3,user.getName());
            preparedStatement.setString(4,user.getSurname());
            preparedStatement.setString(5,user.getBirthday());
            preparedStatement.setString(6,user.getRoad());
            preparedStatement.setString(7,user.getCivic_number());
            preparedStatement.setString(8,user.getCity());
            preparedStatement.setString(9,user.getCAP());

            int rs = preparedStatement.executeUpdate();
            if(rs == 0){
                throw new SQLException("User | Inserimento non eseguito | 0 righe modificate | User: "+ user.toString());
            }
        }
        finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            DatabaseConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
