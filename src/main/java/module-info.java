module com.example.oop_project_phase2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.oop_project_phase2 to javafx.fxml;
    exports com.example.oop_project_phase2;
    exports com.example.oop_project_phase2.card;
    opens com.example.oop_project_phase2.card to javafx.fxml;
    exports com.example.oop_project_phase2.UserManagement;
    opens com.example.oop_project_phase2.UserManagement to javafx.fxml;
    exports com.example.oop_project_phase2.Misc;
    opens com.example.oop_project_phase2.Misc to javafx.fxml;
    exports com.example.oop_project_phase2.Game;
    opens com.example.oop_project_phase2.Game to javafx.fxml;
}