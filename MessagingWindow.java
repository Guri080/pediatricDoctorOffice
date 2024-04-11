package pediatricDoctorOffice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MessagingWindow {

	private final TextArea messageContent = new TextArea();

	public void display() {
		Stage stage = new Stage();
		stage.setTitle("Messages");
		BorderPane layout = new BorderPane();
		
		// buttons and layouts
		Button logOutButton = new Button("Log Out");
		Button sendNewMessageButton = new Button("Send New Message");
		HBox topMenu = new HBox(10, logOutButton, sendNewMessageButton);
		layout.setTop(topMenu);
		
		// when the user wants to send a message
		sendNewMessageButton.setOnAction(e -> NewMessageWindow.display());

		PatientLogin pID = new PatientLogin();
		String filePath = "src/PatientLogins/" + pID.getID() + "_login"; // file path to the patient logins
		String line = "";
		// write the message to the correct patient file
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
		messageContent.setEditable(false);
		layout.setRight(messageContent);

		Scene scene = new Scene(layout, 700, 400);
		stage.setScene(scene);
		stage.show();
	}
}