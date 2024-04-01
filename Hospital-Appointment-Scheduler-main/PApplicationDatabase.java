package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PApplicationDatabase {
    private static final Logger logger = Logger.getLogger(PApplicationDatabase.class.getName());
    private Connection connection;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/javaproject1";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public PApplicationDatabase() {
        establishConnection();
    }

    private void establishConnection() {
        try {
            this.connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
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

    public int insertAppointment(String firstname, String lastname, String age, String appointmenttype,
                                 String description,  String mobileno, String date, String status) {
        String sql = "INSERT INTO app (firstname, lastname, age, appoinmenttype, description, mobileno, date, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, age);
            preparedStatement.setString(4, appointmenttype);
            preparedStatement.setString(5, description);
            preparedStatement.setString(6, mobileno);
            preparedStatement.setString(7, date);
            preparedStatement.setString(8, status);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error during appointment insertion", e);
            // Consider throwing an exception or logging the error for better handling
            return -1;
        }
    }
}
