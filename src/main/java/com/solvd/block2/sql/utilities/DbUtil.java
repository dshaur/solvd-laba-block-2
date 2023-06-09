package com.solvd.block2.sql.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DbUtil {
    private static final String PROPERTIES_FILE = "db.properties";
    private static final int MAX_CONNECTIONS = 100;

    private static List<Connection> connectionPool = new ArrayList<>();

    static {
        initializeConnectionPool();
    }

    private static void initializeConnectionPool() {
        Properties properties = loadProperties();
        if (properties != null) {
            String jdbcUrl = properties.getProperty("jdbc.url");
            String username = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                for (int i = 0; i < MAX_CONNECTIONS; i++) {
                    Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
                    connectionPool.add(connection);
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("Failed to load DB properties.");
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

    public static Connection getConnection() throws SQLException {
        if (connectionPool.isEmpty()) {
            throw new SQLException("Connection pool exhausted!");
        }
        return connectionPool.remove(connectionPool.size() - 1);
    }

    public static void releaseConnection(Connection connection) {
        connectionPool.add(connection);
    }
}
