package application;

import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;

public class PatientprofileController {

    @FXML
    private Button profile;

    @FXML
    private Button appointment;

    @FXML
    private Button record;

    @FXML
    private Button home;

    @FXML
    private Button logout;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField genderField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField bloodGroupField;

    @FXML
    private TextField dobPicker;

    @FXML
    private TextField emailField;

    @FXML
    private TextArea allergiesField;

    @FXML
    private TextArea medicationsField;

    @FXML
    private TextArea medicalHistoryField;

    @FXML
    private TextField heightField;

    @FXML
    private TextField weightField;

    @FXML
    private TextArea surgeriesField;

    @FXML
    private TextArea conditionsField;

    @FXML
    private TextField emergencyContactNameField;

    @FXML
    private TextField emergencyContactNumberField;

    @FXML
    private TextField relationshipField;

    @FXML
    private TextField pharmacyLocationField;

    @FXML
    private TextField pharmacyNameField;

    @FXML
    private TextArea addressArea;

    private patientprofiledatabase database;

    @FXML
    public void initialize() {
        this.database = new patientprofiledatabase();
        // You can perform additional initialization tasks here
    }

    @FXML
    private void handleSaveButton() throws SQLException {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String age = ageField.getText();
        String gender = genderField.getText();
        String phoneNumber = phoneNumberField.getText();
        String bloodGroup = bloodGroupField.getText();
        String dob = dobPicker.getValue() != null ? dobPicker.getValue().toString() : "";
        String email = emailField.getText();
        String allergies = allergiesField.getText();
        String medications = medicationsField.getText();
        String medicalHistory = medicalHistoryField.getText();
        String height = heightField.getText();
        String weight = weightField.getText();
        String surgeries = surgeriesField.getText();
        String conditions = conditionsField.getText();
        String emergencyContactName = emergencyContactNameField.getText();
        String emergencyContactNumber = emergencyContactNumberField.getText();
        String relationship = relationshipField.getText();
        String pharmacyLocation = pharmacyLocationField.getText();
        String pharmacyName = pharmacyNameField.getText();
        String address = addressArea.getText();

        String sql = "INSERT INTO patientprofiledatabase(firstname, lastname, age, gender, phone_number, blood_group, dob, email, allergies, medications, medical_history, height, weight, surgeries, conditions, emergency_contact_name, emergency_contact_number, relationship, pharmacy_location, pharmacy_name, address) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        int rowsAffected = database.insertpatient(
                firstName, lastName, age, gender, phoneNumber, bloodGroup, dob, email, allergies,
                medications, medicalHistory, height, weight, surgeries, conditions,
                emergencyContactName, emergencyContactNumber, relationship, pharmacyLocation,
                pharmacyName, address, sql);

        if (rowsAffected > 0) {
            System.out.println("Data saved successfully!");
            // You can update UI or show a success message here
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Data Saved Successfully");
            alert.showAndWait();
        } else {
            System.out.println("Error saving data!");
            // Handle the case where data could not be saved
        }
    }

    @FXML
    public void redirectToProfile() throws IOException {
        redirectToPage("PatientMainProfile.fxml", "Profile");
    }

    @FXML
    public void redirectToAppointment() throws IOException {
        redirectToPage("PatientAppoinment.fxml", "Appointment");
    }

    @FXML
    public void redirectToRecord() throws IOException {
        redirectToPage("PatientRecord.fxml", "Record");
    }

    @FXML
    public void redirectToHome() throws IOException {
        redirectToPage("patient.fxml", "Home");
    }

    @FXML
    public void redirectTologout() throws IOException {
        redirectToPage("Home.fxml", "Logout");
    }

    private void redirectToPage(String page, String title) throws IOException {
        Stage stage = (Stage) profile.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(page));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }
}
