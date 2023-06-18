package com.solvd.block2.sql.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DbUtil {
    private static final int MAX_CONNECTIONS = 10; // Maximum number of connections in the pool
    private static final String PROPERTIES_FILE = "db.properties"; // File name for the JDBC properties
    private static BlockingQueue<Connection> connectionPool;
    private static final Logger LOGGER = LogManager.getLogger(DbUtil.class);

    private static void initializeConnectionPool() {
        Properties properties = loadProperties();

        if (properties != null) {
            String dbUrl = properties.getProperty("jdbc.url");
            String dbUser = properties.getProperty("jdbc.username");
            String dbPassword = properties.getProperty("jdbc.password");

            connectionPool = new ArrayBlockingQueue<>(MAX_CONNECTIONS);

            try {
                Connection connection = createConnection(dbUrl, dbUser, dbPassword);
                if (connection != null) {
                    connectionPool.offer(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static Connection createConnection(String dbUrl, String dbUser, String dbPassword) throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = DbUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (inputStream != null) {
                properties.load(inputStream);
                return properties;
            } else {
                throw new IOException("File not found: " + PROPERTIES_FILE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    This code establishes a connection to a database using a connection pool. If the connection pool is not initialized,
    it initializes it. Then, it tries to get a connection from the connection pool.
    If there are no connections available and the maximum number of connections has not been reached,
    it creates a new connection using properties loaded from a file and adds it to the connection pool.
    If there are still no connections available, the code waits for a connection to become available.
    */
    public static Connection getConnection() throws InterruptedException, SQLException {
        if (connectionPool == null) {
            initializeConnectionPool();
        }

        Connection connection = connectionPool.poll();
        if (connection == null && connectionPool.size() < MAX_CONNECTIONS) {
            Properties properties = loadProperties();
            if (properties != null) {
                String dbUrl = properties.getProperty("jdbc.url");
                String dbUser = properties.getProperty("jdbc.username");
                String dbPassword = properties.getProperty("jdbc.password");
                connection = createConnection(dbUrl, dbUser, dbPassword);
                if (connection != null) {
                    connectionPool.offer(connection);
                }
            }
        }

        if (connection == null) {
            LOGGER.info("Waiting for a connection...");
            connection = connectionPool.take();
        }
        return connection;
    }

    public static void releaseConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connectionPool.offer(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



