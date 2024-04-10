import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

import javafx.geometry.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
	
	private Label enterPatientID;
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
    private Label LDate;
    private Label LGender;
    private Label LPharmacy;
    private TextArea TxKnownAllergies;
    private TextArea TxHealthConcern;
    private TextArea TxPastHistory;
    private TextArea TxWeight;
    private TextArea TxHeight;
    private TextArea TxTemperature;
    private TextArea TxBloodPressure;
    private TextArea TxDate;
    private TextArea TxGender;
    private TextArea TxPharmacy;
    private Button btnViewPastHistory;
    private Button btnSaveVital;
    private Button btnSendMessage;
    private Label RecordPatientVital;
    
	//layout
	private GridPane IntakegridPane;
	private PediatircAutoSystem mainApp;
	private GridPane RecordVital;

    private String PATIENTID;
    private boolean isHistory = false;
    
   
	 
	public NurseView(PediatircAutoSystem mainApp) {
		this.mainApp = mainApp;
		initializeInputUI(); //check ID first
        initializeUI();//register only if the id not found
        initializeHealthUI();//record vitals
    }
	
	//check id first
	private void initializeInputUI() {
		// Enter Patient ID Label
		enterPatientID = new Label("Enter Patient ID:");
		enterPatientID.setFont(new Font("Arial", 20));
		
        // Patient ID input section   
        patientIdInput = new TextField();
        patientIdInput.setPromptText("Enter Patient ID");
        
        //button part
        submitButton = new Button("Load Patient Data");
	    submitButton.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
	    submitButton.setPrefWidth(150);
	    submitButton.setPrefHeight(40);
        submitButton.setOnAction(event -> loadPatientData(patientIdInput.getText()));
        btnRegister = new Button("Register new account");
	    btnRegister.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
	    btnRegister.setPrefWidth(150);
	    btnRegister.setPrefHeight(40);
        btnRegister.setOnAction(event -> getRegister());
        
        // Back Button
        Button goBackButton = new Button("Go Back");
	    goBackButton.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
	    goBackButton.setPrefWidth(150);
	    goBackButton.setPrefHeight(40);
        goBackButton.setOnAction(e -> mainApp.showMainMenu());

        inputLayout = new VBox(20);
        inputLayout.setAlignment(Pos.CENTER);
        inputLayout.getChildren().addAll(enterPatientID, patientIdInput, submitButton, btnRegister, goBackButton);
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
	    btnViewPastHistory.setOnAction(e -> mainApp.accessHistoryFile(PATIENTID));
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
        LGender = new Label("Gender(M/F)");
        LPharmacy = new Label("Pharmacy");

        btnSendMessage = new Button("Send Message");

		
		//Text Area
		TxFirstName = new TextArea();
        TxlastName = new TextArea();
        TxBirthday = new TextArea();
        TxEmail = new TextArea();
        TxPhoneNumber = new TextArea();
        TxInsuranceID = new TextArea();
        TxGender = new TextArea();
        TxPharmacy = new TextArea();
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
        TxGender.setPrefWidth(400);
        TxGender.setPrefHeight(50);
        TxPharmacy.setPrefWidth(400);
        TxPharmacy.setPrefHeight(50);
       
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
        IntakegridPane.add(LGender, 0, 3);
        IntakegridPane.add(LBirthday, 0, 4);
        IntakegridPane.add(LEmail, 0, 5);
        IntakegridPane.add(LPhoneNumber, 0, 6);
        IntakegridPane.add(LInsuranceID, 0, 7);
        IntakegridPane.add(LPharmacy, 0, 8);
        IntakegridPane.add(TxFirstName, 1, 1);
        IntakegridPane.add(TxlastName, 1, 2);
        IntakegridPane.add(TxGender, 1 , 3);
        IntakegridPane.add(TxBirthday, 1, 4);
        IntakegridPane.add(TxEmail, 1, 5);
        IntakegridPane.add(TxPhoneNumber, 1, 6);
        IntakegridPane.add(TxInsuranceID, 1, 7);
        IntakegridPane.add(TxPharmacy, 1, 8);
        IntakegridPane.add(btnSendMessage, 1, 9);
        IntakegridPane.add(btnSave, 2, 9);

        btnSendMessage.setOnAction(e -> mainApp.sendMessage(PATIENTID, "Nurse: "));
        
        Button goBackButton = new Button("Go Back");
        goBackButton.setOnAction(e -> mainApp.showMainMenu());
        IntakegridPane.add(goBackButton, 0, 9);
		
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

		
		// //get Patients' info
		String firstName = TxFirstName.getText();
        String lastName = TxlastName.getText();
        String gender = TxGender.getText();
        String pharmacy = TxPharmacy.getText();
        String birthday = TxBirthday.getText();
        String email = TxEmail.getText();
        String phoneNumber = TxPhoneNumber.getText();
        String insuranceID = TxInsuranceID.getText();
        
        //Generate Patient's unique ID
        String patientID = generatePatientID(firstName, lastName, birthday);
        PATIENTID = patientID;
        System.out.println("Generated Patient ID: " + patientID);

        try {
        	FileWriter writer = new FileWriter("IDs.txt", true);
            writer.write(patientID + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //write in a file
        try {
        	FileWriter writer = new FileWriter(patientID + "_PatientInfo.txt", true);
            writer.write("First Name: " + firstName + "\n");
            writer.write("Last Name: " + lastName + "\n");
            writer.write("Gender: " + gender + "\n");
            writer.write("Birthday: " + birthday + "\n");
            writer.write("Email: " + email + "\n");
            writer.write("Phone Number: " + phoneNumber + "\n");
            writer.write("Insurance ID: " + insuranceID + "\n");
            writer.write("Pharmacy: " + pharmacy + "\n");
            writer.write("\nVitals " + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //once nurse saves, it will lead to record vitals page
        NurseView.getChildren().clear();
    	NurseView.getChildren().add(RecordVital); 
        //return patientID;  	
     }
	
	//save patient vitals
    private void savePatientVitals() {
        // Get Patients' vital
        String Weight = TxWeight.getText();
        String Height = TxHeight.getText();
        String Temperature = TxTemperature.getText();
        String Bloodpressure = TxBloodPressure.getText();
        String KnownAllergies = TxKnownAllergies.getText();
        String HealthConcern = TxHealthConcern.getText();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDate today = LocalDate.now();
        String formattedDate = dtf.format(today);
    
        String fileName = PATIENTID + "_PatientInfo.txt";
        StringBuilder fileContent = new StringBuilder();

        // Append the new content to the file
        fileContent.append("\nVisit Date(YYYY/MM/DD) "+ formattedDate + "\n");
        //fileContent.append("Patient Vitals:\n");
        fileContent.append("Weight: ").append(Weight).append("\n");
        fileContent.append("Height: ").append(Height).append("\n");
        fileContent.append("Body Temperature: ").append(Temperature).append("\n");
        fileContent.append("Blood Pressure: ").append(Bloodpressure).append("\n");
        fileContent.append("Known Allergies: ").append(KnownAllergies).append("\n");
        fileContent.append("Health Concern: ").append(HealthConcern).append("\n");
        
    
        // Write the file content back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(fileContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        mainApp.showMainMenu();
    }
    
	
	
    private void loadPatientData(String patientID) {
        
        isHistory = mainApp.existsPatientID(patientID);
        PATIENTID = patientID;

        //if the id exists, then go to the record vital page 
        if (isHistory) 
        {
            NurseView.getChildren().clear();
            NurseView.getChildren().add(RecordVital);  
        } else {
            showAlert("Patient ID not Found");
        }

 	
    }
    
    // Utility method to show an alert dialog
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
	
	private String generatePatientID(String firstName, String lastName, String birthday) {
        String patientID;
        do {
            // Extract the initial of the first name
            char firstInitial = firstName.charAt(0);
            // Extract month and day from the birthday
            String[] parts = birthday.split("/");
            if (parts.length < 3) {
                // Handle invalid DOB format here, e.g., throw an exception or return a default value
                showAlert("Wrong format of DOB");
            }
            String month = parts[1]; // The month is the second element
            String day = parts[2];   // The day is the third element

            patientID = firstInitial + lastName + month + day;;
        } while (new File(patientID + "_PatientInfo.txt").exists());
        return patientID;
    }
}
