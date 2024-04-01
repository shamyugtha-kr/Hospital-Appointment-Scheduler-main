package application;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

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

public class doctorcontroller{

	DoctorDatabase Db = new DoctorDatabase();

	@FXML
	private PasswordField password;

	@FXML
	private TextField user;

	@FXML
	private Button DloginButton;
    @FXML
    public void doctorlogintoregister(ActionEvent event) throws IOException {
    		Stage stage = (Stage) DloginButton.getScene().getWindow();
    		stage.close();
    		Parent root = FXMLLoader.load(getClass().getResource("doctorregister.fxml"));
    		Scene scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	}
    @FXML
    public void validate(ActionEvent event) {
    	String name = user.getText();
        String pass = password.getText();
        System.out.println(name);
        System.out.println(pass);

        DoctorDatabase database = new DoctorDatabase(); // Assuming you have a Database class

        if (database.getConnection() != null) {
            String selectQuery = "SELECT * FROM doctors WHERE username = ? AND password = ?";

            try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(selectQuery)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, pass);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    // User found and password matched
                    System.out.println("Login successful!");
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();
                    // Redirect to patient.fxml
                    loadPatientFXML();
                } else {
                    System.out.println("Incorrect username or password");
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid Credentials!");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                database.closeConnection();
            }
        } else {
            System.out.println("Not connected to the database");
        }
    }
    
    // Load patient.fxml and display it
    public void loadPatientFXML() {
        try {
        	Stage stage = (Stage) DloginButton.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("MainDoctor.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	}
