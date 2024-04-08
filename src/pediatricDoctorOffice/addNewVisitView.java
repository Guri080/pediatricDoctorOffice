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

public class addNewVisitView {
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
		Button newPatient = new Button("New Patient");
		Button oldPatient = new Button("Exisiting Patient");
		Button logOutBtn = new Button("Log out");

		newPatient.setFont(largeFont);
		oldPatient.setFont(largeFont);

		/*---------------------------------------------------------------------------------------*/
		newPatient.setOnAction(e -> newPatient(stage));
		oldPatient.setOnAction(e -> existingPatient(stage));

		logOutBtn.setOnAction(event -> {
			PediatricDoctorOffice mainScreen = new PediatricDoctorOffice();
			

		});

		setUp.setSpacing(25);
		setUp.setAlignment(Pos.CENTER);
		setUp.getChildren().addAll(newPatient, oldPatient);
		root.getChildren().add(setUp);
		stage.show();
	}

	void newPatient(Stage oldStage) {
		oldStage.close();
		Stage stage = new Stage();
		stage.initOwner(null);
		StackPane root = new StackPane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		VBox container = new VBox();
		GridPane gridPane = new GridPane();
		// weight, height, body temperature, and blood pressure,
		Label heightLabel = new Label("Enter height: ");
		Label ageLabel = new Label("Enter age: ");
		Label bodyTempIDLabel = new Label("Enter body temprature: ");
		Label bloodPressureLabel = new Label("Enter patient ID: ");
		Label errorLabel = new Label("");

		TextField heightField = new TextField();
		TextField ageField = new TextField();
		TextField bodyTempIDField = new TextField();
		TextField bloodPressureField = new TextField();

		Button saveBtn = new Button("Save");
		Button backBtn = new Button("Go back");
		/*---------------------------------------------------------------------------------------*/
		saveBtn.setOnAction(event -> { // when employee info is filled and log in is clicked
			try {
				if (heightField.getText().isEmpty() || bodyTempIDField.getText().isEmpty()
						|| bloodPressureField.getText().isEmpty()) {
					errorLabel.setStyle("-fx-text-fill: red;");
					errorLabel.setText("Error! One or more fields are empty");
				} else if (Double.parseDouble(ageField.getText()) < 12) {
					errorLabel.setStyle("-fx-text-fill: red;");
					errorLabel.setText("Error! The age should be more than 12");
				} else {
					heightField.clear();
					ageField.clear();
					bodyTempIDField.clear();
					bloodPressureField.clear();
					errorLabel.setStyle("-fx-text-fill: black;");
					errorLabel.setText("Information added");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		backBtn.setOnAction(event -> { // when employee info is filled and log in is clicked
			stage.close();
			addNewVisitView mainScreen = new addNewVisitView();
			try {
				mainScreen.start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		gridPane.add(heightLabel, 0, 1);
		gridPane.add(heightField, 1, 1);
		gridPane.add(bodyTempIDLabel, 0, 2);
		gridPane.add(bodyTempIDField, 1, 2);
		gridPane.add(bloodPressureLabel, 0, 3);
		gridPane.add(bloodPressureField, 1, 3);
		gridPane.add(ageLabel, 0, 4);
		gridPane.add(ageField, 1, 4);
//		gridPane.add(insuranceIDLabel, 0, 5);
//		gridPane.add(insuranceIDText, 1, 5);

		gridPane.setAlignment(Pos.CENTER_LEFT);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		container.setSpacing(20);
		container.setAlignment(Pos.TOP_CENTER);
		container.getChildren().addAll(gridPane, errorLabel, backBtn, saveBtn);
		root.setPadding(new javafx.geometry.Insets(20));
		root.getChildren().add(container);
		stage.setScene(scene);
		stage.show();
	}

	void existingPatient(Stage oldStage) {
		oldStage.close();
		Stage stage = new Stage();
		stage.initOwner(null);
		StackPane root = new StackPane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		VBox container = new VBox();
		GridPane gridPane = new GridPane();
		// weight, height, body temperature, and blood pressure,
		Label patientIDLabel = new Label("Enter patient ID: ");
		Label heightLabel = new Label("Enter height: ");
		Label ageLabel = new Label("Enter age: ");
		Label bodyTempIDLabel = new Label("Enter body temprature: ");
		Label bloodPressureLabel = new Label("Enter patient ID: ");
		Label errorLabel = new Label("");

		TextField patientIDField = new TextField();
		TextField heightField = new TextField();
		TextField ageField = new TextField();
		TextField bodyTempIDField = new TextField();
		TextField bloodPressureField = new TextField();

		Button backBtn = new Button("Go back");
		Button saveBtn = new Button("Save");

		/*---------------------------------------------------------------------------------------*/
		saveBtn.setOnAction(event -> { // when employee info is filled and log in is clicked
			try {
				if (patientIDField.getText().isEmpty() || heightField.getText().isEmpty()
						|| bodyTempIDField.getText().isEmpty() || bloodPressureField.getText().isEmpty()) {
					errorLabel.setStyle("-fx-text-fill: red;");
					errorLabel.setText("Error! One or more fields are empty");
				} else if (Double.parseDouble(ageField.getText()) < 12) {
					errorLabel.setStyle("-fx-text-fill: red;");
					errorLabel.setText("Error! The age should be more than 12");
				} else {
					patientIDField.clear();
					heightField.clear();
					ageField.clear();
					bodyTempIDField.clear();
					bloodPressureField.clear();
					errorLabel.setStyle("-fx-text-fill: black;");
					errorLabel.setText("Information added");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		backBtn.setOnAction(event -> { // when employee info is filled and log in is clicked
			stage.close();
			addNewVisitView mainScreen = new addNewVisitView();
			try {
				mainScreen.start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		gridPane.add(patientIDLabel, 0, 0);
		gridPane.add(patientIDField, 1, 0);
		gridPane.add(heightLabel, 0, 1);
		gridPane.add(heightField, 1, 1);
		gridPane.add(bodyTempIDLabel, 0, 2);
		gridPane.add(bodyTempIDField, 1, 2);
		gridPane.add(bloodPressureLabel, 0, 3);
		gridPane.add(bloodPressureField, 1, 3);
		gridPane.add(ageLabel, 0, 4);
		gridPane.add(ageField, 1, 4);
//		gridPane.add(insuranceIDLabel, 0, 5);
//		gridPane.add(insuranceIDText, 1, 5);

		gridPane.setAlignment(Pos.CENTER_LEFT);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		container.setSpacing(20);
		container.setAlignment(Pos.TOP_CENTER);
		container.getChildren().addAll(gridPane, errorLabel, backBtn, saveBtn);
		root.setPadding(new javafx.geometry.Insets(20));
		root.getChildren().add(container);
		stage.setScene(scene);
		stage.show();
	}

}
