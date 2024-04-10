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

public class PatientPortal {
	public static final int WIDTH = 700, HEIGHT = 450;
	private static final String DATA_PATH = "src/PatientData/";

	public void start(Stage stage) {
		stage.setTitle("Patient Portal");
		StackPane root = new StackPane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		stage.setScene(scene);
		
		Font largeBoldFont = Font.font("Ari" + "al", FontWeight.BOLD, 30);
		Font largeFont = Font.font("Arial", 15);
		
		VBox setUp = new VBox();
		/*---------------------------------------------------------------------------------------*/
		 Label greeting = new Label("Hello);
       		 greeting.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        	Label detailsLabel = new Label("Your Details:");
        	detailsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));

		/*---------------------------------------------------------------------------------------*/
		logOut.setOnAction(event -> { //when patient info is filled and log in is clicked
			PediatricDoctorOffice mainPage = new PediatricDoctorOffice();
			stage.close();
			try {
				mainPage.start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		
		setUp.setAlignment(Pos.CENTER);
		setUp.getChildren().addAll(intro, logOut);
		root.getChildren().add(setUp);
		stage.show();
	}
}
