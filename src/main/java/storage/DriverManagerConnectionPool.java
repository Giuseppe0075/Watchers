package storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DriverManagerConnectionPool {
    private static final List<Connection> freeDbConnections;

    static {
        freeDbConnections = new LinkedList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("DB driver not found!");
        }
    }

    private static Connection createDBConnection() throws SQLException {
        Connection newConnection;
        String db = "bcvrujqustmrsee1qise";
        String username = "uvkau7wy98snlgqm";
        String password = "oSLixuDHYthrPI6ZHPer";
        newConnection = DriverManager.getConnection(
                "jdbc:mysql://uvkau7wy98snlgqm:oSLixuDHYthrPI6ZHPer@bcvrujqustmrsee1qise-mysql.services.clever-cloud.com:3306/bcvrujqustmrsee1qise", username, password);
        newConnection.setAutoCommit(false);
        return newConnection;
    }
    public static synchronized Connection getConnection() throws SQLException {
        Connection connection;
        if (! freeDbConnections.isEmpty()) {
            connection = freeDbConnections.get(0);
            DriverManagerConnectionPool.freeDbConnections.remove(0);
            try {
                if (connection.isClosed())
                    connection = DriverManagerConnectionPool.getConnection();
            } catch (SQLException e) {
                connection = DriverManagerConnectionPool.getConnection();
            }
        } else
            connection = DriverManagerConnectionPool.createDBConnection();
        return connection;
    }
    public static synchronized void releaseConnection(Connection connection) {
        DriverManagerConnectionPool.freeDbConnections.add(connection);
    }
}
