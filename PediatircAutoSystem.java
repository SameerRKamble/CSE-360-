import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.scene.text.*;
import java.io.*;

public class PediatircAutoSystem extends Application {
	
    private NurseView NurseView;
    private DoctorView DoctorView;
    private PatientView PatientView;
	
	// UI Components
	//main
    private Button btnNurseView;
    private Button btnDoctorView;
    private Button btnpatientView;
    private Label MainTitle;
    private Scene mainScene; 
    private Stage primaryStage;

	private Button viewPastButton;
    private Label LDoctorNotes;
    private Label LPersonalInformation;

    private TextArea TxDoctorNotes;
    private TextArea TxPersonalInformation;

	BorderPane borderPane;
	VBox leftVBox;
	ScrollPane personalInfoScrollPane;
	VBox rightVBox;
	ScrollPane doctorNotesScrollPane;
	HBox centerHBox;
	VBox bottomVBox;


	Button goBackButton;

	boolean patientExists = false;

    
	// Scene and Stage
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		//Main UI
		MainTitle = new Label("Welcome to Pediatric Doctor’s Office Automation System");
		btnNurseView = new Button("Nurse View");
		btnDoctorView = new Button("Doctor View");
		btnpatientView = new Button("Patient View");
        
		//set button size
		btnNurseView.setPrefWidth(200);
		btnNurseView.setPrefHeight(80);
		btnDoctorView.setPrefWidth(200);
		btnDoctorView.setPrefHeight(80);
		btnpatientView.setPrefWidth(200);
		btnpatientView.setPrefHeight(80);
		
		//set button color
		btnNurseView.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
		btnDoctorView.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
		btnpatientView.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
		// Attach event handlers to buttons
		btnNurseView.setOnAction(event -> openNurseView(primaryStage));
		btnDoctorView.setOnAction(event -> openDoctorView(primaryStage));
		btnpatientView.setOnAction(event -> openPatientView(primaryStage));
		VBox buttonSection = new VBox(20, btnNurseView, btnDoctorView, btnpatientView);
		buttonSection.setAlignment(Pos.CENTER);
		MainTitle.setAlignment(Pos.CENTER);
		
		//Layout
		VBox mainLayout = new VBox(50,MainTitle,buttonSection);
		// Set padding around the edges (top, right, bottom, left)
		mainLayout.setPadding(new Insets(70, 150, 100, 120)); 
        
        // Scene and Stage
        mainScene = new Scene(mainLayout, 600, 400);
        primaryStage.setScene(mainScene);
        primaryStage.show();
	}
	
	private void openNurseView(Stage stage) {
		NurseView NurseView = new NurseView(this);
        Scene scene = new Scene(NurseView.getRoot(), 900, 650);
        stage.setScene(scene);
		stage.show();
    }

    private void openDoctorView(Stage stage) {
    	DoctorView DoctorView = new DoctorView(this);
		 Scene scene = new Scene(DoctorView.getRoot(), 700, 500);
		 stage.setScene(scene);
		 stage.show();
    }

    private void openPatientView(Stage stage) {
        PatientView patientView = new PatientView(this);
        Scene scene = new Scene(patientView.getRoot(), 800, 600);
        stage.setScene(scene);
		stage.show();
    }

	public void accessHistoryFile(String patientID) {
		String ID = patientID;
		String notes = "";
		String pp = "";
		
		String notesFile = patientID + "_Notes.txt";
		File file = new File(notesFile);

		if (!file.exists()) {
			showAlert("Patient " + patientID + " haven't been through the physical test");
			try {

				file.createNewFile(); // Create the file if it doesn't exist

			} catch (IOException e) {
				e.printStackTrace();

			}
		
		}
		
		//read from notes file
		try (BufferedReader reader = new BufferedReader(new FileReader(notesFile))) {
			String line;
			while ((line = reader.readLine()) != null) {
				notes = notes + line + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//read from patient file
		try (BufferedReader reader = new BufferedReader(new FileReader(patientID + "_PatientInfo.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				pp = pp + line + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Show the history UI
		showHistoryUI(notes, pp);
	}

	private void showHistoryUI(String notes, String pp) {
		LDoctorNotes = new Label("Doctor's notes:");
		LDoctorNotes.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		LDoctorNotes.setPadding(new Insets(10));
		LPersonalInformation = new Label("Patient Information:");
		LPersonalInformation.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		LPersonalInformation.setPadding(new Insets(10));
		TxDoctorNotes = new TextArea(notes);
		TxDoctorNotes.setFont(Font.font("Arial", 12));
		TxDoctorNotes.setPrefHeight(600);
		TxPersonalInformation = new TextArea(pp);
		TxPersonalInformation.setFont(Font.font("Arial", 12));
		TxPersonalInformation.setPrefHeight(600);
	
		borderPane = new BorderPane();
	
		// Create left VBox
		leftVBox = new VBox();
		leftVBox.setPadding(new Insets(10));
		leftVBox.setAlignment(Pos.TOP_CENTER);
		leftVBox.getChildren().addAll(LPersonalInformation, TxPersonalInformation);
	
		// Wrap the TextArea in a ScrollPane
		personalInfoScrollPane = new ScrollPane(leftVBox);
		personalInfoScrollPane.setFitToWidth(true);
		personalInfoScrollPane.setFitToHeight(true);
		personalInfoScrollPane.setPrefViewportHeight(400); // Set preferred height
	
		// Create right VBox
		rightVBox = new VBox();
		rightVBox.setPadding(new Insets(10));
		rightVBox.setAlignment(Pos.TOP_CENTER);
		rightVBox.getChildren().addAll(LDoctorNotes, TxDoctorNotes);
	
		// Wrap the TextArea in a ScrollPane
		doctorNotesScrollPane = new ScrollPane(rightVBox);
		doctorNotesScrollPane.setFitToWidth(true);
		doctorNotesScrollPane.setFitToHeight(true);
		doctorNotesScrollPane.setPrefViewportHeight(400); // Set preferred height
	
		// Create a center HBox and add the left and right VBoxes to it
		centerHBox = new HBox(personalInfoScrollPane, doctorNotesScrollPane);
		centerHBox.setAlignment(Pos.CENTER);
	
		// Set the center HBox in the BorderPane
		borderPane.setCenter(centerHBox);
	
		// Create a VBox for the "Go Back" button
		bottomVBox = new VBox();
		bottomVBox.setAlignment(Pos.CENTER);
		bottomVBox.setPadding(new Insets(10));
	
		goBackButton = new Button("Go Back");
		borderPane.setBottom(bottomVBox);
		goBackButton.setOnAction(e -> showMainMenu());
	
		bottomVBox.getChildren().add(goBackButton);
	
		// Show the scene
		Scene historyScene = new Scene(borderPane, 800, 600);
		primaryStage.setScene(historyScene);
		primaryStage.show();
	}
	
	
	

	public boolean existsPatientID(String searchString) {
		patientExists = false; // Initialize patientExists to false
		try (BufferedReader reader = new BufferedReader(new FileReader("IDs.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.equals(searchString)) {
					patientExists = true; // Set patientExists to true if string is found
					break; // Exit the loop once string is found
				}
			}
		} catch (IOException e) {
			e.printStackTrace(); // Handle file reading exception
		}
		//System.out.println(patientExists); // Print the value of patientExists
		return patientExists;
	}

	public void sendMessage(String patientID, String who) {
		
		String fileName = patientID + "_Messages.txt";
		File file = new File(fileName);

		TextArea textArea = new TextArea();
		textArea.setPrefRowCount(10);
		textArea.setEditable(false);
	
		if (file.exists()) {
			// Read and display existing messages from the file
			readMessagesFromFile(file, textArea);
		}

		
		Button addButton = new Button("Send");
		TextField txMessage = new TextField();
		addButton.setOnAction(e -> {
			String newContent = txMessage.getText();
			if (!newContent.isEmpty()) {
				try (FileWriter writer = new FileWriter(file, true)) {
					writer.write(who + newContent + "\n");
					textArea.appendText(who + newContent + "\n"); // Append the new message to the TextArea
					txMessage.clear(); // Clear the input field after sending
				} catch (IOException ex) {
					ex.printStackTrace();
					showAlert("Failed to send message");
				}
			} else {
				showAlert("Please enter some message!");
			}
		});

		VBox vbox = new VBox(10);
		vbox.setPadding(new Insets(10));
		vbox.getChildren().addAll(new Label("Message Box:"), textArea, txMessage, addButton);

		Stage popupStage = new Stage();
		popupStage.setTitle("Message Viewer");
		popupStage.setScene(new Scene(vbox, 400, 300));
		popupStage.show();
	}
	
	private void readMessagesFromFile(File file, TextArea textArea) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			StringBuilder existingMessages = new StringBuilder();
	
			while ((line = reader.readLine()) != null) {
				existingMessages.append(line).append("\n");
			}
	
			reader.close();
			textArea.setText(existingMessages.toString());
		} catch (IOException e) {
			e.printStackTrace();
			showAlert("Failed to read messages from file!");
		}
	}
	
	private void showAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
    
    public void showMainMenu() {
    	primaryStage.setScene(mainScene); // Reuse the main scene
        primaryStage.show();
	}

	
	// Main method to launch the application
	 public static void main(String[] args) {
	     launch(args);
	 }
	
}
