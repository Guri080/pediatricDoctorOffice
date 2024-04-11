package pediatricDoctorOffice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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
    //private String patientDataFilePath; //patient's data file path
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
        inputFields.getChildren().addAll(firstNameField, lastNameField, birthDateField, searchButton);

        /*-----RIGHT SIDE - VISITS & NURSE NOTES--------------------------------------------------------------------------*/

        VBox rightSideInfo = new VBox(10);
        rightSideInfo.setAlignment(Pos.TOP_CENTER);
        rightSideInfo.setPadding(new Insets(10));

        TextArea visitsArea = new TextArea();
        visitsArea.setPromptText("Visits");
        visitsArea.setEditable(false); 

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
        
        searchButton.setOnAction(event -> {
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
            	visitsArea.clear();
            	System.out.println("patient exists: " + pID);
            	String fp = "src\\PatientData\\" + pID + "_data";
            	
            	try{
            		FileReader reader  = new FileReader(fp);
            		BufferedReader r = new BufferedReader(reader);
            		String line;
            		
            		
            		while((line = r.readLine()) != null) {
            			line = reformat(line);
            			System.out.println(line);
            			visitsArea.appendText(line + "\n");
            			
            		}		
            	}
            	
            	catch(IOException e){
            		e.printStackTrace();
            	}
            }  
        });
        
        prescribeButton.setOnAction(event -> {
        	String prescription = prescriptionField.getText();
        	System.out.println("hi " + prescription);
            // Append prescription to patients data file
            try {
            	if(patientExists) {
            		FileWriter writer = new FileWriter("src/PatientData/" + pID + "_data", true);    
            		writer.write(",Prescription: " + prescription);
            		
            		writer.close();
            	}else {
            	}
	
            } catch (IOException e) {
                e.printStackTrace();
            }
            searchButton.fire();
        });

        inputFields.getChildren().addAll(prescriptionField, prescribeButton);
        
        root.setTop(titleLabel);
        root.setLeft(inputFields);
        root.setRight(rightSideInfo);

        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        BorderPane.setAlignment(inputFields, Pos.TOP_CENTER);
        BorderPane.setAlignment(rightSideInfo, Pos.TOP_CENTER);

        stage.setScene(scene);
        stage.show();
    }
    
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
                if (file.isFile() && file.getName().substring(0, patientID.length()).equals(patientID)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private String reformat(String line) {
    	String[] input = line.split(",");
    	String[] titles = {"Visit Date: ", "Height: ", "Temperature: ", "Age: ", "Blood Pressure: ", "Nurse Notes: ", ""};
    	String output = "";
    	for(int i = 0; i < input.length; i++) {
    		if(i < titles.length) {
    			input[i] = titles[i] + input[i] + " ";
    		}
    		
    		output += input[i];
    	}
    	return output;
    }
}