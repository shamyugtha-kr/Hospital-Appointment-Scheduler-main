package application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class data {

    private final StringProperty firstname;
    private final StringProperty lastname;
    private final StringProperty age;
    private final StringProperty appoinmenttype;
    private final StringProperty description;
    private final StringProperty status;
    private final StringProperty mobileno;
    private final StringProperty date;

    public data( String firstname, String lastname, String age,
                String appoinmenttype, String description, String status, String mobileno, String date) {
       
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.age = new SimpleStringProperty(age);
        this.appoinmenttype = new SimpleStringProperty(appoinmenttype);
        this.description = new SimpleStringProperty(description);
        this.status = new SimpleStringProperty(status);
        this.mobileno = new SimpleStringProperty(mobileno);
        this.date = new SimpleStringProperty(date);
    }

    public StringProperty firstnameProperty() {
        return this.firstname;
    }

    public String getFirstname() {
        return this.firstnameProperty().get();
    }

    public void setFirstname(String firstname) {
        this.firstnameProperty().set(firstname);
    }

    public StringProperty lastnameProperty() {
        return this.lastname;
    }

    public String getLastname() {
        return this.lastnameProperty().get();
    }

    public void setLastname(String lastname) {
        this.lastnameProperty().set(lastname);
    }

    public StringProperty ageProperty() {
        return this.age;
    }

    public String getAge() {
        return this.ageProperty().get();
    }

    public void setAge(String age) {
        this.ageProperty().set(age);
    }

    public StringProperty appoinmenttypeProperty() {
        return this.appoinmenttype;
    }

    public String getAppoinmenttype() {
        return this.appoinmenttypeProperty().get();
    }

    public void setAppoinmenttype(String appoinmenttype) {
        this.appoinmenttypeProperty().set(appoinmenttype);
    }

    public StringProperty descriptionProperty() {
        return this.description;
    }

    public String getDescription() {
        return this.descriptionProperty().get();
    }

    public void setDescription(String description) {
        this.descriptionProperty().set(description);
    }

    public StringProperty statusProperty() {
        return this.status;
    }

    public String getStatus() {
        return this.statusProperty().get();
    }

    public void setStatus(String status) {
        this.statusProperty().set(status);
    }

    public StringProperty mobilenoProperty() {
        return this.mobileno;
    }

    public String getMobileno() {
        return this.mobilenoProperty().get();
    }

    public void setMobileno(String mobileno) {
        this.mobilenoProperty().set(mobileno);
    }

    public StringProperty dateProperty() {
        return this.date;
    }

    public String getDate() {
        return this.dateProperty().get();
    }

    public void setDate(String date) {
        this.dateProperty().set(date);
    }
}
