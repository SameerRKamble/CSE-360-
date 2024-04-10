import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PatientView {

    private TextArea messageArea;
    private BorderPane root;
    private PediatircAutoSystem mainApp;
    
    public PatientView(PediatircAutoSystem mainApp) {
        this.mainApp = mainApp;
        initializeUI();
    }

    private void initializeUI() {
        root = new BorderPane();
        root.setPrefSize(400, 600);

        messageArea = new TextArea();
        messageArea.setEditable(false);
        messageArea.setWrapText(true);

        TextField messageInput = new TextField();
        messageInput.setPromptText("Type your message...");
        messageInput.setPrefWidth(280); 

        Button sendMessageButton = new Button("Send");
        sendMessageButton.setPrefSize(80, 40); 
        sendMessageButton.setOnAction(e -> sendMessage(messageInput.getText()));

        Button changeInfoButton = new Button("Change Info");
        changeInfoButton.setPrefSize(120, 40); 
        changeInfoButton.setOnAction(e -> System.out.println("Change Info button clicked."));

        Button viewHistoryButton = new Button("View History");
        viewHistoryButton.setPrefSize(120, 40); 
        viewHistoryButton.setOnAction(e -> System.out.println("View History button clicked."));

        HBox messageBox = new HBox(10, messageInput, sendMessageButton);
        messageBox.setStyle("-fx-background-color: #ccd6dd; -fx-padding: 10;");
        messageBox.setSpacing(10); 
        messageBox.setPrefHeight(50); 

        VBox buttonBar = new VBox(10, changeInfoButton, viewHistoryButton);
        buttonBar.setStyle("-fx-background-color: #ccd6dd; -fx-padding: 10;");
        buttonBar.setSpacing(10); 

        root.setCenter(messageArea);
        root.setBottom(messageBox);
        root.setRight(buttonBar);
    }

    public BorderPane getRoot() {
        return root;
    }

    private void sendMessage(String message) {
        if (!message.isEmpty()) {
            messageArea.appendText(message + "\n");
            System.out.println("Message sent: " + message);
            // Clear the input after sending
            messageInput.clear();
        }
    }
