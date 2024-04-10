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

    //VBox mainLayout;
    BorderPane mainLayout;

    boolean isHistory = false;
    
    //layout
    private PediatircAutoSystem mainApp;
    
    public DoctorView(PediatircAutoSystem mainApp) {
    	this.mainApp = mainApp;
        initializeUI();
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
		save.setOnAction(e -> doctorNotes(docNoteText.getText()));

		sendMeds = new Button("send meds");
		sendMeds.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
		sendMeds.setPrefWidth(100);
		sendMeds.setPrefHeight(50);
		sendMeds.setOnAction(e -> doctorMedication(medstext.getText()));
		
		saveImmunizations = new Button("Save Immunizations");
		saveImmunizations.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
		saveImmunizations.setPrefWidth(150);
		saveImmunizations.setPrefHeight(50);
		saveImmunizations.setOnAction(e -> doctorImmunizations(immunizationsText.getText()));

		viewHistory = new Button("View his");
		viewHistory.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
		viewHistory.setPrefWidth(100);
		viewHistory.setPrefHeight(50);

		viewHistory.setOnAction(e -> mainApp.openHistory(isHistory));

		
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
		bottomBox.getChildren().addAll(goBackButton, save, sendMeds, saveImmunizations, viewHistory);

		mainLayout.setBottom(bottomBox);
	}
 


	//get root for the main button to use
    public BorderPane getRoot() {
        return mainLayout;
    }
	
	private void doctorNotes(String notes) {

        String fileName ="Notes.txt";
        
        //if they are all filled, create the file
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write("Notes: " + notes + "\n");
            //writer.write("medication: " + medication + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    private void doctorMedication(String medication) {

        String fileName ="Medication.txt";
        
        //if they are all filled, create the file
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write("medication: " + medication + "\n");
            //writer.write("medication: " + medication + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    private void doctorImmunizations(String immunization) {
    	String fileName = "Immunizations.txt";
    	
    	//if they are all filled, create the file
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write("Immunization: " + immunization + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}


    
}
