package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import javafx.scene.control.Button;
import java.io.IOException;

public class Homecontroller {
	
    @FXML
    private TextField username; // Make sure this is of type javafx.scene.control.TextField
    @FXML
    private Button loginButton;
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void patienttologin(ActionEvent event) throws IOException {
    		Stage stage = (Stage) loginButton.getScene().getWindow();
    		stage.close();
    		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
    		Scene scene = new Scene(root);
    		stage.setScene(scene);
    		stage.setTitle("Sign Up");
    		stage.show();
    	}
    public void doctortologin(ActionEvent event) throws IOException {
		Stage stage = (Stage) loginButton.getScene().getWindow();
		stage.close();
		Parent root = FXMLLoader.load(getClass().getResource("Doctorlogin.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Sign Up");
		stage.show();
    }
	public void admintologin(ActionEvent event) throws IOException {
    		Stage stage = (Stage) loginButton.getScene().getWindow();
    		stage.close();
    		Parent root = FXMLLoader.load(getClass().getResource("adminlogin.fxml"));
    		Scene scene = new Scene(root);
    		stage.setScene(scene);
    		stage.setTitle("Sign Up");
    		stage.show();
    	}
	}
