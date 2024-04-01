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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.beans.property.ReadOnlyStringWrapper;

import java.io.IOException;
import java.net.URL;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class PRecordController implements Initializable {

    @FXML
    private TableColumn<data, String> idColumn;

    @FXML
    private TableColumn<data, String> firstNameColumn;

    @FXML
    private TableColumn<data, String> lastNameColumn;

    @FXML
    private TableColumn<data, String> ageColumn;

    @FXML
    private TableColumn<data, String> appoinmentTypeColumn;

    @FXML
    private TableColumn<data, String> reasonColumn;

    @FXML
    private TableColumn<data, String> phoneNumberColumn;

    @FXML
    private TableColumn<data, String> appoinmentDateColumn;

    @FXML
    private TableColumn<data, String> statusColumn;

    @FXML
    private Button profile;

    @FXML
    private Button appoinment;

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
        
        firstNameColumn.setCellValueFactory(f->f.getValue().firstnameProperty());
        lastNameColumn.setCellValueFactory(f->f.getValue().lastnameProperty());
        ageColumn.setCellValueFactory(f->f.getValue().ageProperty());
        appoinmentTypeColumn.setCellValueFactory(f->f.getValue().appoinmenttypeProperty());
        reasonColumn.setCellValueFactory(f->f.getValue().descriptionProperty());
        phoneNumberColumn.setCellValueFactory(f->f.getValue().mobilenoProperty());
        appoinmentDateColumn.setCellValueFactory(f->f.getValue().dateProperty());
        statusColumn.setCellValueFactory(f->f.getValue().statusProperty());

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
                data dataObject = new data(
                  
                        result.getString("firstname"),
                        result.getString("lastname"),
                        result.getString("age"),
                        result.getString("appoinmenttype"),
                        result.getString("description"),
                        result.getString("status"),
                        result.getString("mobileno"),
                        result.getString("date")
                );
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
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
    


    @FXML
    public void initialize() {
        // You can keep any common initialization code here
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
    public void redirectTopatientpage() throws IOException {
        redirectToPage("patient.fxml", "About");
    }

    @FXML
    public void redirectTologin() throws IOException {
        redirectToPage("Home.fxml", "Sign Up");
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
