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
    private TextArea docNoteText;
    private TextArea medstext;
    private Button save;
    private Button sendMeds;

    //VBox mainLayout;
    BorderPane mainLayout;
    
    //layout
    private PediatircAutoSystem mainApp;
    
    public DoctorView(PediatircAutoSystem mainApp) {
    	this.mainApp = mainApp;
        initializeUI();
    }
    
    // private void initializeUI()
    // {
    //     //Label
    //     docNote = new Label("Doctor Notes:");
    //     meds = new Label("Prescription:");
    
    //     //Text Area
    //     docNoteText = new TextArea();
    //     medstext = new TextArea();
    
    //     docNoteText.setPrefWidth(400);
    //     docNoteText.setPrefHeight(50);
    //     medstext.setPrefWidth(400);
    //     medstext.setPrefHeight(50);
    
    //     //Button
    //     save = new Button("Save");
    //     save.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
    //     save.setPrefWidth(100);
    //     save.setPrefHeight(50);
    //     // Attach event handlers to buttons
    //     save.setOnAction(e -> doctorNotes(docNoteText.getText(), medstext.getText()));
    
    //     // Layout
    //     HBox doctorNotesBox = new HBox(docNote, docNoteText);
    //     doctorNotesBox.setAlignment(Pos.CENTER_LEFT);
    //     doctorNotesBox.setSpacing(10);
    
    //     HBox medicationsBox = new HBox(meds, medstext);
    //     medicationsBox.setAlignment(Pos.CENTER_LEFT);
    //     medicationsBox.setSpacing(10);
    
    //     mainLayout = new VBox(doctorNotesBox, medicationsBox, save);
    //     mainLayout.setSpacing(20); // Add spacing between elements
    //     mainLayout.setPadding(new Insets(20)); // Add padding around the VBox
    //     mainLayout.setPrefSize(500, 300); // Set preferred size for the VBox
    
    //     Button goBackButton = new Button("Go Back");
    //     goBackButton.setOnAction(e -> mainApp.showMainMenu());
    //     VBox.setMargin(goBackButton, new Insets(10, 0, 0, 0)); // Add margin to the "Go Back" button
    //     mainLayout.getChildren().add(goBackButton);
    // }

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
    save.setOnAction(e -> doctorNotes(docNoteText.getText()));

    sendMeds = new Button("send meds");
    sendMeds.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
    sendMeds.setPrefWidth(100);
    sendMeds.setPrefHeight(50);
    // Attach event handlers to buttons
    sendMeds.setOnAction(e -> doctorMedication(medstext.getText()));

    // Layout
    mainLayout = new BorderPane();

    VBox doctorNotesVBox = new VBox(docNote, docNoteText);
    doctorNotesVBox.setSpacing(10);

    VBox medicationsVBox = new VBox(meds, medstext);
    medicationsVBox.setSpacing(10);

    mainLayout.setCenter(new VBox(doctorNotesVBox, medicationsVBox));
    mainLayout.setPadding(new Insets(20)); // Add padding around the VBox

    HBox bottomBox = new HBox();
    bottomBox.setSpacing(10);
    bottomBox.setPadding(new Insets(10, 0, 0, 0)); // Add padding to the bottom

    Button goBackButton = new Button("Go Back");
    goBackButton.setOnAction(e -> mainApp.showMainMenu());
    bottomBox.getChildren().addAll(goBackButton, save, sendMeds);

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
            FileWriter writer = new FileWriter(fileName);
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
            FileWriter writer = new FileWriter(fileName);
            writer.write("medication: " + medication + "\n");
            //writer.write("medication: " + medication + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
    
}
