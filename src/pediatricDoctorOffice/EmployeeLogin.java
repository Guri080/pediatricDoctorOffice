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

		/*-----EMPLOYEE LOGIN PAGE------------------------------------------------------------------------------*/
		Label loginLabel = new Label("Employee Login");
		Label IDLabel = new Label("Employee ID (enter 1 for doc view and 2 for nurse view):");
		TextField IDTextField = new TextField();

		Label passwordLabel = new Label("Password:");
		TextField passwordTextField = new TextField();

		Button loginBtn = new Button("Log in");
		Button createAccount = new Button("Create an account");
		loginLabel.setFont(largeBoldFont);
		IDTextField.setPrefWidth(160); // Adjust width as needed
		IDTextField.setMaxWidth(160);
		passwordTextField.setPrefWidth(160); // Adjust width as needed
		passwordTextField.setMaxWidth(160);

		loginBtn.setFont(largeFont);
		/*-----------------------------------------------------------------------------------------------------*/
		loginBtn.setOnAction(event -> { // when employee login button is clicked
			if (IDTextField.getText().isEmpty()) {
				System.out.println("add something to the ID textField");
			} else if (IDTextField.getText().equals("1")) {
				PediatricView employeeLoginGUI = new PediatricView();
				stage.close();
				employeeLoginGUI.start(new Stage());
			} else {
				NursePortal employeeLoginGUI = new NursePortal();
				stage.close();
				employeeLoginGUI.start(new Stage());
			}
		});
		
		createAccount.setOnAction(e->createNewAccount(stage));

		setUp.setPadding(new Insets(80));
		setUp.setAlignment(Pos.TOP_CENTER);
		setUp.setSpacing(10);
		setUp.getChildren().addAll(loginLabel, IDLabel, IDTextField, passwordLabel, passwordTextField, loginBtn);
		root.getChildren().add(setUp);
		stage.show();
	}
	
	void createNewAccount(Stage oldStage) {
		oldStage.close();
		
		
	}
}
