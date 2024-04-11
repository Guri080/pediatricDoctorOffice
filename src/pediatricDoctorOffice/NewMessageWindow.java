package pediatricDoctorOffice;

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
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Send New Message");
        
        /*------------TO WHO--------------------------------------------------------------*/

        TextField toField = new TextField();
        toField.setPromptText("Recipient");

        /*---------MESSAGE CONTENT----------------------------------------------------------------*/
        
        TextArea messageContent = new TextArea();
        messageContent.setPromptText("Write your message here");

        /*---------SEND BUTTON---------------------------------------------------------*/
        
        Button sendButton = new Button("Send");
        sendButton.setOnAction(event -> {
            System.out.println("send button clicked");
            window.close();
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(toField, messageContent, sendButton);

        Scene scene = new Scene(layout, 400, 250);
        window.setScene(scene);
        window.showAndWait();
        
    }
}
