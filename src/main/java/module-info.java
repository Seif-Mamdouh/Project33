module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.Project33 to javafx.fxml;
    exports com.example.Project33;
}