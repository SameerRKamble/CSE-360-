import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

import javafx.geometry.*;

public class NurseView{
	
	//patientIntake
	private Label LfirstName;
	private Label LlastName;
	private Label LBirthday;
	private Label LEmail;
	private Label LPhoneNumber;
	private Label LInsuranceID;
	private Label PatientIntake;
	private TextArea TxFirstName;
	private TextArea TxlastName;
	private TextArea TxBirthday;
	private TextArea TxEmail;
	private TextArea TxPhoneNumber;
	private TextArea TxInsuranceID;
	private Button btnSave;
	
	private TextField patientIdInput;  // TextField for patient ID input
    private Button submitButton;       // Button to submit patient ID
    private Button btnRegister;
    private VBox inputLayout; 
    private VBox NurseView;
	
    //health concern layout
    private Label LKnownAllergies;
    private Label LHealthConcern;
    private Label LPastHistory;
    private Label LWeight;
    private Label LHeight;
    private Label LTemperature;
    private Label LBloodPressure;
    private TextArea TxKnownAllergies;
    private TextArea TxHealthConcern;
    private TextArea TxPastHistory;
    private TextArea TxWeight;
    private TextArea TxHeight;
    private TextArea TxTemperature;
    private TextArea TxBloodPressure;
    private Button btnViewPastHistory;
    private Button btnSaveVital;
    private Label RecordPatientVital;
    
	//layout
	private GridPane IntakegridPane;
	private PediatircAutoSystem mainApp;
	private GridPane RecordVital;
	 
	public NurseView(PediatircAutoSystem mainApp) {
		this.mainApp = mainApp;
		initializeInputUI(); //check ID first
        initializeUI();//register only if the id not found
        initializeHealthUI();//record vitals
    }
	
	//check id first
	private void initializeInputUI() {
        // Patient ID input section   
        patientIdInput = new TextField();
        patientIdInput.setPromptText("Enter Patient ID");
        
        //button part
        submitButton = new Button("Load Patient Data");
        submitButton.setOnAction(event -> loadPatientData(patientIdInput.getText()));
        btnRegister = new Button("Register new account");
        btnRegister.setOnAction(event -> getRegister());

        inputLayout = new VBox(20);
        inputLayout.setAlignment(Pos.CENTER);
        inputLayout.getChildren().addAll(new Label("Patient ID:"), patientIdInput, submitButton, btnRegister);
        NurseView = inputLayout;
    }
	
	//health concern part
	private void initializeHealthUI() {
        
		//UI components
		LKnownAllergies = new Label("Known Allergies:");
		LHealthConcern = new Label("Health Concern:");
	    LWeight = new Label("Weight:");
	    LHeight = new Label("Height:");
	    LTemperature = new Label("Body Temperature:");
	    LBloodPressure = new Label("Blood Pressure:");
	    TxKnownAllergies = new TextArea();
	    TxHealthConcern = new TextArea();
	    TxWeight = new TextArea();
	    TxHeight = new TextArea();
	    TxTemperature = new TextArea();
	    TxBloodPressure = new TextArea();
	    RecordPatientVital = new Label("Record Patient Vitals");
	    TxKnownAllergies.setPrefWidth(400);
	    TxKnownAllergies.setPrefHeight(50);
	    TxWeight.setPrefWidth(400);
	    TxWeight.setPrefHeight(50);
	    TxBloodPressure.setPrefWidth(400);
	    TxBloodPressure.setPrefHeight(50);
	    TxHealthConcern.setPrefWidth(400);
	    TxHealthConcern.setPrefHeight(50);
	    TxHeight.setPrefWidth(400);
	    TxHeight.setPrefHeight(50);
	    TxTemperature.setPrefWidth(400);
	    TxTemperature.setPrefHeight(50);
	    
	    //button
	    btnViewPastHistory = new Button("View Past History");
	    btnSaveVital = new Button("Save");
	    btnViewPastHistory.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
	    btnViewPastHistory.setPrefWidth(200);
	    btnViewPastHistory.setPrefHeight(50);
	    btnSaveVital.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
	    btnSaveVital.setPrefWidth(150);
	    btnSaveVital.setPrefHeight(50);
        // Attach event handlers to buttons
	    btnSaveVital.setOnAction(e -> savePatientVitals());
	    btnViewPastHistory.setOnAction(e -> OpenPatientHistory());
	    Button goBackButton = new Button("Go Back");
	    goBackButton.setPrefWidth(150);
	    goBackButton.setPrefHeight(50);
        goBackButton.setOnAction(e -> mainApp.showMainMenu());
	    
	    //layout
	    RecordVital = new GridPane();
	    RecordVital.setHgap(30);
	    RecordVital.setVgap(30);
	    RecordVital.setPadding(new Insets(50));
	    RecordVital.add(RecordPatientVital, 1, 0);
	    RecordVital.add(LWeight, 0, 1);
	    RecordVital.add(LHeight, 0, 2);
	    RecordVital.add(LTemperature, 0, 3);
	    RecordVital.add(LBloodPressure, 0, 4);
	    RecordVital.add(LKnownAllergies, 0, 5);
	    RecordVital.add(LHealthConcern, 0, 6);
	    RecordVital.add(TxWeight, 1, 1);
	    RecordVital.add(TxHeight, 1, 2);
	    RecordVital.add(TxTemperature, 1, 3);
	    RecordVital.add(TxBloodPressure, 1, 4);
	    RecordVital.add(TxKnownAllergies, 1, 5);
	    RecordVital.add(TxHealthConcern, 1, 6);
	    RecordVital.add(btnSaveVital, 2, 7);
	    RecordVital.add(btnViewPastHistory, 1, 7);
	    RecordVital.add(goBackButton, 0, 7);
	   
    }
	
	private void initializeUI()
	{
		//Label
		LfirstName = new Label("First Name:");
		LlastName = new Label("Last Name:");
		LBirthday = new Label("Birthday (YY/MM/DD)");
		LEmail = new Label("Email:");
		LPhoneNumber = new Label("Phone Number:");
		LInsuranceID = new Label("Insurance ID:");
		PatientIntake = new Label("Patient Intake Form");
		
		//Text Area
		TxFirstName = new TextArea();
        TxlastName = new TextArea();
        TxBirthday = new TextArea();
        TxEmail = new TextArea();
        TxPhoneNumber = new TextArea();
        TxInsuranceID = new TextArea();
        TxFirstName.setPrefWidth(400);
        TxFirstName.setPrefHeight(50);
        TxlastName.setPrefWidth(400);
        TxlastName.setPrefHeight(50);
        TxBirthday.setPrefWidth(400);
        TxBirthday.setPrefHeight(50);
        TxEmail.setPrefWidth(400);
        TxEmail.setPrefHeight(50);
        TxPhoneNumber.setPrefWidth(400);
        TxPhoneNumber.setPrefHeight(50);
        TxInsuranceID.setPrefWidth(400);
        TxInsuranceID.setPrefHeight(50);
       
        //Button
        btnSave = new Button("Save");
        btnSave.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
        btnSave.setPrefWidth(100);
		btnSave.setPrefHeight(50);
        // Attach event handlers to buttons
        btnSave.setOnAction(e -> savePatientInfo());
 
        //Layout
        IntakegridPane = new GridPane();
        IntakegridPane.setHgap(30);
        IntakegridPane.setVgap(30);
        IntakegridPane.setPadding(new Insets(50));
        IntakegridPane.add(PatientIntake, 1, 0);
        IntakegridPane.add(LfirstName, 0, 1);
        IntakegridPane.add(LlastName, 0, 2);
        IntakegridPane.add(LBirthday, 0, 3);
        IntakegridPane.add(LEmail, 0, 4);
        IntakegridPane.add(LPhoneNumber, 0, 5);
        IntakegridPane.add(LInsuranceID, 0, 6);
        IntakegridPane.add(TxFirstName, 1, 1);
        IntakegridPane.add(TxlastName, 1, 2);
        IntakegridPane.add(TxBirthday, 1, 3);
        IntakegridPane.add(TxEmail, 1, 4);
        IntakegridPane.add(TxPhoneNumber, 1, 5);
        IntakegridPane.add(TxInsuranceID, 1, 6);
        IntakegridPane.add(btnSave, 2, 7);
        
        Button goBackButton = new Button("Go Back");
        goBackButton.setOnAction(e -> mainApp.showMainMenu());
        IntakegridPane.add(goBackButton, 1, 7);
		
	}


	 //get root for the main button to use
    public VBox getRoot() {
        return NurseView;
    }
    
    public VBox getRegister() {
    	NurseView.getChildren().clear();
    	NurseView.getChildren().add(IntakegridPane);   	
        return NurseView;
    }
    
	//register an account for the patient
	private void savePatientInfo() {
		
		//get Patients' info
		String firstName = TxFirstName.getText();
        String lastName = TxlastName.getText();
        String birthday = TxBirthday.getText();
        String email = TxEmail.getText();
        String phoneNumber = TxPhoneNumber.getText();
        String insuranceID = TxInsuranceID.getText();
        
        //Generate Patient's unique ID
        String patientID = generatePatientID();
        System.out.println("Generated Patient ID: " + patientID);
        //write in a file
        try {
        	FileWriter writer = new FileWriter(patientID + "_PatientInfo.txt");
            writer.write("First Name: " + firstName + "\n");
            writer.write("Last Name: " + lastName + "\n");
            writer.write("Birthday: " + birthday + "\n");
            writer.write("Email: " + email + "\n");
            writer.write("Phone Number: " + phoneNumber + "\n");
            writer.write("Insurance ID: " + insuranceID + "\n");
            writer.close();
        	} 
        catch (IOException e) {
            e.printStackTrace();
          	}
        
        //once nurse saves, it will lead to record vitals page
        NurseView.getChildren().clear();
    	NurseView.getChildren().add(RecordVital);   	
     }
	
	//save patient vitals
	private void savePatientVitals() {
		
		//get Patients' vital
		String Weight = TxWeight.getText();
        String Height = TxHeight.getText();
        String Temperature = TxTemperature.getText();
        String Bloodpressure = TxBloodPressure.getText();
        String KnownAllergies = TxKnownAllergies.getText();
        String HealthConcern = TxHealthConcern.getText();
        
        //write in a file
        try {
        	FileWriter writer = new FileWriter("_PatientVital.txt");
            writer.write("Weight: " + Weight + "\n");
            writer.write("Height: " + Height + "\n");
            writer.write("Body Temperature: " + Temperature + "\n");
            writer.write("Blood Pressure: " + Bloodpressure + "\n");
            writer.write("Known Allergies: " + KnownAllergies + "\n");
            writer.write("Health Concern: " + HealthConcern + "\n");
            writer.close();
        	} 
        catch (IOException e) {
            e.printStackTrace();
          	}
     }
	
	//open patient history
	private void OpenPatientHistory() {
		
	}
	
	
    private void loadPatientData(String patientID) {
        
    	// Validate patient ID and load data
        if (patientID == null) {
            showAlert("No patient ID input.");
            return;
        }
        //if the id cannot found or not register
        if (!patientID.matches("\\d{5}")) {
            showAlert("Haven't registered yet.");
            return;
        }
        //if the id exists, then go to the record vital page 
        NurseView.getChildren().clear();
    	NurseView.getChildren().add(RecordVital);   	
    }
    
    // Utility method to show an alert dialog
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
	
	private String generatePatientID() {
        String patientID;
        do {
        	//get Patients' info
    		String firstName = TxFirstName.getText();
            String lastName = TxlastName.getText();
            String birthday = TxBirthday.getText();
            // Extract the initial of the first name
            char firstInitial = firstName.charAt(0);
            // Extract month and day from the birthday
            String[] parts = birthday.split("/");
            String month = parts[1]; // The month is the second element
            String day = parts[2];   // The day is the third element

            patientID = firstInitial + lastName + month + day;;
        } while (new File(patientID + "_PatientInfo.txt").exists());
        return patientID;
    }
}
