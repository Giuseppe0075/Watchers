package database;

import org.tinylog.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Connection implements AutoCloseable{
    public final java.sql.Connection connection;

    public Connection(java.sql.Connection connection) {
        this.connection = connection;
    }

    private PreparedStatement buildStatement(String query, List<Object> params) throws SQLException {
        PreparedStatement ps = this.connection.prepareStatement(query);
        if(params != null) {
            int index = 1;
            for (Object o : params) {
                ps.setObject(index, o);
                index++;
            }
        }
        return ps;
    }

    public ResultSet executeQuery(String query) throws SQLException {
        return executeQuery(query, null);
    }

    public ResultSet executeQuery(String query, List<Object> params) throws SQLException {
        PreparedStatement ps = buildStatement(query, params);
        Logger.debug("-------"+ps.toString());
        return ps.executeQuery();


    }

    public int executeUpdate(String query) throws SQLException {
        return executeUpdate(query, null);
    }

    public int executeUpdate(String query, List<Object> params) throws SQLException {
        PreparedStatement ps = buildStatement(query, params);
        return ps.executeUpdate();

    }


    @Override
    public void close(){
        DatabaseConnectionPool.getInstance().releaseConnection(this);
    }
}
