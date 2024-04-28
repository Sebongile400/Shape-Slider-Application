module com.example.shapesliderapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.shapesliderapp to javafx.fxml;
    exports com.example.shapesliderapp;
}