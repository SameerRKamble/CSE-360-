import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.*;
import javafx.geometry.*;
import javafx.scene.text.*;

public class PatientView {

    private TextArea messageArea;
    BorderPane root;

    private TextField patientIdInput;  // TextField for patient ID input
    private Button submitButton;       // Button to submit patient ID
    private VBox inputLayout; 
    private VBox PatientView;
	private Label enterPatientID;

    private boolean isHistory = false;
	String PATIENTID;
    String fullName = "";

    private PediatircAutoSystem mainApp;
    TextArea messageInput;
    
    public PatientView(PediatircAutoSystem mainApp) {
    	this.mainApp = mainApp;
        initializeInputUI();
        initializeUI();
    }

    private void initializeInputUI() {
    	// Enter Patient ID Label
    	enterPatientID = new Label("Enter Patient ID:");
    	enterPatientID.setFont(new Font("Arial", 20));

        // Patient ID input section   
        patientIdInput = new TextField();
        patientIdInput.setPromptText("Enter Patient ID");
        
        // Submit button
        submitButton = new Button("Load Patient Data");
	    submitButton.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
	    submitButton.setPrefWidth(150);
	    submitButton.setPrefHeight(40);
        submitButton.setOnAction(event -> loadPatientData(patientIdInput.getText()));

        // Back button
        Button goBackButton = new Button("Go Back");
	    goBackButton.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
	    goBackButton.setPrefWidth(150);
	    goBackButton.setPrefHeight(40);
        goBackButton.setOnAction(e -> mainApp.showMainMenu());

        inputLayout = new VBox(20);
        inputLayout.setAlignment(Pos.CENTER);
        inputLayout.getChildren().addAll(enterPatientID, patientIdInput, submitButton, goBackButton);
        PatientView = inputLayout;
    }
	
    private void initializeUI() {
        root = new BorderPane();
        root.setPrefSize(400, 600);

        Label titleLabel = new Label("Welcome to Patient Messaging System");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        titleLabel.setPadding(new Insets(10));
        titleLabel.setAlignment(Pos.CENTER);
    
        messageArea = new TextArea();
        messageArea.setEditable(false);
        messageArea.setWrapText(true);
        messageArea.setPrefHeight(400);
        messageArea.setPadding(new Insets(10));
    
        messageInput = new TextArea();
        messageInput.setPromptText("Type your message...");
        messageInput.setPrefRowCount(1); // Set initial row count
        messageInput.setWrapText(true); // Enable text wrapping
        messageInput.setPrefHeight(50); // Adjust height as needed

        Button goBackButton = new Button("Go Back");
        goBackButton.setOnAction(e -> mainApp.showMainMenu());

        Button sendMessageButton = new Button("Send");
        sendMessageButton.setOnAction(e -> loadMessageFromFile(PATIENTID, messageInput.getText()));
    
        Button changeInfoButton = new Button("Change Info");
        changeInfoButton.setOnAction(e -> changeInfo(PATIENTID));
    
        Button viewHistoryButton = new Button("View History");
        viewHistoryButton.setOnAction(e -> mainApp.accessHistoryFile(PATIENTID));
    
        HBox messageBox = new HBox(10, sendMessageButton, messageInput);
        messageBox.setAlignment(Pos.CENTER);
        messageBox.setPadding(new Insets(10));
        messageBox.setStyle("-fx-background-color: #f0f0f0; -fx-border-width: 1; -fx-border-color: #a0a0a0;");
        messageBox.setSpacing(10);
    
        VBox buttonBar = new VBox(10, changeInfoButton, viewHistoryButton, goBackButton);
        buttonBar.setAlignment(Pos.CENTER);
        buttonBar.setPadding(new Insets(10));
        //buttonBar.setStyle("-fx-background-color: #f0f0f0; -fx-border-width: 1; -fx-border-color: #a0a0a0;");
        buttonBar.setSpacing(10);
    
        VBox contentBox = new VBox(titleLabel, messageArea, messageBox);
        contentBox.setSpacing(10);
        contentBox.setPadding(new Insets(10));
    
        root.setCenter(contentBox);
        root.setRight(buttonBar);
    }
    

    private void changeInfo(String patientID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(patientID + "_PatientInfo.txt"))) {
            StringBuilder fileContent = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Email:")) {
                    String newEmail = showInputDialog("Enter new email:");
                    fileContent.append("Email: ").append(newEmail).append("\n");
                } else if (line.startsWith("Phone Number:")) {
                    String newPhoneNumber = showInputDialog("Enter new phone number:");
                    fileContent.append("Phone Number: ").append(newPhoneNumber).append("\n");
                } else {
                    fileContent.append(line).append("\n");
                }
            }

            try (FileWriter writer = new FileWriter(patientID + "_PatientInfo.txt")) {
                writer.write(fileContent.toString());
                showAlert("Info updated successfully!");
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Failed to update info!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String showInputDialog(String message) {
        TextField textField = new TextField();
        Label label = new Label(message);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.add(label, 0, 0);
        grid.add(textField, 1, 0);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getDialogPane().setContent(grid);
        alert.showAndWait();

        return textField.getText();
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

    
    private void loadMessageFromFile(String patientID, String message) {
        String fileName = patientID + "_Messages.txt";
        File file = new File(fileName);

        try {
            if (!file.exists()) {
                file.createNewFile(); // Create the file if it doesn't exist
                // No existing messages to display
            } else {
                // Read and display existing messages from the file
                readMessagesFromFile(file);
            }

            // Append the new message to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write("Patient: " + message + "\n");
            writer.close();

            // Append the new message to the TextArea
            sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
            sendMessage("Failed to write message to file!");
        }
    }

    private void readMessagesFromFile(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder existingMessages = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                existingMessages.append(line).append("\n");
            }

            reader.close();
            messageArea.setText(existingMessages.toString());
        } catch (IOException e) {
            e.printStackTrace();
            sendMessage("Failed to read messages from file!");
        }
    }

    private void sendMessage(String message) {
        if (!message.isEmpty()) {
            messageArea.appendText("Patient: " + message + "\n");
            // Clear the input after sending
            messageInput.clear();
        }
    }
}

    
