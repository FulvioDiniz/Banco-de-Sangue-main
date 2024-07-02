module JavaFXApplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.sql;

    opens poov.controledoacaosangue to javafx.fxml;
    opens poov.controledoacaosangue.controller to javafx.fxml, javafx.graphics;
    opens poov.controledoacaosangue.dao to javafx.fxml, javafx.graphics;
    opens poov.controledoacaosangue.dao.core to javafx.fxml, javafx.graphics;
    opens poov.controledoacaosangue.model to javafx.base, javafx.fxml, javafx.graphics;
    exports poov.controledoacaosangue;
}