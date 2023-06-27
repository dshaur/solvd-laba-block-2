package com.solvd.block2.sql.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DbUtil {
    private static final Logger LOGGER = LogManager.getLogger(DbUtil.class.getName());
    private static final int MAX_POOL_SIZE = 10;
    private static final BlockingQueue<Connection> connectionPool = new LinkedBlockingQueue<>(MAX_POOL_SIZE);
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load db.properties file.", e);
        }
    }


    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("jdbc.url"),
                properties.getProperty("jdbc.username"),
                properties.getProperty("jdbc.password")
        );
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = connectionPool.poll();
        if (connection == null) {
            if (connectionPool.size() < MAX_POOL_SIZE) {
                connection = createConnection();
                LOGGER.info("Added connection: " + Thread.currentThread().getName());
            } else {
                try {
                    connection = connectionPool.take();
                    LOGGER.info("Client connected: " + Thread.currentThread().getName() +
                            " [Available connections: " + connectionPool.size() + "]");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new SQLException("Failed to obtain a database connection.", e);
                }
            }
        } else {
            LOGGER.info("Client connected: " + Thread.currentThread().getName() +
                    " [Available connections: " + connectionPool.size() + "]");
        }
        return connection;
    }

    public static void releaseConnection(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed() && connectionPool.size() < MAX_POOL_SIZE) {
            connectionPool.offer(connection);
            LOGGER.info("Client exhausted: " + Thread.currentThread().getName() +
                    " [Available connections: " + connectionPool.size() + "]");
        } else {
            try {
                connection.close();
                LOGGER.info("Closed excess connection: " + Thread.currentThread().getName() +
                        " [Available connections: " + connectionPool.size() + "]");
            } catch (SQLException e) {
                LOGGER.error("Failed to close the excess database connection.", e);
            }
        }
    }
}





