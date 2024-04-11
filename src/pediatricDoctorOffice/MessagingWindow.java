package pediatricDoctorOffice;

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

    private final TextArea messageContent = new TextArea();

    public void display() {
        Stage stage = new Stage();
        stage.setTitle("Messages");
        BorderPane layout = new BorderPane();
        
        Button logOutButton = new Button("Log Out");
        Button sendNewMessageButton = new Button("Send New Message");
        HBox topMenu = new HBox(10, logOutButton, sendNewMessageButton);
        layout.setTop(topMenu);
        
        sendNewMessageButton.setOnAction(e -> NewMessageWindow.display());

        /*-------TABLE LAYOUT------------------------------------------------------------------------*/
        
        TableView<Message> table = new TableView<>();
        TableColumn<Message, String> fromColumn = new TableColumn<>("From");
        fromColumn.setMinWidth(100);
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("from"));
        
        TableColumn<Message, String> toColumn = new TableColumn<>("To");
        toColumn.setMinWidth(100);
        toColumn.setCellValueFactory(new PropertyValueFactory<>("to"));
        
        TableColumn<Message, Void> viewColumn = new TableColumn<>("View");
        viewColumn.setCellFactory(new Callback<TableColumn<Message, Void>, TableCell<Message, Void>>() {
            public TableCell<Message, Void> call(final TableColumn<Message, Void> param) {
                return new TableCell<Message, Void>() {
                    private final Button viewButton = new Button("View");

                    {
                        viewButton.setOnAction(event -> {
                            Message msg = getTableView().getItems().get(getIndex());
                            messageContent.setText(msg.getContent());
                        });
                    }

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

        table.getColumns().addAll(fromColumn, toColumn, viewColumn);

        /*----------CREATES MESSAGE IN ROW-------------------------------------------------------------------------*/
        
        Message sampleMessage = new Message("joe mama", "you", "whats up gang");
        Message sampleMessage2 = new Message("yo papa", "joe mama", "test");
        table.getItems().addAll(sampleMessage, sampleMessage2);

        VBox tableContainer = new VBox(table);
        layout.setLeft(tableContainer);

        messageContent.setText("click view message");
        messageContent.setEditable(false);
        layout.setRight(messageContent);

        Scene scene = new Scene(layout, 700, 400);
        stage.setScene(scene);
        stage.show();
    }
    
    /*---------MESSAGE CLASS---------------------------------------------------------------*/
    //@guri use thisn for when you populate information from the backend so it will go into the table
    
    public static class Message {
        private String from;
        private String to;
        private String content;

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
