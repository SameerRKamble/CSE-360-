import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import javafx.geometry.*;

public class CTScanTechView{
	
	//CTScanView
    private Label docNote;
    private Label meds;
    private TextArea docNoteText;
    private TextArea medstext;
    private Button save;
    private Button sendMeds;

    VBox mainLayout;
    
    //layout
    private HeartHealthApp mainApp;
    
    public CTScanTechView(HeartHealthApp mainApp) {
    	this.mainApp = mainApp;
        initializeUI();
    }
    
    private void initializeUI()
    {
		//Label
		docNote = new Label("Doctor Notes:");
		meds = new Label("Prescription:");
	
		//Text Area
		docNoteText = new TextArea();
        medstext = new TextArea();

        docNoteText.setPrefWidth(400);
        docNoteText.setPrefHeight(50);
        medstext.setPrefWidth(400);
        medstext.setPrefHeight(50);

        
        //Button
        save = new Button("Save");
        save.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
        save.setPrefWidth(100);
		save.setPrefHeight(50);
        // Attach event handlers to buttons
        save.setOnAction(e -> doctorNotes(docNoteText.getText(),medstext.getText()));


        // Layout
        HBox doctorNotesBox = new HBox(docNote, docNoteText);
        HBox.setHgrow(docNoteText, Priority.ALWAYS); // Allow text area to grow horizontally
        
        HBox medicationsBox = new HBox(meds, medstext);
        HBox.setHgrow(medstext, Priority.ALWAYS);
        
        mainLayout = new VBox(doctorNotesBox, medicationsBox, save);
        mainLayout.setSpacing(10); // Add spacing between elements
        mainLayout.setPrefSize(500, 200); // Set preferred size for the VBox
        
        Button goBackButton = new Button("Go Back");
        goBackButton.setOnAction(e -> mainApp.showMainMenu());
        mainLayout.getChildren().add(goBackButton);
        
    }
 
    //get root for the main button to use
    public VBox getRoot() {
        return mainLayout;
    }
	
	private void doctorNotes(String notes, String medication) {

        String fileName ="Medications.txt";
        
        //if they are all filled, create the file
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write("Notes: " + notes + "\n");
            writer.write("medication: " + medication + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
    
}
