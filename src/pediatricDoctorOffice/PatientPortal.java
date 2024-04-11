package pediatricDoctorOffice;

// Import statements for JavaFX components, IO operations, and styling.
import java.io.BufferedReader;
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

// Main class definition for the patient portal interface.
public class PatientPortal {
    public static final int WIDTH = 700, HEIGHT = 450; // Constants for the window size.

    // Reference to the PatientLogin class to access the patient ID.
    PatientLogin p1 = new PatientLogin();

    // Initializes and displays the patient portal UI.
    public void start(Stage stage) {
        stage.setTitle("Patient Portal");// Title of the window.
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);

         // Fonts for the UI elements.
        Font largeBoldFont = Font.font("Arial", FontWeight.BOLD, 30);
        Font largeFont = Font.font("Arial", 15);

        // VBox for center content with padding and spacing.
        VBox centerContent = new VBox(10); 
        centerContent.setPadding(new Insets(20)); 

        // Label to greet the patient with their ID.
        String pID = p1.getpID(); // Fetches the patient ID from PatientLogin.
        Label intro = new Label("Hello, " + pID);
        intro.setFont(largeBoldFont);

        // Button to navigate to the messaging functionality.
        Button messageButton = new Button("Message");
        messageButton.setFont(largeFont);
        messageButton.setOnAction(e -> new MessagingWindow().display());
        HBox topRightBox = new HBox(messageButton);
        topRightBox.setAlignment(Pos.TOP_RIGHT);
        topRightBox.setPadding(new Insets(10));
        
        // VBox for displaying patient information.
        VBox patientInfoBox = new VBox(5);
        Label heightLabel = new Label("Height: ");
        Label tempLabel = new Label("Temperature: ");
        Label ageLabel = new Label("Age: ");
        Label bpLabel = new Label("Blood Pressure: ");
        patientInfoBox.getChildren().addAll(heightLabel, tempLabel, ageLabel, bpLabel);

        // TextArea for displaying visit entries with a border.
        TextArea visitEntry = new TextArea();
        visitEntry.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        visitEntry.setPadding(new Insets(10));
        centerContent.getChildren().addAll(intro, patientInfoBox, visitEntry);
        
        // Attempts to read patient data from a file and display it.
        String fp = "src/PatientData/" + pID + "_data";    // File path based on patient ID.    
        try {
            FileReader reader  = new FileReader(fp);
            BufferedReader r = new BufferedReader(reader);
            String line;
            System.out.println("reading " +fp);
            String lastLine = null;
            while((line = r.readLine()) != null) {
            	System.out.println(line);
            	if (line != null) {
                    lastLine = line; // Keeps track of the last line read.
                }
                line = reformat(line); // Reformats the line for display.
                visitEntry.appendText(line + "\n");  // Appends the reformatted line to the TextArea.                
            }
            // Updates the patient information labels with the last entry's data
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
            e.printStackTrace();// Prints any IO exception stack trace.
        }
        
        
         // Configures the layout of the root container and shows the stage.
        root.setTop(topRightBox);
        root.setCenter(centerContent);
        stage.show();
    }

   // Reformats a string line by appending titles to the respective data fields for readability.
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
        return output.toString(); // Returns the reformatted string.
    }
}
