import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

import javafx.geometry.*;

public class PatientIntake{
	
	//patientIntake
	private Label LfirstName;
	private Label LlastName;
	private Label LEmail;
	private Label LPhoneNumber;
	private Label LHealthHistory;
	private Label LInsuranceID;
	private Label PatientIntake;
	private TextArea TxFirstName;
	private TextArea TxlastName;
	private TextArea TxEmail;
	private TextArea TxPhoneNumber;
	private TextArea TxHealthHistory;
	private TextArea TxInsuranceID;
	private Button btnSave;
	
	//layout
	private GridPane IntakegridPane;
	private HeartHealthApp mainApp;
	 
	public PatientIntake(HeartHealthApp mainApp) {
		this.mainApp = mainApp;
        initializeUI();
    }
	
	private void initializeUI()
	{
		//Label
		LfirstName = new Label("First Name:");
		LlastName = new Label("Last Name:");
		LEmail = new Label("Email:");
		LPhoneNumber = new Label("Phone Number:");
		LHealthHistory = new Label("Health History:");
		LInsuranceID = new Label("Insurance ID:");
		PatientIntake = new Label("Patient Intake Form");
		
		//Text Area
		TxFirstName = new TextArea();
        TxlastName = new TextArea();
        TxEmail = new TextArea();
        TxPhoneNumber = new TextArea();
        TxHealthHistory = new TextArea();
        TxInsuranceID = new TextArea();
        TxFirstName.setPrefWidth(400);
        TxFirstName.setPrefHeight(50);
        TxlastName.setPrefWidth(400);
        TxlastName.setPrefHeight(50);
        TxEmail.setPrefWidth(400);
        TxEmail.setPrefHeight(50);
        TxPhoneNumber.setPrefWidth(400);
        TxPhoneNumber.setPrefHeight(50);
        TxHealthHistory.setPrefWidth(400);
        TxHealthHistory.setPrefHeight(50);
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
        IntakegridPane.add(LEmail, 0, 3);
        IntakegridPane.add(LPhoneNumber, 0, 4);
        IntakegridPane.add(LHealthHistory, 0, 5);
        IntakegridPane.add(LInsuranceID, 0, 6);
        IntakegridPane.add(TxFirstName, 1, 1);
        IntakegridPane.add(TxlastName, 1, 2);
        IntakegridPane.add(TxEmail, 1, 3);
        IntakegridPane.add(TxPhoneNumber, 1, 4);
        IntakegridPane.add(TxHealthHistory, 1, 5);
        IntakegridPane.add(TxInsuranceID, 1, 6);
        IntakegridPane.add(btnSave, 2, 7);
        
        Button goBackButton = new Button("Go Back");
        goBackButton.setOnAction(e -> mainApp.showMainMenu());
        IntakegridPane.add(goBackButton, 1, 7);
		
	}


	 //get root for the main button to use
    public GridPane getRoot() {
        return IntakegridPane;
    }
	
	private void savePatientInfo() {
		
		//get Patients' info
		String firstName = TxFirstName.getText();
        String lastName = TxlastName.getText();
        String email = TxEmail.getText();
        String phoneNumber = TxPhoneNumber.getText();
        String healthHistory = TxHealthHistory.getText();
        String insuranceID = TxInsuranceID.getText();
        
        //Generate Patient's unique ID
        String patientID = generatePatientID();
        System.out.println("Generated Patient ID: " + patientID);
        //write in a file
        try {
        	FileWriter writer = new FileWriter(patientID + "_PatientInfo.txt");
            writer.write("First Name: " + firstName + "\n");
            writer.write("Last Name: " + lastName + "\n");
            writer.write("Email: " + email + "\n");
            writer.write("Phone Number: " + phoneNumber + "\n");
            writer.write("Health History: " + healthHistory + "\n");
            writer.write("Insurance ID: " + insuranceID + "\n");
            writer.close();
        	} 
        catch (IOException e) {
            e.printStackTrace();
          	}
     }
	
	private String generatePatientID() {
        Random random = new Random();
        String patientID;
        do {
            patientID = String.format("%05d", random.nextInt(100000));
        } while (new File(patientID + "_PatientInfo.txt").exists());
        return patientID;
    }
	
	
		

}