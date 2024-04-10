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
        HBox topRightBox = new HBox(messageButton);
        topRightBox.setAlignment(Pos.TOP_RIGHT);
        topRightBox.setPadding(new Insets(10)); // Adjust padding as needed

        /*------------PATIENT INFORMATION---------------------------------------------------------*/
        
        loadPatientData(visitsBox, patientName, patientDOB);

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

        root.setTop(topRightBox);
        root.setCenter(centerContent);

        stage.show();
    }

private void loadPatientData(VBox visitsBox, String patientName, String patientDOB) {
    String patientFileName = getPatientFileName(patientName, patientDOB);
    File patientFile = new File(patientFileName);

    if (patientFile.exists()) {
        try (BufferedReader reader = new BufferedReader(new FileReader(patientFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming each line is a separate visit entry with format: height, temperature, age, blood pressure, notes
                String[] visitData = line.split(",");

                // Create a VBox for this visit
                VBox visitEntryBox = new VBox(5);
                visitEntryBox.setPadding(new Insets(5));
                visitEntryBox.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

                // Check to ensure we have all expected data points
                if (visitData.length >= 5) {
                    // Assign each data point to a label with a descriptive prefix
                    Label heightLabel = new Label("Height: " + visitData[0] + " cm");
                    Label tempLabel = new Label("Temperature: " + visitData[1] + " °C");
                    Label ageLabel = new Label("Age: " + visitData[2] + " years");
                    Label bpLabel = new Label("Blood Pressure: " + visitData[3]);
                    Label notesLabel = new Label("Notes: " + visitData[4]);

                    // Add all the labels to the visit entry box
                    visitEntryBox.getChildren().addAll(heightLabel, tempLabel, ageLabel, bpLabel, notesLabel);
                } else {
                    // Handle case where data is missing or not as expected
                    visitEntryBox.getChildren().add(new Label("Incomplete visit data."));
                }

                // Add the visit entry box to the main visits box
                visitsBox.getChildren().add(visitEntryBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        visitsBox.getChildren().add(new Label("No visit data available for this patient."));
    }
}

}
