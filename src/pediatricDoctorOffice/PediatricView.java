package pediatricDoctorOffice;

//Imports
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// PediatricView class
public class PediatricView {
    public static final int WIDTH = 700, HEIGHT = 450; // Window dimensions.
    boolean patientExists = false;// Flag to check if the patient exists in the database
    String pID;

    // Method to start the application
    public void start(Stage stage) {
        // Window setup
        stage.setTitle("Patient Information");
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        // Title setup
        Label titleLabel = new Label("Pediatric Doctor's View");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setPadding(new Insets(10, 0, 10, 0));
        BorderPane.setAlignment(titleLabel, Pos.CENTER);

        // Top section setup
        VBox topSection = new VBox(10);
        topSection.setAlignment(Pos.CENTER);
        topSection.setPadding(new Insets(10));

        // Information TextFields
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        TextField birthDateField = new TextField();
        birthDateField.setPromptText("Date of Birth");

        // Buttons
        Button searchButton = new Button("Search");
        searchButton.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        Button sendMessageButton = new Button("Send Message");
        sendMessageButton.setFont(Font.font("Arial", FontWeight.NORMAL, 15));   
        HBox searchAndMessageButtons = new HBox(10, searchButton, sendMessageButton);
        searchAndMessageButtons.setAlignment(Pos.CENTER);
        
        topSection.getChildren().addAll(titleLabel, firstNameField, lastNameField, birthDateField, searchAndMessageButtons);
        
         // Patient info display setup
        TextArea patientInfoArea = new TextArea();
        patientInfoArea.setPromptText("Patient Information...");
        patientInfoArea.setEditable(false);

        // Prescription section setup
        VBox bottomSection = new VBox(10);
        bottomSection.setAlignment(Pos.CENTER);
        bottomSection.setPadding(new Insets(10));
        TextField prescriptionField = new TextField();
        prescriptionField.setPromptText("Enter prescription");

        // Prescription Button
        Button prescribeButton = new Button("Prescribe");
        prescribeButton.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        bottomSection.getChildren().addAll(prescriptionField, prescribeButton);

         // Send message action
        sendMessageButton.setOnAction(e -> NewMessageWindow.display());
        
        // Search patient action
        searchButton.setOnAction(event -> {
        	if (firstNameField.getText().trim().isEmpty() || lastNameField.getText().trim().isEmpty() || birthDateField.getText().trim().isEmpty()) {
                showErrorDialog("Fields must not be empty");
                return;
            }

            // Construct patient ID
            String name = firstNameField.getText() + lastNameField.getText();
            String bday = birthDateField.getText();
            
            pID = name + bday;
            pID = pID.replaceAll(" ", "");
            pID = pID.replaceAll("/", "");
            
            // Check patient existence
            patientExists = searchPatient(pID);
            
            // Populate patient information
            if(patientExists) {      
                patientInfoArea.clear();
                String fp = "src/PatientData/" + pID + "_data";        
                try {
                    FileReader reader  = new FileReader(fp);
                    BufferedReader r = new BufferedReader(reader);
                    String line;
                    // Append each line to TextArea
                    while((line = r.readLine()) != null) {
                        line = reformat(line);
                        patientInfoArea.appendText(line + "\n");
                    }       
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // Prescribe action
        prescribeButton.setOnAction(event -> {
            String prescription = prescriptionField.getText();
             // Append prescription to patient file
            if(patientExists) {
                try {
                    FileWriter writer = new FileWriter("src/PatientData/" + pID + "_data", true);    
                    writer.write(",Prescription: " + prescription);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                searchButton.fire();// Refresh patient data
            }
        });

        // Set layout sections
        root.setTop(topSection);
        root.setCenter(patientInfoArea);
        root.setBottom(bottomSection);

        stage.setScene(scene);
        stage.show();
    }

    // Searches for patient data file
    private Boolean searchPatient(String patientID) {
        String directoryPath = "src/PatientData";  // Data directory
        
        // Create a File object representing the directory
        File directory = new File(directoryPath);
        
        // Verify that the specified path exists and is a directory
        if (directory.exists() && directory.isDirectory()) {
            // Get a list of files in the directory
            File[] files = directory.listFiles();
            
            // Iterate through the files
            for (File file : files) {
                if (file.isFile() && file.getName().startsWith(patientID)) {
                    return true; // Patient found
                }
            }
        }
        return false; // Patient not found
    }

    // Error dialog display
    private void showErrorDialog(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Reformats data line for display
    private String reformat(String line) {
        String[] input = line.split(",");
        String[] titles = {"Visit Date: ", "Height: ", "Temperature: ", "Age: ", "Blood Pressure: ", "Nurse Notes: ", ""};
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < input.length; i++) {
            if(i < titles.length) {
                if(i == input.length - 1 && input[i].isEmpty()) {
                    output.append(titles[i]).append(" ");
                } else {
                    output.append(titles[i]).append(input[i]).append(" ");
                }
            }
        }
        return output.toString();
    }
}
