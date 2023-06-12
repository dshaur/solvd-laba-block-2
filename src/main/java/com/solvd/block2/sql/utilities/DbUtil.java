package com.solvd.block2.sql.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {
    private static final String PROPERTIES_FILE = "db.properties";

    private static Connection createConnection() {
        Properties properties = loadProperties();
        if (properties != null) {
            String jdbcUrl = properties.getProperty("jdbc.url");
            String username = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");

            try {
                return DriverManager.getConnection(jdbcUrl, username, password);
            } catch (SQLException e) {
                throw new RuntimeException("Failed to create a new database connection.", e);
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

    public static Connection getConnection() {
        return createConnection();
    }

}

