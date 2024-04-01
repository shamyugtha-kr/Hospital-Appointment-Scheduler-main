// RegisterController.java
package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Dregistercontroller {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    public void DregistertoLogin(ActionEvent event) throws IOException {
        // Perform the registration process
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        // Print values to console for debugging
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

        // Call the method in your database class to insert data
        DoctorDatabase database = new DoctorDatabase();
        int rowsAffected = database.insert(username, password, email);
        System.out.println(rowsAffected);
        if (rowsAffected > 0) {
    
            System.out.println("Registration successful!");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Registration Successful!");
            alert.showAndWait();
            // Navigate to the login page
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("MainDoctor.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Doctor Register");
            stage.show();
        } else {
            System.out.println("Registration failed.");
            // You can add error handling or display a message to the user if registration fails
        }
    }
}
