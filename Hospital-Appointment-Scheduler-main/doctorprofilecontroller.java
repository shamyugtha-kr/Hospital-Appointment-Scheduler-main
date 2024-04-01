package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class doctorprofilecontroller {

    @FXML
    private Button home;

    @FXML
    private Button profile;

    @FXML
    private Button appoinment;

    @FXML
    private Button logout;

    @FXML
    private Button saveButton;

    @FXML
    public void initialize() {
        // You can perform any initialization here
    }

    @FXML
    public void redirectToHome(ActionEvent event) throws IOException {
        redirectToPage("MainDoctor.fxml", "Home", event);
    }

    @FXML
    public void redirectToProfile(ActionEvent event) throws IOException {
        // This is the current page, no need to redirect to itself
    }

    @FXML
    public void redirectToAppointment(ActionEvent event) throws IOException {
        redirectToPage("DoctorAppoinment.fxml", "Appointment", event);
    }

    @FXML
    public void redirectTologout(ActionEvent event) throws IOException {
        redirectToPage("Home.fxml", "Login", event);
    }

    @FXML
    public void saveProfile(ActionEvent event) {
        // Implement logic to save the profile information
        System.out.println("Profile Saved!");
    }

    private void redirectToPage(String page, String title, ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(page));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }
}
