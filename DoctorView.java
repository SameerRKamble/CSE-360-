import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import javafx.geometry.*;

public class DoctorView{
	
	//CTScanView
    private Label docNote;
    private Label meds;
    private Label immunizations;
    private TextArea docNoteText;
    private TextArea medstext;
    private TextArea immunizationsText;
    private Button save;
    private Button sendMeds;
    private Button viewHistory;
    private Button saveImmunizations;
	private Button viewMessage;

	private TextField patientIdInput;  // TextField for patient ID input
    private Button submitButton;       // Button to submit patient ID
    private VBox inputLayout; 
    private VBox DoctorView;

	private boolean isHistory = false;
	String PATIENTID;

    //VBox mainLayout;
    BorderPane mainLayout;
    
    //layout
    private PediatircAutoSystem mainApp;
    
    public DoctorView(PediatircAutoSystem mainApp) {
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
        DoctorView = inputLayout;
    }
    

    private void initializeUI() {
		//Label
		docNote = new Label("Doctor Notes:");
		meds = new Label("Prescription:");
		immunizations = new Label("Immunizations:");

		//Text Area
		docNoteText = new TextArea();
		medstext = new TextArea();
		immunizationsText = new TextArea();

		docNoteText.setPrefWidth(400);
		docNoteText.setPrefHeight(50);
		medstext.setPrefWidth(400);
		medstext.setPrefHeight(50);
		immunizationsText.setPrefWidth(400);
		immunizationsText.setPrefHeight(50);

		//Button
		save = new Button("Save");
		save.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
		save.setPrefWidth(100);
		save.setPrefHeight(50);
		save.setOnAction(e -> saveDoctorNotes(PATIENTID));

		sendMeds = new Button("send meds");
		sendMeds.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
		sendMeds.setPrefWidth(100);
		sendMeds.setPrefHeight(50);
		sendMeds.setOnAction(e -> sendPharmacy(medstext.getText()));

		viewHistory = new Button("View his");
		viewHistory.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
		viewHistory.setPrefWidth(100);
		viewHistory.setPrefHeight(50);

		viewHistory.setOnAction(e -> mainApp.accessHistoryFile(PATIENTID));

		viewMessage = new Button("View message");
		viewMessage.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
		viewMessage.setPrefWidth(100);
		viewMessage.setPrefHeight(50);
		viewMessage.setOnAction(e -> sendPharmacy(medstext.getText()));
		viewMessage.setOnAction(e -> mainApp.sendMessage(PATIENTID));

		
		// Layout
		mainLayout = new BorderPane();

		VBox doctorNotesVBox = new VBox(docNote, docNoteText);
		doctorNotesVBox.setSpacing(10);

		VBox medicationsVBox = new VBox(meds, medstext);
		medicationsVBox.setSpacing(10);
		
		VBox immunizationsVBox = new VBox(immunizations, immunizationsText);
		immunizationsVBox.setSpacing(10);

		mainLayout.setCenter(new VBox(doctorNotesVBox, medicationsVBox, immunizationsVBox));
		mainLayout.setPadding(new Insets(20)); // Add padding around the VBox

		HBox bottomBox = new HBox();
		bottomBox.setSpacing(10);
		bottomBox.setPadding(new Insets(10, 0, 0, 0)); // Add padding to the bottom

		Button goBackButton = new Button("Go Back");
		goBackButton.setOnAction(e -> mainApp.showMainMenu());
		bottomBox.getChildren().addAll(goBackButton, save, sendMeds, viewHistory, viewMessage);

		mainLayout.setBottom(bottomBox);
	}

	//get root for the main button to use
    public VBox getRoot() {
        return DoctorView;
    }

	private void sendPharmacy(String message)
	{
		String pharmacy = "";
		try (BufferedReader reader = new BufferedReader(new FileReader(PATIENTID + "_PatientInfo.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
                if (line.startsWith("Pharmacy:")) {
                    pharmacy = line.substring(line.indexOf(":") + 1);
                    break;
				}
			}
		}
			catch (IOException e) {
			e.printStackTrace();
		}

		showAlert("Medication send to Pharmacy " + pharmacy);
	}


	private void loadPatientData(String patientID) {
        
        isHistory = mainApp.existsPatientID(patientID);
        PATIENTID = patientID;

        //if the id exists, then go to the mainlayout 
        if (isHistory) 
        {
            DoctorView.getChildren().clear();
            DoctorView.getChildren().add(mainLayout);  
        }
        else
        {
            showAlert("Patient ID not Found");
        }
 	
    }

	private void saveDoctorNotes(String patientID) {

		patientID = PATIENTID;
		String notesString = docNoteText.getText();
		String medsString = medstext.getText();
		String immunizationsString = immunizationsText.getText();

        String fileName = patientID + "_Notes.txt";
        
        //if they are all filled, create the file
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write("Notes: " + notesString + "\n");
			writer.write("Medication: " + medsString + "\n");
			writer.write("Immunization: " + immunizationsString + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}




	private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    
}
