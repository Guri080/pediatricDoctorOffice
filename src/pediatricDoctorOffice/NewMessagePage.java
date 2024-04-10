package pediatricDoctorOffice;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewMessagePage {

    public void start(Stage primaryStage) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL); // Use the stage passed in instead of creating a new one
        stage.setTitle("New Message");

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 700, 450); // WIDTH and HEIGHT constants can be replaced directly if they're not used elsewhere
        stage.setScene(scene);

        Font largeBoldFont = Font.font("Arial", FontWeight.BOLD, 30);
        Font largeFont = Font.font("Arial", 15);

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        // Title
        Label titleLabel = new Label("Compose New Message");
        titleLabel.setFont(largeBoldFont);

        // Fields
        TextField senderField = new TextField();
        senderField.setFont(largeFont);
        senderField.setPromptText("Sender");

        TextField receiverField = new TextField();
        receiverField.setFont(largeFont);
        receiverField.setPromptText("Receiver");

        TextArea messageArea = new TextArea();
        messageArea.setFont(largeFont);
        messageArea.setPromptText("Your message here...");
        
        // Send Button
        Button sendButton = new Button("Send");
        sendButton.setFont(largeFont);
        sendButton.setOnAction(event -> {
            System.out.println("Message sent!");
            // Here, you can implement the actual logic to send a message
            stage.close(); // Closes the dialog after sending the message
        });
        
        Button logOutButton = new Button("Log Out");
        logOutButton.setFont(largeFont);
        logOutButton.setOnAction(event -> {
            // Implement logout logic here
            stage.close(); // For simplicity, just close this stage
            // Potentially redirect to login screen or perform cleanup actions
        });

        layout.getChildren().addAll(titleLabel, senderField, receiverField, messageArea, sendButton, logOutButton);
        root.getChildren().add(layout);

        stage.showAndWait();
    }
}
