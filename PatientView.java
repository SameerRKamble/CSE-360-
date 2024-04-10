import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.*;
import javafx.geometry.*;

public class PatientView {

    private TextArea messageArea;
    BorderPane root;

    private TextField patientIdInput;  // TextField for patient ID input
    private Button submitButton;       // Button to submit patient ID
    private VBox inputLayout; 
    private VBox PatientView;

    private boolean isHistory = false;
	String PATIENTID;

    private PediatircAutoSystem mainApp;
    TextField messageInput;
    
    public PatientView(PediatircAutoSystem mainApp) {
    	this.mainApp = mainApp;
        initializeInputUI();
        initializeUI();
    }

    private void initializeInputUI() {
        // Patient ID input section   
        patientIdInput = new TextField();
        patientIdInput.setPromptText("Enter Patient ID");
        
        //button part
        submitButton = new Button("Load Patient Data");
        submitButton.setOnAction(event -> loadPatientData(patientIdInput.getText()));

        inputLayout = new VBox(20);
        inputLayout.setAlignment(Pos.CENTER);
        inputLayout.getChildren().addAll(new Label("Patient ID:"), patientIdInput, submitButton);
        PatientView = inputLayout;
    }

    private void initializeUI() {
        root = new BorderPane();
        root.setPrefSize(400, 600);

        messageArea = new TextArea();
        messageArea.setEditable(false);
        messageArea.setWrapText(true);

        messageInput = new TextField();
        messageInput.setPromptText("Type your message...");
        messageInput.setPrefWidth(280); // Adjust width as needed

        Button sendMessageButton = new Button("Send");
        sendMessageButton.setPrefSize(80, 40); // Make button larger
        sendMessageButton.setOnAction(e -> sendMessage(messageInput.getText()));

        Button changeInfoButton = new Button("Change Info");
        //changeInfoButton.setOnAction(e -> System.out.println("Change Info button clicked."));

        Button viewHistoryButton = new Button("View History");
        viewHistoryButton.setOnAction(e -> mainApp.accessHistoryFile(PATIENTID));

        HBox messageBox = new HBox(10, messageInput, sendMessageButton);
        messageBox.setStyle("-fx-background-color: #ccd6dd; -fx-padding: 10;");
        messageBox.setSpacing(10); // Adjust spacing as needed
        messageBox.setPrefHeight(50); // Adjust height as needed

        VBox buttonBar = new VBox(10, changeInfoButton, viewHistoryButton);
        buttonBar.setStyle("-fx-background-color: #ccd6dd; -fx-padding: 10;");
        buttonBar.setSpacing(10); // Adjust spacing as needed

        root.setCenter(messageArea);
        root.setBottom(messageBox);
        root.setRight(buttonBar);
    }

    private void loadPatientData(String patientID) {
        
        isHistory = mainApp.existsPatientID(patientID);
        PATIENTID = patientID;

        //if the id exists, then go to the mainlayout 
        if (isHistory) 
        {
            PatientView.getChildren().clear();
            PatientView.getChildren().add(root);  
        }
        else
        {
            showAlert("Patient ID not Found");
        }
 	
    }

    public VBox getRoot() {
        return PatientView;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void sendMessage(String message) {
        if (!message.isEmpty()) {
            messageArea.appendText(message + "\n");
            System.out.println("Message sent: " + message);
            // Clear the input after sending
            messageInput.clear();
        }
    }
}

    