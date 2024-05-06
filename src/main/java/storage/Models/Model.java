package storage.Models;

import database.Connection;
import database.DatabaseConnectionPool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Model{

    /**
     * The method doSave() saves in a table passed as parameter the values passed as parameter
     * The method throws an exception if the query fails
     * The values must be in the same order as the columns
     */
    public static void doSave(String table, List<String> columns, List<Object> values) throws SQLException {
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
        try (database.Connection connection = DatabaseConnectionPool.getInstance().getConnection()){
            int rs = connection.executeUpdate(String.valueOf(query), values);
            if(rs == 0){
                throw new SQLException(table + " | " + "doSave: Failed | ");
            }
        }
    }

    /**
     * The method doDeleteByCond() deletes from a table passed as parameter tuples that satisfy the condition passed as parameter
     * The method throws an exception if the query fails
     * The values must be in the same order as the columns
     */
    public static void doDeleteByCond(String table, String condition, List<Object> values) throws SQLException {
        try (database.Connection connection = DatabaseConnectionPool.getInstance().getConnection()){
            int rs = connection.executeUpdate("DELETE FROM " + table + " " + condition, values);

            if(rs == 0){
                throw new SQLException(table + " | doDeleteByCond: Failed | condition: " + condition);
            }
        }
    }

    /**
     * The method doRetrieveByKey() retrieves from a table passed as parameter the tuple that has the keys passed as parameter
     */
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
        return rs;
    }

    /**
     * The method doRetrieveByCond() retrieves from a table passed as parameter the tuples that satisfy the condition passed as parameter
     */
    public static ResultSet  doRetrieveByCond(String table, String condition, List<Object> values) throws SQLException {
        ResultSet rs;
        StringBuilder query = new StringBuilder("SELECT * FROM " + table + " " + condition);
        try (Connection connection = DatabaseConnectionPool.getInstance().getConnection()) {
            rs = connection.executeQuery(String.valueOf(query), values);
            if(rs == null){
                throw new SQLException(table + " | doRetrieveByCond: Failed | condition: " + condition);
            }
        }catch (SQLException e){
            throw new SQLException(table + " | doRetrieveByCond: Failed | " + e.getMessage());
        }
        return rs;
    }

    /**
     * The method doRetrieveAll() retrieves all the tuples from a table passed as parameter
     */
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
