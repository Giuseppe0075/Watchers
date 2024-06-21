package database;

import org.tinylog.Logger;
import utils.ConfigurationProperties;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class DatabaseConnectionPool {

    private static DatabaseConnectionPool instance = null;
    private static final String db = "bcvrujqustmrsee1qise";
    private static final String uri = ConfigurationProperties.getUrl();//+"?autoReconnect=true"
    private static final String username = ConfigurationProperties.getUsername();
    private static final String password = ConfigurationProperties.getPassword();

    private static final int POOL_SIZE = 3;
    private final Queue<Connection> pool = new LinkedList<>();


    private DatabaseConnectionPool() {
        Logger.info("Trying to connect to db with URI: {}, USER: {}, PSW: {}", uri, username, password);

        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                pool.add(new Connection(DriverManager.getConnection(uri, username, password)));
            }catch (Exception e){
                Logger.error(e, "failed to create the connection to the database");
            }
        }
        Logger.info("Connection Pool created, "+ pool.size() + "/" + POOL_SIZE + " Connection Created");

        Runtime runtime = Runtime.getRuntime();
        runtime.addShutdownHook(new Thread(() -> {
            for (Connection connection : pool) {
                try {
                    connection.connection.close();
                } catch (Exception e) {
                    Logger.warn(e, "Failed to close connection in the pool");
                }
            }

            Logger.info("Db connection pool closed");
        }));
    }

    /**
     * Singleton Class
     * @return the instance of the class
     */
    public static DatabaseConnectionPool getInstance(){
        if(instance == null){
            instance = new DatabaseConnectionPool();
        }
        return instance;
    }

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            Logger.error("JDBC DRIVER NOT FOUND", e);
        }
    }

    /**
     * Get a connection from the pool. If no connections are available, wait
     * for a short period before throwing an exception.
     *
     * @throws SQLException if a connection cannot be obtained within the timeout
     */
    public synchronized Connection getConnection() throws SQLException {
        final long TIMEOUT_MILLIS = 500; // Adjust as needed
        long start = System.currentTimeMillis();
        Connection connection = pool.poll(); // Try to get a connection without blocking

        while (connection == null) {
            try {
                long elapsedTime = System.currentTimeMillis() - start;
                if (elapsedTime >= TIMEOUT_MILLIS) {
                    throw new SQLException("No connections available in the pool within timeout");
                }

                // Check if a connection became available after the initial poll
                connection = pool.poll();
                if (connection != null) {
                    break; // Connection obtained, exit the loop
                }

                // Sleep for a short interval before retrying
                Thread.sleep(50); // Adjust as needed
            } catch (InterruptedException e) {
                throw new SQLException("Interrupted while waiting for connection", e);
            }
        }

        Logger.debug("Connection Pool added connection " + (!pool.isEmpty() ? pool.size() - 1 : 0) + "/" + POOL_SIZE);
        return connection;
    }



    public synchronized void releaseConnection(Connection connection) {
        pool.add(connection);
        Logger.debug("Releasing connection. " + pool.size() + "/" + POOL_SIZE);
    }

    public synchronized void releaseConnection(java.sql.Connection connection) {
        pool.add(new Connection(connection));
    }
}
