package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBase2 {
    private static final Logger logger = Logger.getLogger(DataBase2.class.getName());
    private Connection connection;

    public DataBase2() {
        establishConnection();
    }
    
    private void establishConnection() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/javaproject1";
        String username = "root";
        String password = "root";

        try {
            this.connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error during database connection", e);
        }
    }

    // Get the current database connection
    public Connection getConnection() {
        if (connection == null) {
            establishConnection();
        }
        return connection;
    }

    public int insert(String user, String pass, String email) {
        String sql = "INSERT INTO patients (username, email, password) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, pass);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error during insertion", e);
        }
        return -1;
    }

    public String getpass(String specificUsername) {
        String sql = "SELECT password FROM patients WHERE username = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, specificUsername);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("password");
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving password", e);
        }
        return null;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error closing database connection", e);
        }
    }
}
