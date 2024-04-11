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

public class NursePortal {
	public static final int WIDTH = 700, HEIGHT = 450;

	public void start(Stage stage) {
		stage.setTitle("Employee Login");
		StackPane root = new StackPane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		stage.setScene(scene);
		Font largeBoldFont = Font.font("Ari" + "al", FontWeight.BOLD, 30);
		Font largeFont = Font.font("Arial", 15);
		VBox setUp = new VBox();

		/*---------------------------------------------------------------------------------------*/
		//greeting text
		Label intro = new Label("Hello Nurse");
		Button logOut = new Button("Log Out");
		Button addVisit = new Button("Add new visit");

		intro.setFont(largeBoldFont);
		addVisit.setFont(largeFont);
		logOut.setFont(largeFont);

		/*---------------------------------------------------------------------------------------*/
		//add a new visit
		addVisit.setOnAction(event -> { // when employee info is filled and log in is clicked
			addNewVisitView addNewVisit = new addNewVisitView();
			stage.close();
			try {
				addNewVisit.start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		//logout
		logOut.setOnAction(event -> { // when employee info is filled and log in is clicked
			PediatricDoctorOffice mainPage = new PediatricDoctorOffice();
			stage.close();
			try {
				//back to main page
				mainPage.start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		setUp.setSpacing(25);

		setUp.setAlignment(Pos.CENTER);
		setUp.getChildren().addAll(intro, addVisit, logOut);
		root.getChildren().add(setUp);
		stage.show();
	}
}
