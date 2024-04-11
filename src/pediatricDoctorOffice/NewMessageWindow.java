package pediatricDoctorOffice;

//Imports
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewMessageWindow {

    public static void display() {
        Stage window = new Stage(); // Creates a new stage for the window
        window.initModality(Modality.APPLICATION_MODAL); // Sets the window to block input events to other windows
        window.setTitle("Send New Message"); // Sets the title of the window
        
        // TextField for entering the message recipient
        TextField toField = new TextField();
        toField.setPromptText("Recipient");

        // TextArea for entering the message content
        TextArea messageContent = new TextArea();
        messageContent.setPromptText("Write your message here");

       // Button to send the message
        Button sendButton = new Button("Send");
        sendButton.setOnAction(event -> {
            System.out.println("send button clicked");
            window.close(); // Closes the window after clicking send
        });

        // Layout setup
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(toField, messageContent, sendButton);

        // Scene setup
        Scene scene = new Scene(layout, 400, 250);
        window.setScene(scene);
        window.showAndWait();
        
    }
}
