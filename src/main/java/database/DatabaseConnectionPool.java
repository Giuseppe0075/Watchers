package database;

import org.tinylog.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class DatabaseConnectionPool {
    //TODO: Put username and password into a config file, Test the Pool

    private static DatabaseConnectionPool instance = null;
    private static final String db = "bcvrujqustmrsee1qise";
    private static final String uri = "jdbc:mysql://uvkau7wy98snlgqm:oSLixuDHYthrPI6ZHPer@bcvrujqustmrsee1qise-mysql.services.clever-cloud.com:3306/" +db;
    private static final String username = "uvkau7wy98snlgqm";
    private static final String password = "oSLixuDHYthrPI6ZHPer";

    private static final int POOL_SIZE = 1;
    private final Queue<Connection> pool = new LinkedList<>();


    private DatabaseConnectionPool() {
        Logger.info("Trying to connect to db with URI: {}, USER: {}, PSW: {}", uri, username, password);

        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                pool.add(new Connection(DriverManager.getConnection(uri, username, password)));
            }catch (Exception e){
                Logger.error("failed to create the connection to the database", e);
            }
        }
        Logger.info("Connection Pool created, "+ pool.size() + "/" + POOL_SIZE + " Connection Created");

        Runtime runtime = Runtime.getRuntime();
        runtime.addShutdownHook(new Thread(() -> {
            Logger.info("Closing pool");
            for (Connection connection : pool) {
                try {
                    connection.connection.close();
                } catch (Exception e) {
                    Logger.warn("Failed to close connection in the pool", e);
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
     * Get a connection if avaiable, non blockin
     * @return a valid connection
     */
    public synchronized Connection getConnection2(){
        Logger.debug("Getting connection");
        return pool.remove();
    }

    public synchronized java.sql.Connection getConnection(){
        return pool.remove().connection;
    }

    public synchronized void releaseConnection(Connection connection) {
        Logger.debug("releasing connection");
        pool.add(connection);
    }

    public synchronized void releaseConnection(java.sql.Connection connection) {
        pool.add(new Connection(connection));
    }
}
