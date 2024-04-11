package pediatricDoctorOffice;

import java.awt.Label;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewMessageWindow {

	public static void display() {
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

//			if (toField.getText().isEmpty() || messageContent.getText().isEmpty()) { // check if all boxes are filled
//				messageContent.setStyle("-fx-text-fill: red;");
//				messageContent.setPromptText("one of your field is empty");
//			} else { // write message
			messageContent.setPromptText("");
			writeMessageToFile(messageContent.getText());

			window.close();

		});

		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20));
		layout.getChildren().addAll(toField, messageContent, sendButton);

		Scene scene = new Scene(layout, 400, 250);
		window.setScene(scene);
		window.showAndWait();

	}

	private static void writeMessageToFile(String message) {
		PatientLogin pID = new PatientLogin();
		String directory = "src/PatientLogins/" + pID.getID() + "_login";
		message = "\nPatient: " + message + "\n";
		
		System.out.println(message);
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(directory, true));
			System.out.println("writing in: " + directory);
			writer.write(message);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
