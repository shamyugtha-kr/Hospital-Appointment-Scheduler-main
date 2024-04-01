// RegisterController.java
package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    public void registerAndGoToLogin(ActionEvent event) throws IOException {
        // Perform the registration process
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        // Print values to console for debugging
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

        // Call the method in your database class to insert data
        DataBase2 database = new DataBase2();
        int rowsAffected = database.insert(username, password, email);

        if (rowsAffected > 0) {
            System.out.println("Registration successful!");

            // Navigate to the login page
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("patient.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Sign Up");
            stage.show();
        } else {
            System.out.println("Registration failed.");
            // You can add error handling or display a message to the user if registration fails
        }
    }
}