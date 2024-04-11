package pediatricDoctorOffice;

// Import statements for JavaFX components required for creating the GUI
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MessagingWindow {

    // TextArea for displaying the content of a selected message
    private final TextArea messageContent = new TextArea();

    public void display() {
        // Create a new stage for the messaging window
        Stage stage = new Stage();
        stage.setTitle("Messages");
        BorderPane layout = new BorderPane();

        // Buttons and their container at the top
        Button logOutButton = new Button("Log Out");
        Button sendNewMessageButton = new Button("Send New Message");
        HBox topMenu = new HBox(10, logOutButton, sendNewMessageButton);
        layout.setTop(topMenu);

         // Action for the "Send New Message" button
        sendNewMessageButton.setOnAction(e -> NewMessageWindow.display());

        /*-------TABLE LAYOUT------------------------------------------------------------------------*/
        // Setup for displaying messages in a table format
        TableView<Message> table = new TableView<>();

         // Column for the sender
        TableColumn<Message, String> fromColumn = new TableColumn<>("From");
        fromColumn.setMinWidth(100);
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("from")); // Binding to "from" property of Message

         // Column for the recipient
        TableColumn<Message, String> toColumn = new TableColumn<>("To");
        toColumn.setMinWidth(100);
        toColumn.setCellValueFactory(new PropertyValueFactory<>("to")); // Binding to "to" property of Message

         // Column for the view button in each row
        TableColumn<Message, Void> viewColumn = new TableColumn<>("View");
        viewColumn.setCellFactory(new Callback<TableColumn<Message, Void>, TableCell<Message, Void>>() {
            public TableCell<Message, Void> call(final TableColumn<Message, Void> param) {
                return new TableCell<Message, Void>() 
                {
                    private final Button viewButton = new Button("View");
                    {
                         // Action for the view button to display message content
                        viewButton.setOnAction(event -> {
                            Message msg = getTableView().getItems().get(getIndex());
                            messageContent.setText(msg.getContent());
                        });
                    }
                    
                     // Method to update or clear the cell's content
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(viewButton);
                        }
                    }
                };
            }
        });

         // Add columns to table
        table.getColumns().addAll(fromColumn, toColumn, viewColumn);

        /*----------CREATES MESSAGE IN ROW-------------------------------------------------------------------------*/
        // Sample messages to display in the table
        Message sampleMessage = new Message("joe mama", "you", "whats up gang");
        Message sampleMessage2 = new Message("yo papa", "joe mama", "test");
        table.getItems().addAll(sampleMessage, sampleMessage2);

        VBox tableContainer = new VBox(table);
        layout.setLeft(tableContainer); // Set the table on the left side of the layout

        // Setup for the message content area
        messageContent.setText("click view message");
        messageContent.setEditable(false);// Make the text area non-editable
        layout.setRight(messageContent); // Place the message content on the right

        // Final scene setup
        Scene scene = new Scene(layout, 700, 400);
        stage.setScene(scene);
        stage.show();
    }
    
    /*---------MESSAGE CLASS---------------------------------------------------------------*/
    // Message class used to represent messages in the system
    public static class Message {
        private String from;
        private String to;
        private String content;

         // Constructor and getters
        public Message(String from, String to, String content) {
            this.from = from;
            this.to = to;
            this.content = content;
        }
        public String getFrom() {
            return from;
        }
        public String getTo() {
            return to;
        }
        public String getContent() {
            return content;
        }
    }
}
