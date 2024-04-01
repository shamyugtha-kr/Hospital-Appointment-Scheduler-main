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

public class MainDoctorController {

    @FXML
    private Button home;

    @FXML
    private Button profile;

    @FXML
    private Button appointment;

    @FXML
    private Button logout;

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
        redirectToPage("doctorProfile.fxml", "Profile", event);
    }

    @FXML
    public void redirectToAppointment(ActionEvent event) throws IOException {
        redirectToPage("doctorappoinment.fxml", "Appointment", event);
    }

    @FXML
    public void redirectTologout(ActionEvent event) throws IOException {
        redirectToPage("Home.fxml", "Login", event);
    }

    private void redirectToPage(String page, String title, ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(page));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
