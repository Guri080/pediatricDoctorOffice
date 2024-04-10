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
    public static final int WIDTH = 700, HEIGHT = 450;

    public static void display() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("New Message");
        StackPane root = new StackPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
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
            // Here you can add actual sending logic
            stage.close(); // Close the dialog after sending the message
        });

        layout.getChildren().addAll(titleLabel, senderField, receiverField, messageArea, sendButton);
        root.getChildren().add(layout);

        stage.showAndWait();
    }
}
