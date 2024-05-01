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
    public static ResultSet doRetrieveByKey(String table, List<String> keys_names, List<Object> keys) throws Exception {
        ResultSet rs;
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

    public static ResultSet  doRetrieveByCond(String table, String cond) throws SQLException {
        ResultSet rs;
        StringBuilder query = new StringBuilder("SELECT * FROM " + table + " " + cond);
        try (Connection connection = DatabaseConnectionPool.getInstance().getConnection()) {
            rs = connection.executeQuery(String.valueOf(query));
            if(rs == null){
                throw new SQLException(table + " | doRetrieveByCond: Failed | condition: " + cond);
            }
        }catch (SQLException e){
            throw new SQLException(table + " | doRetrieveByCond: Failed | " + e.getMessage());
        }
        return rs;
    }

    public static ResultSet doRetrieveAll(String table) throws SQLException {
        ResultSet rs;
        StringBuilder query = new StringBuilder("SELECT * FROM " + table);
        try (Connection connection = DatabaseConnectionPool.getInstance().getConnection()) {
            rs = connection.executeQuery(String.valueOf(query));
            if(rs == null){
                throw new SQLException(table + " | doRetrieveAll: Failed |");
            }
        }catch (SQLException e){
            throw new SQLException(table + " | doRetrieveAll: Failed | " + e.getMessage());
        }
        return rs;
    }

    /**
     * The method updates the table with the values passed as parameters
     * The ids of the table MUST be in the last positions of the values list
     * @param table
     * @param columns
     * @param values
     * @throws SQLException
     */
    public static void doUpdate(String table, List<String> columns, List<Object> values, List<String> keys) throws SQLException {
        StringBuilder query = new StringBuilder("UPDATE " + table + " SET ");
        for(String c : columns){
            query.append(c).append(" = ?,");
        }
        query.deleteCharAt(query.length()-1);
        query.append(" WHERE ");
        for(String key : keys){
            query.append(key).append(" = ? AND ");
        }
        query.delete(query.length()-5, query.length());
        try (Connection connection = DatabaseConnectionPool.getInstance().getConnection()){
            int rs = connection.executeUpdate(String.valueOf(query), values);
            if(rs == 0){
                throw new SQLException(table + " | doUpdate: Failed | ");
            }
        }
    }
}
