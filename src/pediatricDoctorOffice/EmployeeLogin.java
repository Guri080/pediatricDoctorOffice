package pediatricDoctorOffice;

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
<<<<<<< HEAD
import javafx.scene.control.PasswordField;
=======
>>>>>>> d36b17541cdb3b951f92dc768ae4cb34b10a45b8
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextField;

public class EmployeeLogin {
	public static final int WIDTH = 700, HEIGHT = 450;

	public void start(Stage stage) {
		stage.setTitle("Employee Login");
		StackPane root = new StackPane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		stage.setScene(scene);

		Font largeBoldFont = Font.font("Arial", FontWeight.BOLD, 30);
		Font largeFont = Font.font("Arial", 15);

		VBox setUp = new VBox();

<<<<<<< HEAD
		String[] doctorAccessCode = { "do11", "do12", "do13", "do14" };
		String[] nurseAccessCode = { "nu11", "nu12", "nu13", "nu14" };

		/*-----EMPLOYEE LOGIN PAGE------------------------------------------------------------------------------*/
		Label loginLabel = new Label("Employee Login");
		Label passwordLabel = new Label("Enter your employee access code:");
		Label errorLabel = new Label("");

		PasswordField passwordTextField = new PasswordField();
=======
		/*-----EMPLOYEE LOGIN PAGE------------------------------------------------------------------------------*/
		Label loginLabel = new Label("Employee Login");
		Label IDLabel = new Label("Employee ID (enter 1 for doc view and 2 for nurse view):");
		TextField IDTextField = new TextField();

		Label passwordLabel = new Label("Password:");
		TextField passwordTextField = new TextField();
>>>>>>> d36b17541cdb3b951f92dc768ae4cb34b10a45b8

		Button loginBtn = new Button("Log in");

		loginLabel.setFont(largeBoldFont);
<<<<<<< HEAD
=======
		IDTextField.setPrefWidth(160); // Adjust width as needed
		IDTextField.setMaxWidth(160);
>>>>>>> d36b17541cdb3b951f92dc768ae4cb34b10a45b8
		passwordTextField.setPrefWidth(160); // Adjust width as needed
		passwordTextField.setMaxWidth(160);

		loginBtn.setFont(largeFont);
		/*-----------------------------------------------------------------------------------------------------*/
		loginBtn.setOnAction(event -> { // when employee login button is clicked
<<<<<<< HEAD
			if (passwordTextField.getText().isEmpty()) {
				errorLabel.setStyle("-fx-text-fill: red;");
				errorLabel.setText("Error! The access code field is empty");
			} else if (searchEmployee(doctorAccessCode, nurseAccessCode, passwordTextField.getText()) == 1) {
				PediatricView employeeLoginGUI = new PediatricView();
				stage.close();
				employeeLoginGUI.start(new Stage());
			} else if (searchEmployee(doctorAccessCode, nurseAccessCode, passwordTextField.getText()) == 2) {
				NursePortal employeeLoginGUI = new NursePortal();
				stage.close();
				employeeLoginGUI.start(new Stage());
=======
			if (IDTextField.getText().isEmpty()) {
				System.out.println("add something to the ID textField");
			} else if (IDTextField.getText().equals("1")) {
				PediatricView employeeLoginGUI = new PediatricView();
				stage.close();
				employeeLoginGUI.start(new Stage());
>>>>>>> d36b17541cdb3b951f92dc768ae4cb34b10a45b8
			} else {
				errorLabel.setStyle("-fx-text-fill: red;");
				errorLabel.setText("Error! access code is incorrect");

			}
		});

		setUp.setPadding(new Insets(80));
		setUp.setAlignment(Pos.TOP_CENTER);
		setUp.setSpacing(10);
		setUp.getChildren().addAll(passwordLabel, passwordTextField, errorLabel, loginBtn);
		root.getChildren().add(setUp);
		stage.show();
	}

	int searchEmployee(String[] doctorArr, String[] nurseArr, String accessID) {
		String employeePosition = accessID.substring(0, 2);

		if (employeePosition.equals("do")) {
			for (String it : doctorArr) {
				if (accessID.equals(it)) {
					return 1;
				}
			}
		} else if (employeePosition.equals("nu")) {
			for (String it : nurseArr) {
				if (accessID.equals(it)) {
					return 2;
				}
			}
		}

		return 3;
	}
}
