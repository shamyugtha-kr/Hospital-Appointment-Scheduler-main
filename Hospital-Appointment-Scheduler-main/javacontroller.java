package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class javacontroller {

	@FXML
    private Button profile;

    @FXML
    private Button appoinment;

    @FXML
    private Button record;

    @FXML
    private Button notification;

    @FXML
    private Button about;

    @FXML
    private Button logout;

    @FXML
    public void initialize() {
        // You can perform any initialization here
    }

    @FXML
    public void redirectToProfile(ActionEvent event) throws IOException {
        redirectToPage("Patientprofile.fxml", "Profile", event);
    }

    @FXML
    public void redirectToAppointment(ActionEvent event) throws IOException {
        redirectToPage("PatientAppoinment.fxml", "Appointment", event);
    }

    @FXML
    public void redirectToRecord(ActionEvent event) throws IOException {
        redirectToPage("PatientRecord.fxml", "Record", event);
    }

    @FXML
    public void redirectToNotification(ActionEvent event) throws IOException {
        redirectToPage("patientnotification.fxml", "Notification", event);
    }

    @FXML
    public void redirectToAbout(ActionEvent event) throws IOException {
        redirectToPage("PatientAbout.fxml", "About", event);
    }
    
    @FXML
    public void redirectTologout(ActionEvent event) throws IOException {
        redirectToPage("Home.fxml", "Sign Up", event);
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
