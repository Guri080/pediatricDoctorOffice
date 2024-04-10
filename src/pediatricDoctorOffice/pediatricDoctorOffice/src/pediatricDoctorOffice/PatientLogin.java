package pediatricDoctorOffice;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PatientLogin {
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
	        Label loginLabel = new Label("Patient Login");
	        Label userNameLabel = new Label("Username:");
	        TextField userNameTextField = new TextField();
	        
	        Label passwordLabel = new Label("Password:");
	        TextField passwordTextField = new TextField();
	        
	        Button loginBtn = new Button("Log in");
	        Button newPatient = new Button("Create an account");
	        
	        
	        loginLabel.setFont(largeBoldFont);
	        userNameTextField.setPrefWidth(160); // Adjust width as needed
	        userNameTextField.setMaxWidth(160);
	        passwordTextField.setPrefWidth(160); // Adjust width as needed
	        passwordTextField.setMaxWidth(160);
	        
	        loginBtn.setFont(largeFont);
	        /*-----------------------------------------------------------------------------------------------------*/
	        loginBtn.setOnAction(event -> { //when patient login button is clicked
				PatientPortal patientLoginGUI = new PatientPortal();
				stage.close();
				patientLoginGUI.start(new Stage());
			});
	        
	        
	        
	        
	        
	        
	        setUp.setPadding(new Insets(80)); 
	        setUp.setAlignment(Pos.TOP_CENTER);
	        setUp.setSpacing(10);
	        setUp.getChildren().addAll(loginLabel, userNameLabel, userNameTextField, passwordLabel, passwordTextField, loginBtn);
	        root.getChildren().add(setUp);
	        stage.show();
	    }
}
