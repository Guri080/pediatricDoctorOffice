package pediatricDoctorOffice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PediatricNewMessageWindow {
	public void display() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Send New Message");
		/*------------TO WHO--------------------------------------------------------------*/

		TextField toField = new TextField();
		toField.setPromptText("Recipient");

		/*---------MESSAGE CONTENT----------------------------------------------------------------*/

		TextArea messageContent = new TextArea();
		messageContent.setPromptText("Write your message here");

		/*---------SEND BUTTON---------------------------------------------------------*/

		Button sendButton = new Button("Send");
		sendButton.setOnAction(event -> {
			
			messageContent.setPromptText("");
			writeMessageToFile(messageContent.getText());

			window.close();

		});
		
		// layouts
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20));
		layout.getChildren().addAll(toField, messageContent, sendButton);

		Scene scene = new Scene(layout, 400, 250);
		window.setScene(scene);
		window.showAndWait();
	}
	
	// write message/email to the patient
	private static void writeMessageToFile(String message) {
		PediatricMessagingWindow pID = new PediatricMessagingWindow();
		String patientID = pID.userID();
		String directory = "src/PatientLogins/" + patientID + "_login"; // patient directory
		message = "\nDoctor: " + message + "\n";

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(directory, true));
			writer.write(message); // write message to the file
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
