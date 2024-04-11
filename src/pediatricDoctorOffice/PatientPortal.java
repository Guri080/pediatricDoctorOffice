package pediatricDoctorOffice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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
    
    PatientLogin p1 = new PatientLogin();
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
        String pID = p1.getpID();
        Label intro = new Label("Hello, " + pID);
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
        //Old Code
        
//        ScrollPane scrollPane = new ScrollPane();
//        VBox visitsBox = new VBox(10);
//        scrollPane.setContent(visitsBox);
//        scrollPane.setFitToWidth(true);
//        scrollPane.setPrefHeight(HEIGHT / 2);

        /*------------ADDING ENTRY NEEDS WORK--------------------------------------------------------*/
        
        TextArea visitEntry = new TextArea();
        visitEntry.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        visitEntry.setPadding(new Insets(10));
        centerContent.getChildren().addAll(intro, patientInfoBox, visitEntry);
        
        //get file using pID
        // read visits
        String fp = "src/PatientData/" + pID + "_data";
        
        try {
            FileReader reader  = new FileReader(fp);
            BufferedReader r = new BufferedReader(reader);
            String line;
            System.out.println("reading " +fp);
            String lastLine = null;
            while((line = r.readLine()) != null) {
            	System.out.println(line);
            	if (line != null) {
                    lastLine = line;
                }
                line = reformat(line);
                visitEntry.appendText(line + "\n");
                
                
            }
            if(lastLine != null) {
            	System.out.println("ll" + lastLine);
            	String[] info = lastLine.split(",");
            	for(int i = 0; i < info.length; i++) {
            		System.out.println(info[i]);
            	}
                heightLabel.setText("Height: " + info[1]);
                tempLabel.setText("Temperature: " + info[2]);
                ageLabel.setText("Age: " + info[3]);
                bpLabel.setText("Blood Pressure: " + info[4]);
            }
            
            
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        
        /*------------MESSAGES BUTTON OPENS NEW PAGE--------------------------------------------------------*/

        root.setTop(topRightBox);
        root.setCenter(centerContent);

        stage.show();
    }

    //Puts information into a readable format for the patient

    private String reformat(String line) {
        String[] input = line.split(",");
        String[] titles = {"Visit Date: ", "Height: ", "Temperature: ", "Age: ", "Blood Pressure: ", "Nurse Notes: ", ""};
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < input.length; i++) {
            if(i < titles.length) {
                if(i == input.length - 1 && input[i].isEmpty()) {
                    output.append(titles[i]).append(" ");
                } else {
                    output.append(titles[i]).append(input[i]).append(" ");
                }
            }
        }
        return output.toString();
    }
}
