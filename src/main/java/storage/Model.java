package storage;

import database.Connection;
import database.DatabaseConnectionPool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Model{
    public static void doSave(String table, List<Object> values, List<String> columns) throws SQLException {
        try (database.Connection connection = DatabaseConnectionPool.getInstance().getConnection()){
            //INSERT INTO table (
            StringBuilder query = new StringBuilder("INSERT INTO " + table + " (");

            //value, value, value,...
            for(String value : columns){
                query.append(value).append(",");
            }
            //remove the last ,
            query.deleteCharAt(query.length()-1);

            //) VALUES ?,?,?,...
            query.append(") VALUES (");
            query.append("?,".repeat(columns.size()));
            //remove the last ,
            query.deleteCharAt(query.length()-1);
            query.append(")");
            int rs = connection.executeUpdate(String.valueOf(query), values);
            if(rs == 0){
                throw new SQLException(table + " | " + "doSave: Failed | ");
            }
        }
    }
    public static void doDeleteByCond(String table, String condition) throws SQLException {
        try (database.Connection connection = DatabaseConnectionPool.getInstance().getConnection()){
            int rs = connection.executeUpdate("DELETE FROM " + table + " " + condition);

            if(rs == 0){
                throw new SQLException(table + " | doDeleteByCond: Failed | condition: " + condition);
            }
        }
    }
    public static ResultSet doRetrieveByKey(String table, List<String> keys_names, List<Object> keys) throws SQLException, Exception {
        ResultSet rs = null;
        StringBuilder query = new StringBuilder("SELECT * FROM " + table + " WHERE ");
        for (String k : keys_names){
            query.append(k).append(" = ? AND ");
        }
        query.delete(query.length()-5, query.length());
        try (Connection connection = DatabaseConnectionPool.getInstance().getConnection()) {
            rs = connection.executeQuery(String.valueOf(query), keys);
            if(rs == null){
                throw new SQLException(table + " | doRetrieveByKey: Failed | keys: " + keys);
            }
        }catch (SQLException e){
            throw new SQLException(table + " | doRetrieveByKey: Failed | " + e.getMessage());
        }
        rs.next();
        return rs;
    }

    public static ResultSet  doRetrieveByCond(String table, String cond) throws SQLException, Exception {
        ResultSet rs = null;
        StringBuilder query = new StringBuilder("SELECT * FROM " + table + " " + cond);
        try (Connection connection = DatabaseConnectionPool.getInstance().getConnection()) {
            rs = connection.executeQuery(String.valueOf(query));
            if(rs == null){
                throw new SQLException(table + " | doRetrieveByKey: Failed | condition: " + cond);
            }
        }catch (SQLException e){
            throw new SQLException(table + " | doRetrieveByKey: Failed | " + e.getMessage());
        }
        return rs;
    }

    public static <T> Collection<T> doRetrieveAll(String table) throws SQLException, Exception {
        return null;
    }

    public void doSaveOrUpdate(String table, Object entity) throws SQLException, Exception {

    }
}
