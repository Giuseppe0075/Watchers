package storage;

import database.DatabaseConnectionPool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Model<T>{
    public static void doSave(String table, List<Object> values, List<String> columns) throws SQLException, Exception {
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

    public void doDelete(String table, Object entity) throws SQLException, Exception {

    }

    public void doDeleteByCond(String table, String cond) throws SQLException, Exception {

    }

    public Object doRetrieveByKey(String table, Object... key) throws SQLException, Exception {
        return null;
    }

    public Collection doRetrieveByCond(String table, String cond) throws SQLException, Exception {
        return null;
    }

    public Collection doRetrieveAll(String table) throws SQLException, Exception {
        return null;
    }

    public void doSaveOrUpdate(String table, Object entity) throws SQLException, Exception {

    }
}
