//DOCTOR PORTAL

package pediatricDoctorOffice;

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

public class PediatricView {
    public static final int WIDTH = 700, HEIGHT = 450;
    boolean patientExists = false;
    String pID;
    public void start(Stage stage) {
        stage.setTitle("Patient Information");

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        /*-----TITLE--------------------------------------------------------------------------*/
        
        Label titleLabel = new Label("Pediatric Doctor's View");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setPadding(new Insets(10, 0, 10, 0));
        BorderPane.setAlignment(titleLabel, Pos.CENTER);

        /*-----TOP SECTION--------------------------------------------------------------------------*/
        //This is responsible for creating the area where the pediatric doctor can search for the patient's information in the database and
        //show information regarding the patient

        VBox topSection = new VBox(10);
        topSection.setPadding(new Insets(10));
        
        Button logoutButton = new Button("Logout");
        logoutButton.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        topSection.getChildren().add(logoutButton);
        
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");

        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");

        TextField birthDateField = new TextField();
        birthDateField.setPromptText("Date of Birth");

        Button searchButton = new Button("Search");
        searchButton.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        
        Button sendMessageButton = new Button("Send Message");
        sendMessageButton.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        
        HBox searchAndMessageButtons = new HBox(10, searchButton, sendMessageButton);
        searchAndMessageButtons.setAlignment(Pos.CENTER);
        
        topSection.getChildren().addAll(titleLabel, firstNameField, lastNameField, birthDateField, searchAndMessageButtons);
        
        /*-----PATIENT INFORMATION--------------------------------------------------------------------------*/
        //Big text box that is used to display the Patient's information
        
        TextArea patientInfoArea = new TextArea();
        patientInfoArea.setPromptText("Patient Information...");
        patientInfoArea.setEditable(false);

        /*-----PRESCRIPTION FIELD AND BUTTON--------------------------------------------------------------------------*/
        //Prescription textfield at bottom to allow doctors to enter in the prescription and prescribe them a medication which will
        //save to the database
        
        VBox bottomSection = new VBox(10);
        bottomSection.setAlignment(Pos.CENTER);
        bottomSection.setPadding(new Insets(10));

        TextField prescriptionField = new TextField();
        prescriptionField.setPromptText("Enter prescription");

        Button prescribeButton = new Button("Prescribe");
        prescribeButton.setFont(Font.font("Arial", FontWeight.NORMAL, 15));

        bottomSection.getChildren().addAll(prescriptionField, prescribeButton);
        
        /*-----EVENT HANDLERS--------------------------------------------------------------------------*/
        //When the logout button is pressed take the user back to the login page
        
        logoutButton.setOnAction(event -> {
            stage.close();
            PediatricDoctorOffice doctorOffice = new PediatricDoctorOffice();
            try {
                doctorOffice.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        //Opens a send message window
        sendMessageButton.setOnAction(e -> NewMessageWindow.display());
        
        //Searching logic for the patient using the inputs at the top of the screen
        searchButton.setOnAction(event -> {
        	if (firstNameField.getText().trim().isEmpty() || lastNameField.getText().trim().isEmpty() || birthDateField.getText().trim().isEmpty()) {
                showErrorDialog("Fields must not be empty");
                return;
            }
        	
            String name = firstNameField.getText() + lastNameField.getText();
            String bday = birthDateField.getText();
            
            pID = name + bday;
            pID = pID.replaceAll(" ", "");
            pID = pID.replaceAll("/", "");
            
            //does patient exist
            //gets here properly with correct pID
            patientExists = searchPatient(pID);
            
            //populate the visits and nurse info boxes
            if(patientExists) {      
                patientInfoArea.clear();
                String fp = "src/PatientData/" + pID + "_data";
                
                try {
                    FileReader reader  = new FileReader(fp);
                    BufferedReader r = new BufferedReader(reader);
                    String line;
                    
                    while((line = r.readLine()) != null) {
                        line = reformat(line);
                        patientInfoArea.appendText(line + "\n");
                    }       
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        });
        
        //Prescribe button will append information to the patient's data file
        prescribeButton.setOnAction(event -> {
            String prescription = prescriptionField.getText();
            //Append prescription to patients data file
            if(patientExists) {
                try {
                    FileWriter writer = new FileWriter("src/PatientData/" + pID + "_data", true);    
                    writer.write(",Prescription: " + prescription);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                searchButton.fire();
            }
        });

        root.setTop(topSection);
        root.setCenter(patientInfoArea);
        root.setBottom(bottomSection);

        stage.setScene(scene);
        stage.show();
    }
    
    //Search Patient will locate the information of the patient in the database given inputs
    private Boolean searchPatient(String patientID) {
        // Specify the directory containing the text files
        String directoryPath = "src/PatientData";
        
        // Create a File object representing the directory
        File directory = new File(directoryPath);
        
        // Verify that the specified path exists and is a directory
        if (directory.exists() && directory.isDirectory()) {
            // Get a list of files in the directory
            File[] files = directory.listFiles();
            
            // Iterate through the files
            for (File file : files) {
                if (file.isFile() && file.getName().startsWith(patientID)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    //Used to create an error if the fields aren't filled out    
    private void showErrorDialog(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    //This will reformat the information in the file and display it to the doctor given inputs
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
