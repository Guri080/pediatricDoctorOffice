package pediatricDoctorOffice;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PatientPortal {
    public static final int WIDTH = 700, HEIGHT = 450;
    // Placeholder for patient name
    private static final String patientName = "Name";

    public void start(Stage stage) {
        stage.setTitle("Patient Portal");
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);

        Font largeBoldFont = Font.font("Arial", FontWeight.BOLD, 30);
        Font largeFont = Font.font("Arial", 15);

        VBox centerContent = new VBox(10); // Adjust spacing as needed
        centerContent.setPadding(new Insets(20)); // Margin around the VBox

        /*-------HELLO PATIENT NAME------------------------------------------------------------------------*/
        
        Label intro = new Label("Hello " + patientName);
        intro.setFont(largeBoldFont);

        /*-------MESSAGE BUTTON-------------------------------------------------------------*/
        
        Button messageButton = new Button("Message");
        messageButton.setFont(largeFont);
        messageButton.setOnAction(e -> new MessagingWindow().display());
        HBox topRightBox = new HBox(messageButton);
        topRightBox.setAlignment(Pos.TOP_RIGHT);
        topRightBox.setPadding(new Insets(10));
        
        /*------------PATIENT INFORMATION---------------------------------------------------------*/
        
        VBox patientInfoBox = new VBox(5);
        Label heightLabel = new Label("Height: ");
        Label tempLabel = new Label("Temperature: ");
        Label ageLabel = new Label("Age: ");
        Label bpLabel = new Label("Blood Pressure: ");
        patientInfoBox.getChildren().addAll(heightLabel, tempLabel, ageLabel, bpLabel);

        /*----------SCROLL PANE USED FOR MULTIPLE VISIT ENTRIES----------------------------------------------------------*/
        
        ScrollPane scrollPane = new ScrollPane();
        VBox visitsBox = new VBox(10);
        scrollPane.setContent(visitsBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(HEIGHT / 2);

        /*------------ADDING ENTRY NEEDS WORK--------------------------------------------------------*/
        
        HBox visitEntry = new HBox(20);
        visitEntry.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        visitEntry.setPadding(new Insets(10));
        Label dateLabel = new Label("Date: ");
        Button viewVisitButton = new Button("View Visit Information");
        visitEntry.getChildren().addAll(dateLabel, viewVisitButton);
        visitsBox.getChildren().add(visitEntry);
        centerContent.getChildren().addAll(intro, patientInfoBox, scrollPane);
        
        /*------------MESSAGES BUTTON OPENS NEW PAGE--------------------------------------------------------*/
        

        root.setTop(topRightBox);
        root.setCenter(centerContent);

        stage.show();
    }
}
