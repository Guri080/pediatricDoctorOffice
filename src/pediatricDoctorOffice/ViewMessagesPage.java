package pediatricDoctorOffice;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ViewMessagesPage {
    public static final int WIDTH = 700, HEIGHT = 450;

    public void start(Stage stage) {
        stage.setTitle("Message Inbox");

        VBox layout = new VBox(20); // Increased spacing for better visibility
        layout.setAlignment(Pos.CENTER);

        // Title
        Label titleLabel = new Label("Message Inbox");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        // List for messages
        ListView<String> messageList = new ListView<>();
        messageList.getItems().addAll(
            "Sender: Dr. Smith, Receiver: John Doe, Message: Your next appointment is scheduled for...",
            "Sender: Jane Doe, Receiver: Dr. Johnson, Message: I have a question about my prescription.",
            "Sender: Patient Care, Receiver: Alice Smith, Message: Reminder: Annual check-up is due."
            // Dynamically load messages or add more sample messages here
        );

        // Buttons
        Button newMessageButton = new Button("Compose New Message");
        newMessageButton.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        newMessageButton.setOnAction(event -> {
            // Assume NewMessagePage has been adjusted to also use a start method
            new NewMessagePage().start(new Stage()); // This line might need adjustment based on your NewMessagePage implementation
        });

        Button logOutButton = new Button("Log Out");
        logOutButton.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        logOutButton.setOnAction(event -> {
            stage.close(); // Placeholder for actual log out logic
        });

        layout.getChildren().addAll(titleLabel, messageList, newMessageButton, logOutButton);

        Scene scene = new Scene(layout, 700, 450);
        stage.setScene(scene);
        stage.show();
    }
}
