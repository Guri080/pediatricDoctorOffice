package pediatricDoctorOffice;

// Import statements for necessary JavaFX components
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

public class NursePortal {
	public static final int WIDTH = 700, HEIGHT = 450;  // Constants for the window size

	public void start(Stage stage) {
		stage.setTitle("Nurse Portal"); // Sets the window title.
		StackPane root = new StackPane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		stage.setScene(scene);

		// Fonts for text elements
		Font largeBoldFont = Font.font("Ari" + "al", FontWeight.BOLD, 30);
		Font largeFont = Font.font("Arial", 15);

		// Main layout container
		VBox setUp = new VBox();

		// UI components
		Label intro = new Label("Hello Nurse");
		Button logOut = new Button("Log Out");
		Button addVisit = new Button("Add new visit");

		// Styling the components
		intro.setFont(largeBoldFont);
		addVisit.setFont(largeFont);
		logOut.setFont(largeFont);

		// Event handling for the "Add new visit" button
		addVisit.setOnAction(event -> { 
			addNewVisitView addNewVisit = new addNewVisitView();
			stage.close();
			try {
				addNewVisit.start(new Stage());// Opens the new visit window
			} catch (Exception e) {
				e.printStackTrace();// Prints any exceptions to the console
			}
		});

		// Event handling for the "Log Out" button
		logOut.setOnAction(event -> { 
			PediatricDoctorOffice mainPage = new PediatricDoctorOffice();
			stage.close();
			try {
				mainPage.start(new Stage()); // Attempts to return to the main page
			} catch (Exception e) {
				e.printStackTrace();// Prints any exceptions to the console
			}
		});

		// Sets spacing between elements
		setUp.setSpacing(25);

		setUp.setAlignment(Pos.CENTER);// Centers elements within the VBox
		setUp.getChildren().addAll(intro, addVisit, logOut); // Adds UI components to the layout
		root.getChildren().add(setUp); // Adds the layout to the root pane
		stage.show();// Displays the window
	}
}
