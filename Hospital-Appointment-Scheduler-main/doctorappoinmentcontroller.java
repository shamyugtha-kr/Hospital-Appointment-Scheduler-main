package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

public class doctorappoinmentcontroller implements Initializable {

    @FXML
    private TableColumn<data, String> firstNameColumn;

    @FXML
    private TableColumn<data, String> lastNameColumn;

    @FXML
    private TableColumn<data, String> ageColumn;

    @FXML
    private TableColumn<data, String> appointmentTypeColumn;

    @FXML
    private TableColumn<data, String> reasonColumn;

    @FXML
    private TableColumn<data, String> phoneNumberColumn;

    @FXML
    private TableColumn<data, String> appointmentDateColumn;

    @FXML
    private TableColumn<data, String> statusColumn;

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
    private TableView<data> recordTable;

    private Connection connect = null;
    private PreparedStatement prepare = null;
    private ResultSet result = null;
    private PApplicationDatabase database = new PApplicationDatabase();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the table view columns
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstnameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastnameProperty());
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty());
        appointmentTypeColumn.setCellValueFactory(cellData -> cellData.getValue().appoinmenttypeProperty());
        reasonColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        phoneNumberColumn.setCellValueFactory(cellData -> cellData.getValue().mobilenoProperty());
        appointmentDateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        // Refresh the table view
        addPetsShowListData();
    }
    
    public void addPetsShowListData() {
        try {
            // Connect to the database
            connect = database.getConnection();

            // Prepare the select statement
            String selectData = "SELECT * FROM app";
            prepare = connect.prepareStatement(selectData);

            // Execute the query and store the results
            result = prepare.executeQuery();

            // Create a new ObservableList of data objects
            ObservableList<data> dataObservableList = FXCollections.observableArrayList();

            // Iterate through the result set and populate the data list
            while (result.next()) {
                data dataObject = new data(result.getString("firstname"), result.getString("lastname"),
                        result.getString("age"), result.getString("appoinmenttype"), result.getString("description"),
                        result.getString("status"), result.getString("mobileno"), result.getString("date"));
                dataObservableList.add(dataObject);
            }

            // Close the result set and statement
            result.close();
            prepare.close();

            // Update the table view with the data list
            recordTable.setItems(dataObservableList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    private void redirectToProfile() throws IOException {
        redirectToPage("DoctorProfile.fxml", "Profile");
    }

    @FXML
    private void redirectToAppointment() throws IOException {
        redirectToPage("DoctorAppointment.fxml", "Appointment");
    }

    @FXML
    private void redirectToHome() throws IOException {
        redirectToPage("MainDoctor.fxml", "About");
    }

    @FXML
    private void redirectTologin() throws IOException {
        redirectToPage("Home.fxml", "Sign Up");
    }

    @FXML
    private void acceptAppointment() {
        if (showConfirmationDialog("Accept Appointment", "Are you sure you want to accept this appointment?")) {
            updateStatus("accepted");
        }
    }

    @FXML
    private void declineAppointment() {
        if (showConfirmationDialog("Decline Appointment", "Are you sure you want to decline this appointment?")) {
            updateStatus("declined");
        }
    }

    private boolean showConfirmationDialog(String title, String content) {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle(title);
        confirmationDialog.setHeaderText(null);
        confirmationDialog.setContentText(content);

        Optional<ButtonType> result = confirmationDialog.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    private void updateStatus(String newStatus) {
        // Get the selected item from the table
        data selectedData = recordTable.getSelectionModel().getSelectedItem();

        if (selectedData != null) {
            try {
                // Connect to the database
                connect = database.getConnection();

                // Prepare the update statement for the "app" table
                String updateStatusQuery = "UPDATE app SET status = ? WHERE firstname = ? AND lastname = ? AND age = ? AND appoinmenttype = ?";
                prepare = connect.prepareStatement(updateStatusQuery);

                // Set parameters
                prepare.setString(1, newStatus);
                prepare.setString(2, selectedData.getFirstname());
                prepare.setString(3, selectedData.getLastname());
                prepare.setString(4, selectedData.getAge());
                prepare.setString(5, selectedData.getAppoinmenttype());

                // Execute the update
                prepare.executeUpdate();

                // Close the statement
                prepare.close();

                // Insert data into the "acceptedappointment" table
                String insertQuery = "INSERT INTO acceptedappointments (firstname, lastname, age, appoinmenttype, description, mobileno, date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                prepare = connect.prepareStatement(insertQuery);

                // Set parameters for insertion
                prepare.setString(1, selectedData.getFirstname());
                prepare.setString(2, selectedData.getLastname());
                prepare.setString(3, selectedData.getAge());
                prepare.setString(4, selectedData.getAppoinmenttype());
                prepare.setString(5, selectedData.getDescription());
                prepare.setString(6, selectedData.getMobileno());
                prepare.setString(7, selectedData.getDate());
                prepare.setString(8, newStatus); // Set the status to "accepted" or "declined"

                // Execute the insertion
                prepare.executeUpdate();

                // Close the statement
                prepare.close();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Response Successful");
                alert.showAndWait();
                // Refresh the table to reflect the changes
                addPetsShowListData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            showAlert("No Appointment Selected", "Please select an appointment before accepting or declining.");
        }
    }

    private void redirectToPage(String page, String title) throws IOException {
        // Assuming you have a reference to the current stage
        Stage stage = (Stage) profile.getScene().getWindow(); // You can use any control to get the current stage
        Parent root = FXMLLoader.load(getClass().getResource(page));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }
}
