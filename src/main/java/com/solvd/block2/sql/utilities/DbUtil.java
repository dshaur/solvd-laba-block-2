package com.solvd.block2.sql.utilities;

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

    static {
        connectionPool = new ArrayBlockingQueue<>(MAX_CONNECTIONS);
        initializeConnectionPool();
    }

    private static void initializeConnectionPool() {
        Properties properties = loadProperties();

        if (properties != null) {
            String dbUrl = properties.getProperty("jdbc.url");
            String dbUser = properties.getProperty("jdbc.username");
            String dbPassword = properties.getProperty("jdbc.password");

            try {
                for (int i = 0; i < MAX_CONNECTIONS; i++) {
                    Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                    connectionPool.offer(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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

    public static Connection getConnection() throws InterruptedException {
        return connectionPool.take();
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



