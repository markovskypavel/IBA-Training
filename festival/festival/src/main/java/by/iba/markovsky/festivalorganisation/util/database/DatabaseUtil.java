package by.iba.markovsky.festivalorganisation.util.database;

import by.iba.markovsky.festivalorganisation.configuration.DatabaseConfiguration;
import by.iba.markovsky.festivalorganisation.exception.RepositoryException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseUtil {

    private static DatabaseUtil database = new DatabaseUtil();
    private static Connection connection;
    static {
        try {
            String url = DatabaseConfiguration.getProperty("hibernate.connection.url");
            String username = DatabaseConfiguration.getProperty("hibernate.connection.username");
            String password = DatabaseConfiguration.getProperty("hibernate.connection.password");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException exp) {
            throw new ExceptionInInitializerError(exp.getMessage());
        }
    }

    private DatabaseUtil() {
    }

    public static DatabaseUtil getInstance() {
        return database;
    }
    public static Connection getConnection() {
        return connection;
    }
    public static void closeConnection() throws RepositoryException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }
    public static PreparedStatement getStatement(String SQL) {
        try {
            return connection.prepareStatement(SQL);
        } catch (SQLException exp) {
            return null;
        }
    }

}
