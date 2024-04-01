package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import application.patientprofiledatabase;
public class patientprofiledatabase {

  private static final Logger logger = Logger.getLogger(patientprofiledatabase.class.getName());
  private Connection connection;

  public patientprofiledatabase() {
    establishConnection();
  }

  private void establishConnection() {
    String jdbcUrl = "jdbc:mysql://localhost:3306/javaproject1"; // Replace with your database details
    String username = "root"; // 
    String password = "root"; // Replace with your database password

    try {
      this.connection = DriverManager.getConnection(jdbcUrl, username, password);
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "Error during database connection", e);
    }
  }

  public Connection getConnection() {
    if (connection == null) {
      establishConnection();
    }
    return connection;
  }

  public int insertpatient(
      String firstName,
      String lastName,
      String age,
      String gender,
      String phoneNumber,
      String bloodGroup,
      String dob,
      String email,
      String allergies,
      String medications,
      String medicalHistory,
      String height,
      String weight,
      String surgeries,
      String conditions,
      String emergencyContactName,
      String emergencyContactNumber,
      String relationship,
      String pharmacyLocation,
      String pharmacyName,
      String address,
      String sql) {
	  
	  
      try (Connection connection = getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

      // Set prepared statement values based on PatientProfile data
      preparedStatement.setString(1, firstName);
      preparedStatement.setString(2, lastName);
      preparedStatement.setString(3, age);
      preparedStatement.setString(4, gender);
      preparedStatement.setString(5, phoneNumber);
      preparedStatement.setString(6, bloodGroup);
      preparedStatement.setString(7, dob);
      preparedStatement.setString(8, email);
      preparedStatement.setString(9, allergies);
      preparedStatement.setString(10, medications);
      preparedStatement.setString(11, medicalHistory);
      preparedStatement.setString(12, height);
      preparedStatement.setString(13, weight);
      preparedStatement.setString(14, surgeries);
      preparedStatement.setString(15, conditions);
      preparedStatement.setString(16, emergencyContactName);
      preparedStatement.setString(17, emergencyContactNumber);
      preparedStatement.setString(18, relationship);
      preparedStatement.setString(19, pharmacyLocation);
      preparedStatement.setString(20, pharmacyName);
      preparedStatement.setString(21, address);

      int rowsAffected = preparedStatement.executeUpdate();
      return rowsAffected;
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "Error saving patient data", e);
    }
    return -1;
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
