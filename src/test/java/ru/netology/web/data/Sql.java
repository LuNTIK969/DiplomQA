package ru.netology.web.data;

import javax.sql.DataSource;
import lombok.SneakyThrows;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Sql {
    private static DataSource dataSource;

    private static DataSource createDataSource(String url, String username, String password) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    private static DataSource getDataSource() {
        if (dataSource == null) {
            String url = System.getProperty("db.url");
            String username = System.getProperty("db.user");
            String password = System.getProperty("db.password");
            dataSource = createDataSource(url, username, password);
        }
        return dataSource;
    }

    private static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    @SneakyThrows
    public static void cleanDb() {
        String[] deleteStatements = new String[] {
                "DELETE FROM credit_request_entity",
                "DELETE FROM order_entity",
                "DELETE FROM payment_entity"
        };
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(Arrays.toString(deleteStatements))) {
            for (String query : deleteStatements) {
                stmt.addBatch(query);
            }
            stmt.executeBatch();
        }
    }


    @SneakyThrows
    public static String getPaymentStatus() {
        String sql = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("status");
                }
            }
        }
        return null;
    }

    @SneakyThrows
    public static String getRequestStatus() {
        String sql = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("status");
                }
            }
        }
        return null;
    }
}