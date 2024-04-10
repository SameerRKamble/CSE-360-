import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PatientView  {

    private TextArea messageArea;
    BorderPane root;


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

        Button sendMessageButton = new Button("Send");
        sendMessageButton.setOnAction(e -> sendMessage(messageInput.getText()));

        Button changeInfoButton = new Button("Change Info");
        changeInfoButton.setOnAction(e -> System.out.println("Change Info button clicked."));

        Button viewHistoryButton = new Button("View History");
        viewHistoryButton.setOnAction(e -> System.out.println("View History button clicked."));

        HBox messageBox = new HBox(10, messageInput, sendMessageButton);
        VBox buttonBar = new VBox(10, changeInfoButton, viewHistoryButton);
        
        // Styling
        messageArea.setStyle("-fx-background-color: white; -fx-padding: 10;");
        messageBox.setStyle("-fx-background-color: #ccd6dd; -fx-padding: 10;");
        buttonBar.setStyle("-fx-background-color: #ccd6dd; -fx-padding: 10;");

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
        }
    }
}
