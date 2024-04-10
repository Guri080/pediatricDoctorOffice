package pediatricDoctorOffice;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PediatricView extends Application {
    public static final int WIDTH = 900, HEIGHT = 450;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Patient Information");

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        /*-----TITLE--------------------------------------------------------------------------*/
        
        Label titleLabel = new Label("Pediatric Doctor's View");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setPadding(new Insets(10, 0, 10, 0));

        /*-----LEFT SIDE--------------------------------------------------------------------------*/
        
        VBox inputFields = new VBox(10);
        inputFields.setAlignment(Pos.TOP_CENTER);
        inputFields.setPadding(new Insets(10));

        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");

        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");

        TextField birthDateField = new TextField();
        birthDateField.setPromptText("Date of Birth");

        Button searchButton = new Button("Search");
        searchButton.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        searchButton.setMaxWidth(Double.MAX_VALUE);
        searchButton.setOnAction(event -> {
            // I will implement the search logic here later
            // For now, just setting example text
        });

        inputFields.getChildren().addAll(firstNameField, lastNameField, birthDateField, searchButton);

        /*-----PATIENT INFO DISPLAY--------------------------------------------------------------------------*/
        VBox patientInfo = new VBox(10);
        patientInfo.setAlignment(Pos.TOP_CENTER);
        patientInfo.setPadding(new Insets(10));
        
        Label patientInfoTitle = new Label("Patient Information");
        patientInfoTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        /*-----PATIENT INFO DISPLAY NON EDITABLE--------------------------------------------------------------------------*/
        
        TextField nameField = new TextField();
        nameField.setEditable(false);
        TextField birthField = new TextField();
        birthField.setEditable(false);
        TextArea additionalInfoArea = new TextArea();
        additionalInfoArea.setEditable(false);

        patientInfo.getChildren().addAll(patientInfoTitle, new Label("Immunization"), nameField, new Label("bla bla:"), birthField, new Label("Nurse's Notes:"), additionalInfoArea);

        /*-----DISPLAY--------------------------------------------------------------------------*/
        
        root.setTop(titleLabel);
        root.setLeft(inputFields);
        root.setCenter(patientInfo);

        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        BorderPane.setAlignment(inputFields, Pos.TOP_CENTER);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
