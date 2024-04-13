module com.alessandra.leleabarrotes {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.alessandra.leleabarrotes to javafx.fxml;
    exports com.alessandra.leleabarrotes;
    exports com.alessandra.leleabarrotes.controllers;
    opens com.alessandra.leleabarrotes.controllers to javafx.fxml;
}