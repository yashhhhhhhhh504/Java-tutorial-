module com.example.assignment_2021504_ap {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.assignment_2021504_ap to javafx.fxml;
    exports com.example.assignment_2021504_ap;
}