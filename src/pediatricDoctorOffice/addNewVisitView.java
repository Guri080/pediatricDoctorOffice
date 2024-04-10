package pediatricDoctorOffice;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextField;

public class addNewVisitView {
	public static final int WIDTH = 700, HEIGHT = 450;

	public void start(Stage stage) {
		stage.setTitle("Employee Login");
		StackPane root = new StackPane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		stage.setScene(scene);
		Font largeBoldFont = Font.font("Ari" + "al", FontWeight.BOLD, 30);
		Font largeFont = Font.font("Arial", 15);
		VBox setUp = new VBox();

		/*---------------------------------------------------------------------------------------*/
		Button newPatient = new Button("New Patient");
		Button oldPatient = new Button("Exisiting Patient");
		Button logOutBtn = new Button("Log out");

		newPatient.setFont(largeFont);
		oldPatient.setFont(largeFont);

		/*---------------------------------------------------------------------------------------*/
		newPatient.setOnAction(e -> newPatient(stage));
		oldPatient.setOnAction(e -> existingPatient(stage));

		logOutBtn.setOnAction(event -> {
			PediatricDoctorOffice mainScreen = new PediatricDoctorOffice();			
		});

		setUp.setSpacing(25);
		setUp.setAlignment(Pos.CENTER);
		setUp.getChildren().addAll(newPatient, oldPatient);
		root.getChildren().add(setUp);
		stage.show();
	}
	void newPInit(String bday, String name, String height, String temp, String age, String bp, String date, String nn) {
		String str = name + bday;
		str = str.replaceAll("\\s", "");
		str = str.replaceAll("/", "");
		nn = nn.replaceAll(",", "");
		String patientID = str;
		str = "src/PatientData/" + str + "_data";
		String nstr = "src/PatientLogins/" + patientID + "_login";
		
		String patientD = date + "," +height + "," + temp + "," + age + "," + bp + "," + nn;
		
		try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(str));
            //BufferedWriter pwriter = new BufferedWriter(new FileWriter(nstr));
            writer.write(patientD);
            //pwriter.write(patientID);
            writer.close();
            //pwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		try {
            BufferedWriter pwriter = new BufferedWriter(new FileWriter(nstr));
            //BufferedWriter pwriter = new BufferedWriter(new FileWriter(nstr));
            pwriter.write(patientID);
            //pwriter.write(patientID);
            pwriter.close();
            //pwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private Boolean searchPatient(String patientID) {
        // Specify the directory containing the text files
        String directoryPath = "src/patientDataFile";

        // Create a File object representing the directory
        File directory = new File(directoryPath);

        // Verify that the specified path exists and is a directory
        if (directory.exists() && directory.isDirectory()) {
            // Get a list of files in the directory
            File[] files = directory.listFiles();

            // Iterate through the files
            for (File file : files) {
                if (file.isFile() && file.getName().substring(0, 5).equals(patientID)) {
                    return true;
                }
            }
        }
        return false;
    }
	
	

	void newPatient(Stage oldStage) {
		oldStage.close();
		Stage stage = new Stage();
		stage.initOwner(null);
		StackPane root = new StackPane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		VBox container = new VBox();
		GridPane gridPane = new GridPane();
		// weight, height, body temperature, and blood pressure,
		Label nameLabel = new Label("Enter Name (firstname lastname): ");
		Label bdayLabel = new Label("Enter Birthday (mm/dd/yyyy): ");
		Label heightLabel = new Label("Enter height (cm): ");
		Label ageLabel = new Label("Enter age: ");
		Label bodyTempIDLabel = new Label("Enter body temprature: ");
		Label bloodPressureLabel = new Label("Enter blood pressure: ");
		Label nurseNotesLabel = new Label("Nurse Notes: ");
		Label errorLabel = new Label("");
		Label dateLabel = new Label("Enter date of visit (mm/dd/yyyy): ");
		
		TextField nameField = new TextField();
		TextField bdayField = new TextField();
		TextField heightField = new TextField();
		TextField ageField = new TextField();
		TextField bodyTempIDField = new TextField();
		TextField bloodPressureField = new TextField();
		TextField nurseNotesField = new TextField(); 
		TextField dateField = new TextField();
		

		Button saveBtn = new Button("Save");
		Button backBtn = new Button("Go back");
		
		
		/*---------------------------------------------------------------------------------------*/
		saveBtn.setOnAction(event -> { // when employee info is filled and log in is clicked
			try {
				if (heightField.getText().isEmpty() || dateField.getText().isEmpty()||bdayField.getText().isEmpty() || nameField.getText().isEmpty() ||bodyTempIDField.getText().isEmpty()|| bloodPressureField.getText().isEmpty()) {
					errorLabel.setStyle("-fx-text-fill: red;");
					errorLabel.setText("Error! One or more fields are empty");
				} else if (Double.parseDouble(ageField.getText()) < 12) {
					errorLabel.setStyle("-fx-text-fill: red;");
					errorLabel.setText("Error! The age should be more than 12");
				} else {
					String bday = bdayField.getText();
					String name = nameField.getText(); 
					String height = heightField.getText();
					String temp = bodyTempIDField.getText();
					String age = ageField.getText();
					String bp = bloodPressureField.getText();
					String nn = nurseNotesField.getText();
					String date = dateField.getText().replaceAll("/", "");
					newPInit(bday, name, height, temp, age, bp, date,nn);//**
					heightField.clear();
					ageField.clear();
					bodyTempIDField.clear();
					bloodPressureField.clear();
					errorLabel.setStyle("-fx-text-fill: black;");
					errorLabel.setText("Information added");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		backBtn.setOnAction(event -> { // when employee info is filled and log in is clicked
			stage.close();
			addNewVisitView mainScreen = new addNewVisitView();
			try {
				mainScreen.start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		gridPane.add(nameLabel,0, 1);
		gridPane.add(nameField, 1,1);
		gridPane.add(bdayLabel,  0, 2);
		gridPane.add(bdayField, 1, 2);
		gridPane.add(heightLabel, 0, 3);
		gridPane.add(heightField, 1, 3);
		gridPane.add(bodyTempIDLabel, 0, 4);
		gridPane.add(bodyTempIDField, 1, 4);
		gridPane.add(bloodPressureLabel, 0, 5);
		gridPane.add(bloodPressureField, 1, 5);
		gridPane.add(ageLabel, 0, 6);
		gridPane.add(ageField, 1, 6);
		gridPane.add(nurseNotesLabel, 0, 7);
		gridPane.add(nurseNotesField, 1, 7);
		gridPane.add(dateLabel, 0, 8);
		gridPane.add(dateField, 1, 8);
//		gridPane.add(insuranceIDLabel, 0, 5);
//		gridPane.add(insuranceIDText, 1, 5);

		gridPane.setAlignment(Pos.CENTER_LEFT);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		container.setSpacing(20);
		container.setAlignment(Pos.TOP_CENTER);
		container.getChildren().addAll(gridPane, errorLabel, backBtn, saveBtn);
		root.setPadding(new javafx.geometry.Insets(20));
		root.getChildren().add(container);
		stage.setScene(scene);
		stage.show();
	}

	void existingPatient(Stage oldStage) {
		oldStage.close();
		Stage stage = new Stage();
		stage.initOwner(null);
		StackPane root = new StackPane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		VBox container = new VBox();
		GridPane gridPane = new GridPane();
		// weight, height, body temperature, and blood pressure,
		Label nameLabel = new Label("Enter Name (firstname lastname): ");
		Label bdayLabel = new Label("Enter Birthday (mm/dd/yyyy): ");
		Label heightLabel = new Label("Enter height (cm): ");
		Label ageLabel = new Label("Enter age: ");
		Label bodyTempIDLabel = new Label("Enter body temperature: ");
		Label bloodPressureLabel = new Label("Enter blood pressure: ");
		Label nurseNotesLabel = new Label("Nurse Notes: ");
		Label errorLabel = new Label("");
		Label dateLabel = new Label("Enter date of visit (mm/dd/yyyy): ");

		TextField nameField = new TextField();
		TextField bdayField = new TextField();
		TextField heightField = new TextField();
		TextField ageField = new TextField();
		TextField bodyTempIDField = new TextField();
		TextField bloodPressureField = new TextField();
		TextField nurseNotesField = new TextField();
		TextField dateField = new TextField();

		Button backBtn = new Button("Go back");
		Button saveBtn = new Button("Save");

		/*---------------------------------------------------------------------------------------*/
		saveBtn.setOnAction(event -> { // when employee info is filled and log in is clicked
			try {
				if (nameField.getText().isEmpty()||nurseNotesField.getText().isEmpty() ||bdayField.getText().isEmpty() || heightField.getText().isEmpty()
						|| bodyTempIDField.getText().isEmpty() || bloodPressureField.getText().isEmpty()) {
					errorLabel.setStyle("-fx-text-fill: red;");
					errorLabel.setText("Error! One or more fields are empty");
				} else if (Double.parseDouble(ageField.getText()) < 12) {
					errorLabel.setStyle("-fx-text-fill: red;");
					errorLabel.setText("Error! The age should be more than 12");
				} else {
					String name = nameField.getText();
					String bday = bdayField.getText();
					String height = heightField.getText();
					String temp = bodyTempIDField.getText();
					String age = ageField.getText();
					String bp = bloodPressureField.getText();
					String nn = nurseNotesField.getText();
					String date = dateField.getText().replaceAll("/", "");
					existingPVisit(bday, name, height,temp, age, bp, nn, date, errorLabel);
					heightField.clear();
					ageField.clear();
					bodyTempIDField.clear();
					bloodPressureField.clear();
					errorLabel.setStyle("-fx-text-fill: black;");
					errorLabel.setText("Information added");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		backBtn.setOnAction(event -> { // when employee info is filled and log in is clicked
			stage.close();
			addNewVisitView mainScreen = new addNewVisitView();
			try {
				mainScreen.start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		gridPane.add(nameLabel, 0, 0);
		gridPane.add(nameField, 1, 0);
		gridPane.add(bdayLabel, 0, 1);
		gridPane.add(bdayField, 1, 1);
		gridPane.add(heightLabel, 0, 2);
		gridPane.add(heightField, 1, 2);
		gridPane.add(bodyTempIDLabel, 0, 3);
		gridPane.add(bodyTempIDField, 1, 3);
		gridPane.add(bloodPressureLabel, 0, 4);
		gridPane.add(bloodPressureField, 1, 4);
		gridPane.add(ageLabel, 0, 5);
		gridPane.add(ageField, 1, 5);
		gridPane.add(nurseNotesLabel, 0, 7);
		gridPane.add(nurseNotesField, 1, 7);
		gridPane.add(dateLabel, 0, 8);
		gridPane.add(dateField, 1, 8);

		gridPane.setAlignment(Pos.CENTER_LEFT);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		container.setSpacing(20);
		container.setAlignment(Pos.TOP_CENTER);
		container.getChildren().addAll(gridPane, errorLabel, backBtn, saveBtn);
		root.setPadding(new javafx.geometry.Insets(20));
		root.getChildren().add(container);
		stage.setScene(scene);
		stage.show();
	}
	
	void existingPVisit(String bday, String name, String height, String temp, String age, String bp,String nn,String date,Label eL) {
		String str = name + bday;
		str = str.replaceAll("\\s", "");
		str = str.replaceAll("/", "");
		nn = nn.replaceAll(",", "");
		
		str = "src/PatientData/" + str + "_data";
		
		String patientD = date + "," + height + "," + temp + "," + age + "," + bp + "," + nn;
		
		try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(str, true));
            writer.write("\n" + patientD);
            System.out.println("written");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

}