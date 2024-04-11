package pediatricDoctorOffice;

//Imports
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
import javafx.scene.control.PasswordField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextField;

// Employee Login class
public class EmployeeLogin {
	public static final int WIDTH = 700, HEIGHT = 450; // Constants for the window size

	public void start(Stage stage) {
		// Setup the primary stage with title and initial scene
		stage.setTitle("Employee Login");
		StackPane root = new StackPane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		stage.setScene(scene);
		
		Font largeBoldFont = Font.font("Arial", FontWeight.BOLD, 30);
		Font largeFont = Font.font("Arial", 15);

		VBox setUp = new VBox();

		// Access codes for doctors and nurses
		String[] doctorAccessCode = { "do11", "do12", "do13", "do14" };
		String[] nurseAccessCode = { "nu11", "nu12", "nu13", "nu14" };

		/*-----EMPLOYEE LOGIN PAGE------------------------------------------------------------------------------*/
		//Consists of title, access code field, and login button which is to be input by the employee
		
		Label loginLabel = new Label("Employee Login");
		Label accessIDLabel = new Label("Enter employee access code:");
		Label errorLabel = new Label("");

		PasswordField passwordTextField = new PasswordField();

		Button loginBtn = new Button("Log in");
		
		// Set fonts and widths for UI components
		loginLabel.setFont(largeBoldFont);
		passwordTextField.setPrefWidth(160);
		passwordTextField.setMaxWidth(160);

		loginBtn.setFont(largeFont);
		/*-----------------------------------------------------------------------------------------------------*/
		//Login button will use the authenticateEmployee to verify users and if successful take them into their respective employee portals.
		
		loginBtn.setOnAction(event -> { 
			if (passwordTextField.getText().isEmpty()) {
				errorLabel.setStyle("-fx-text-fill: red;");
				errorLabel.setText("Error! One or more fields are empty");
			} else if (authenticateEmployee(doctorAccessCode, nurseAccessCode, passwordTextField.getText()) == 1) {
				// Redirect to doctor's view
				PediatricView employeeLoginGUI = new PediatricView();
				stage.close();
				employeeLoginGUI.start(new Stage());
			} else if (authenticateEmployee(doctorAccessCode, nurseAccessCode, passwordTextField.getText()) == 2) {
				// Redirect to nurse's view
				NursePortal employeeLoginGUI = new NursePortal();
				stage.close();
				employeeLoginGUI.start(new Stage());
			} else {
				// Show error for incorrect access code
				errorLabel.setStyle("-fx-text-fill: red;");
				errorLabel.setText("Error! incorrect access code");
			}
		});
		
		// Layout setup for the login page
		setUp.setPadding(new Insets(80));
		setUp.setAlignment(Pos.TOP_CENTER);
		setUp.setSpacing(10);
		setUp.getChildren().addAll(loginLabel, accessIDLabel, passwordTextField, errorLabel, loginBtn);
		root.getChildren().add(setUp);
		stage.show();
	}

	/*-----AUTHENTICATION LOGIC-------------------------------------------------------------------------------------*/
	//This method is responsible for verifying the access codes of the employees(doctors/nurses)
	
	int authenticateEmployee(String[] doctorArr, String[] nurseArr, String accessID) 
	{
		String employeePosition = accessID.substring(0, 2);

		if (employeePosition.equals("do")) {
			for (String it : doctorArr) {
				if (accessID.equals(it)) 
					return 1; // Doctor access granted
			}

		} else if (employeePosition.equals("nu")) {
			for (String it : nurseArr) {
				if (accessID.equals(it)) 
					return 2; // Nurse access granted	
			}
		}

		return 3;
	}
}
