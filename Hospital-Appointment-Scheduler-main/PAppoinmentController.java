package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.time.LocalDate;

public class PAppoinmentController {
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField ageField;

    @FXML
    private DatePicker appointmentDateField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField appointmentTypeField;

    @FXML
    private TextArea reasonField;

    @FXML
    private Button requestAppointmentButton;

    @FXML
    private Button profile;

    @FXML
    private Button appointment;

    @FXML
    private Button record;

    @FXML
    private Button home;

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
        // Optional: Handle the action for the Appointment button
    }

    @FXML
    public void redirectToRecord(ActionEvent event) throws IOException {
        redirectToPage("PatientRecord.fxml", "Records", event);
    }

    @FXML
    public void redirectToHome(ActionEvent event) throws IOException {
        redirectToPage("Home.fxml", "Home", event);
    }

    private void redirectToPage(String page, String title, ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(page));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

    @FXML
    public void requestAppointment(ActionEvent event) throws IOException {
        // Get values from the form
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String age = ageField.getText();
        
        LocalDate appointmentDate = appointmentDateField.getValue();
        if (appointmentDate == null) {
            // Handle the case where no date is selected
            showAlert("Error", "Please select an appointment date.");
            return; // exit the method
        }

        // Validate and format the appointment date string
        String formattedAppointmentDate = validateAndFormatDateString(appointmentDate.toString());

        String phoneNumber = phoneNumberField.getText();
        String appointmentType = appointmentTypeField.getText();
        String reason = reasonField.getText();

        // Print values to console for debugging
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Age: " + age);
        System.out.println("Appointment Date: " + formattedAppointmentDate);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Appointment Type: " + appointmentType);
        System.out.println("Reason: " + reason);

        // Call the method in your database class to insert data
        PApplicationDatabase database = new PApplicationDatabase();
        int rowsAffected = database.insertAppointment(firstName, lastName, age, appointmentType, reason, phoneNumber, formattedAppointmentDate, "Pending");

        if (rowsAffected > 0) {
            System.out.println("Appointment requested successfully!");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Appoinment Request Successful!");
            alert.showAndWait();
        }
    }
    // Helper method to validate and format the date string
    private String validateAndFormatDateString(String dateString) {
        // Add your validation logic here if needed
        return dateString + " 00:00:00"; // Assuming you want to set the time to midnight
    }

    // Helper method to show an alert
    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}