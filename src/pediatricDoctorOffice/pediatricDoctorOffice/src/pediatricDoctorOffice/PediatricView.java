package pediatricDoctorOffice;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PediatricView {
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
		Label intro = new Label("Hello Doctor");
		Button logOut = new Button("Log Out");

		intro.setFont(largeBoldFont);
		logOut.setFont(largeFont);
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
