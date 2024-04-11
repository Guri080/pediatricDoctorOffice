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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PediatricMessagingWindow {

	private final TextArea messageContent = new TextArea();
	public static String userID = "";

	public void display() {
		Stage stage = new Stage();
		stage.setTitle("Messages");
		BorderPane layout = new BorderPane();

		Button logOutButton = new Button("Log Out");
		Button sendNewMessageButton = new Button("Send New Message");
		HBox topMenu = new HBox(10, logOutButton, sendNewMessageButton);
		layout.setTop(topMenu);

		sendNewMessageButton.setVisible(false);
		// -----------------
		VBox inputFields = new VBox(10);
		inputFields.setAlignment(Pos.TOP_CENTER);
		inputFields.setPadding(new Insets(10));

		TextField firstNameField = new TextField();
		firstNameField.setPromptText("First Name");

		TextField lastNameField = new TextField();
		// lastNameField.setPromptText("Last Name");

		TextField birthDateField = new TextField();
		// birthDateField.setPromptText("Date of Birth");

		Button searchButton = new Button("Search");
		searchButton.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
		searchButton.setMaxWidth(Double.MAX_VALUE);

		inputFields.getChildren().addAll(firstNameField, lastNameField, birthDateField, searchButton);
		// -------------
		layout.setLeft(inputFields);

		// ****************************
		searchButton.setOnAction(event -> {
			messageContent.clear();
			String pID = firstNameField.getText() + lastNameField.getText()
					+ birthDateField.getText().replaceAll("/", "");

			if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty()
					|| birthDateField.getText().isEmpty()) {

			} else if (!searchPatient(pID)) {
				System.out.println("does not exist");
			} else {
				userID = pID;
				sendNewMessageButton.setVisible(true);
				sendNewMessageButton.setOnAction(e -> new PediatricNewMessageWindow().display());
				String filePath = "src/PatientLogins/" + pID + "_login";
				String line = "";
				try {
					BufferedReader reader = new BufferedReader(new FileReader(filePath));
					while ((line = reader.readLine()) != null) {
						line = reader.readLine();
						messageContent.appendText(line + "\n");
					}
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		messageContent.setEditable(false);
		layout.setRight(messageContent);

		Scene scene = new Scene(layout, 700, 400);
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
				if (file.isFile() && file.getName().startsWith(patientID)) {
					return true;
				}
			}
		}
		return false;
	}

	String userID() {
		return userID;
	}
}
