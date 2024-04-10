package pediatricDoctorOffice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PediatricView {
    public static final int WIDTH = 700, HEIGHT = 450;
    private String patientDataFilePath; //patient's data file path

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
            String formattedDOB = birthDateField.getText().replaceAll("[^\\d]", "");
            patientDataFilePath = "src/PatientData/" + firstNameField.getText() + lastNameField.getText() + formattedDOB + "_Data";

            // Check if the file exists before attempting to load data
            if (Files.exists(Paths.get(patientDataFilePath))) {
                // Logic to load and display patient data can be added here
            } else {
                // Handle rror case when no patient is found
                System.out.println("No patient record found. Please check the entered information.");
                // Display an error
            }
        });

        inputFields.getChildren().addAll(firstNameField, lastNameField, birthDateField, searchButton);

        /*-----RIGHT SIDE - VISITS & NURSE NOTES--------------------------------------------------------------------------*/

        VBox rightSideInfo = new VBox(10);
        rightSideInfo.setAlignment(Pos.TOP_CENTER);
        rightSideInfo.setPadding(new Insets(10));

        TextArea visitsArea = new TextArea();
        visitsArea.setPromptText("Visits");
        visitsArea.setEditable(false); // If you want to make it non-editable

        TextArea nurseNotesArea = new TextArea();
        nurseNotesArea.setPromptText("Nurse Notes from the most recent view");
      
        nurseNotesArea.setEditable(false);

        rightSideInfo.getChildren().addAll(visitsArea, nurseNotesArea);

        root.setTop(titleLabel);
        root.setLeft(inputFields);
        root.setRight(rightSideInfo);

        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        BorderPane.setAlignment(inputFields, Pos.TOP_CENTER);
        BorderPane.setAlignment(rightSideInfo, Pos.TOP_CENTER);
        
        /*-----PRESCRIPTION FIELD AND BUTTON--------------------------------------------------------------------------*/

        TextField prescriptionField = new TextField();
        prescriptionField.setPromptText("Enter prescription");

        Button prescribeButton = new Button("Prescribe");
        prescribeButton.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        prescribeButton.setOnAction(event -> {
        	String prescription = prescriptionField.getText();
        	
            // Append prescription to patients data file
            try {
            	
                // Make sure patient data file path has been set
                if (patientDataFilePath == null || patientDataFilePath.isEmpty()) {
                    System.out.println("No patient selected. Please use the search function.");
                    return;
                }
                
                // Read existing content and append new prescription data if doctor prescribes
                String existingContent = new String(Files.readAllBytes(Paths.get(patientDataFilePath)));
                String newContent = existingContent.trim() + "," + prescription + "\n"; // Add the prescription at the end and start a new line to indicate end of visit
                
                // Write back to the specified file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(patientDataFilePath))) {
                    writer.write(newContent);
                }
                prescriptionField.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        root.setTop(titleLabel);
        root.setLeft(inputFields);
        root.setRight(rightSideInfo);

        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        BorderPane.setAlignment(inputFields, Pos.TOP_CENTER);
        BorderPane.setAlignment(rightSideInfo, Pos.TOP_CENTER);

        stage.setScene(scene);
        stage.show();
    }
}
