package pediatricDoctorOffice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PatientLogin {
	public static final int WIDTH = 700, HEIGHT = 450;

	public void start(Stage stage) {
		stage.setTitle("Employee Login");
		StackPane root = new StackPane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		stage.setScene(scene);

		Font largeBoldFont = Font.font("Arial", FontWeight.BOLD, 30);
		Font largeFont = Font.font("Arial", 15);

		VBox setUp = new VBox();

		/*-----EMPLOYEE LOGIN PAGE------------------------------------------------------------------------------*/
		Label loginLabel = new Label("Patient Login");
		Label userNameLabel = new Label("Username:");
		Label errorLabel = new Label("");
		TextField userNameTextField = new TextField();

		Label passwordLabel = new Label("Password:");
		PasswordField passwordTextField = new PasswordField();

		Button loginBtn = new Button("Log in");
		Button newPatient = new Button("Create an account");

		loginLabel.setFont(largeBoldFont);
		userNameTextField.setPrefWidth(160); // Adjust width as needed
		userNameTextField.setMaxWidth(160);
		passwordTextField.setPrefWidth(160); // Adjust width as needed
		passwordTextField.setMaxWidth(160);

		loginBtn.setFont(largeFont);
		/*-----------------------------------------------------------------------------------------------------*/
		loginBtn.setOnAction(event -> { // when patient login button is clicked
			PatientPortal patientLoginGUI = new PatientPortal();

			if (userNameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
				errorLabel.setStyle("-fx-text-fill: red;");
				errorLabel.setText("Error! One or more fields are empty");
			} else if (!(seachPatientAccount(userNameTextField.getText(), passwordTextField.getText()))) {

			} else {
				stage.close();
				patientLoginGUI.start(new Stage());
			}
		});

		newPatient.setOnAction(e -> createAccount(stage));

		setUp.setPadding(new Insets(80));
		setUp.setAlignment(Pos.TOP_CENTER);
		setUp.setSpacing(10);
		setUp.getChildren().addAll(loginLabel, userNameLabel, userNameTextField, passwordLabel, passwordTextField,
				loginBtn, newPatient);
		root.getChildren().add(setUp);
		stage.show();
	}

	void createAccount(Stage oldStage) {
		System.out.println("button clicked");
		oldStage.close();
		Stage stage = new Stage();
		stage.initOwner(null);
		StackPane root = new StackPane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		VBox container = new VBox();
		GridPane gridPane = new GridPane();

		Label nameLabel = new Label("Enter your name");
		Label DOBLabel = new Label("Enter your date of birth");
		Label passwordLabel = new Label("Enter your password");
		Label confirmPasswordLabel = new Label("Confirm your password");
		Label errorLabel = new Label("");

		TextField nameField = new TextField();
		TextField DOBField = new TextField();
		PasswordField passwordField = new PasswordField();
		PasswordField confirmPasswordField = new PasswordField();

		Button createAccBtn = new Button("Create Account");

		gridPane.add(nameLabel, 0, 0);
		gridPane.add(nameField, 1, 0);
		gridPane.add(DOBLabel, 0, 1);
		gridPane.add(DOBField, 1, 1);
		gridPane.add(passwordLabel, 0, 2);
		gridPane.add(passwordField, 1, 2);
		gridPane.add(confirmPasswordLabel, 0, 3);
		gridPane.add(confirmPasswordField, 1, 3);

		createAccBtn.setOnAction(event -> {
			if (nameField.getText().isEmpty() || DOBField.getText().isEmpty() || passwordField.getText().isEmpty()
					|| confirmPasswordField.getText().isEmpty()) {
				errorLabel.setStyle("-fx-text-fill: red;");
				errorLabel.setText("Error! One or more fields are empty");
			} else if (!(passwordField.getText().equals(confirmPasswordField.getText()))) {
				errorLabel.setStyle("-fx-text-fill: red;");
				errorLabel.setText("Error! passwords do not match");
			} else if (searchPatient(nameField.getText(), DOBField.getText())) {
				writeToFile(nameField.getText(), DOBField.getText(), passwordField.getText());
				errorLabel.setStyle("-fx-text-fill: black;");
				errorLabel.setText("Account Created");
			} else {
				errorLabel.setStyle("-fx-text-fill: red;");
				errorLabel.setText("Error! a patient with your info does not exist");
			}

		});

		gridPane.setAlignment(Pos.CENTER_LEFT);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		container.setSpacing(20);
		container.setAlignment(Pos.TOP_CENTER);
		container.getChildren().addAll(gridPane, errorLabel, createAccBtn);
		root.setPadding(new javafx.geometry.Insets(20));
		root.getChildren().add(container);
		stage.setScene(scene);
		stage.show();

	}

	boolean searchPatient(String name, String DOB) {
		String str = name + DOB;
		str = str.replaceAll("\s", "");
		str = str.replaceAll("/", "");
		String patientID = str + "_login";
		String directoryPath = "src/PatientLogins/";

		// Create a File object representing the directory
		File directory = new File(directoryPath);

		// Verify that the specified path exists and is a directory
		if (directory.exists() && directory.isDirectory()) {
			// Get a list of files in the directory
			File[] files = directory.listFiles();

			// Iterate through the files
			for (File file : files) {
				if (file.isFile() && file.getName().equals(patientID)) {
					return checkUser(file);
				}
			}
		}
		return false;
	}

	private boolean checkUser(File patientFile) {
		String line = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(patientFile));
			line = reader.readLine();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] arr = line.split(",");

		if (arr.length >= 2) {
			return false; // patient already exists
		} else {
			return true; // patient does NOT exists
		}
	}

	private boolean seachPatientAccount(String patientID, String password) {
		String directoryPath = "src/PatientLogins/";
		String line = "";
		// Create a File object representing the directory
		File directory = new File(directoryPath);
		File patientFile = null;
		
		String patientFileName = patientID + "_login";

		// Verify that the specified path exists and is a directory
		if (directory.exists() && directory.isDirectory()) {
			// Get a list of files in the directory
			File[] files = directory.listFiles();

			// Iterate through the files
			for (File file : files) {
				System.out.println("The files are: " + file.getName());
				if (file.isFile() && file.getName().equals(patientFileName)) {
					// break if patient exists
					System.out.println("patient exists");
					patientFile = file;
					break;
				}
			}
		}

		if (patientFile == null) {
			System.out.println("is null???");
			return false; // patient does not exist
		}

		// **Authenticate patient userID and password **//
		try {
			BufferedReader reader = new BufferedReader(new FileReader(patientFile));
			line = reader.readLine();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] arr = line.split(",");

		System.out.println("The arry has" + arr[0] + " " + arr[1]);

		if (arr.length >= 2 && arr[0].equals(patientID) && arr[1].equals(password)) {// patient exists
			return true;
		}
		return false;
	}

	private void writeToFile(String name, String DOB, String password) {
		System.out.println(name + " " + DOB + " " + password);
		String str = name + DOB;
		str = str.replaceAll("\s", "");
		str = str.replaceAll("/", "");
		String filePath = "src/PatientLogins/" + str + "_login";

		System.out.println(str);
		System.out.println(filePath);

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
			writer.write("," + password);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}