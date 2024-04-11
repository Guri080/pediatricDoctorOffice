package pediatricDoctorOffice;

// Imports
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
import java.util.ArrayList;

// Main class that extends Application
public class PediatricDoctorOffice extends Application {
	public static final int WIDTH = 700, HEIGHT = 450; // Constants for the window dimensions.

	public static void main(String[] args) {
		launch(args);
	}
	//Start Method
	@Override
	public void start(Stage stage) throws Exception {
		StackPane root = new StackPane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		stage.setTitle("Hospital");// Sets the title for the window.
		
		String perimeter = " -fx-border-color: black;\r\n"  + "    -fx-border-insets: 1;\r\n"+ "    -fx-border-width: 2;\r\n";
		Font boldFont = Font.font("Arial", FontWeight.BOLD, 14);
		
		 // VBox layout 
		VBox login = new VBox();
		login.setAlignment(Pos.CENTER);

		// Buttons for employee and patient login.
		Button employeeLogin = new Button("Employee"); // made change here
		Button patientLogin = new Button("Patient Login");

		// Setting the size of the VBox container and the buttons.
		login.setMinHeight(200);
		login.setMinWidth(180);
		employeeLogin.setPrefSize(150, 50);
		patientLogin.setPrefSize(150, 50);
		
		login.getChildren().addAll(employeeLogin, patientLogin);
		
		// Event handler for the employee login button
		employeeLogin.setOnAction(event -> { 
			EmployeeLogin employeeLoginGUI = new EmployeeLogin();
			stage.close();
			employeeLoginGUI.start(new Stage());
		});

		// Event handler for the patient login button.
		patientLogin.setOnAction(event -> { //when patient login button is clicked
			PatientLogin patientLoginGUI = new PatientLogin();
			stage.close();
			patientLoginGUI.start(new Stage());
		});

		// Adding the VBox to the root container 
		root.getChildren().add(login);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
}
