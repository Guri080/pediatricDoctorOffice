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
import java.util.ArrayList;


public class PediatricDoctorOffice extends Application {
	public static final int WIDTH = 700, HEIGHT = 450;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		StackPane root = new StackPane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		stage.setTitle("Hospital");
		String perimeter = " -fx-border-color: black;\r\n" 
						 + "    -fx-border-insets: 1;\r\n"
						 + "    -fx-border-width: 2;\r\n";
		Font boldFont = Font.font("Arial", FontWeight.BOLD, 14);
		

		/*-----LOGIN PAGE------------------------------------------------------------------------------*/
		VBox login = new VBox();
		login.setAlignment(Pos.CENTER);
		
		Button employeeLogin = new Button("Employee"); // made change here
		Button patientLogin = new Button("Patient Login");
		
		login.setMinHeight(200);
		login.setMinWidth(180);
		employeeLogin.setPrefSize(150, 50);
		patientLogin.setPrefSize(150, 50);
		
		login.getChildren().addAll(employeeLogin, patientLogin);
		/*---------------------------------------------------------------------------------------------*/
		
		employeeLogin.setOnAction(event -> { //when employee login button is clicked
			EmployeeLogin employeeLoginGUI = new EmployeeLogin();
			stage.close();
			employeeLoginGUI.start(new Stage());
		});
		
		patientLogin.setOnAction(event -> { //when patient login button is clicked
			PatientLogin patientLoginGUI = new PatientLogin();
			stage.close();
			patientLoginGUI.start(new Stage());
		});
	
		root.getChildren().add(login);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
}