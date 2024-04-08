import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import javafx.geometry.*;

public class CTScanTechView{
	
	//CTScanView
    private Label CTLbPatientID;
    private Label CTLbtotalCAC;
    private Label CTLbCAC;
    private Label CTLbLM;
    private Label CTLbLAD;
    private Label CTLbLCX;
    private Label CTLbRCA;
    private Label CTLbPDA;
    private TextArea CTpatientId;
    private TextArea CTtotalcacScore;
    private TextArea CTLM;
    private TextArea CTLAD;
    private TextArea CTLCX ;
    private TextArea CTRCA;
    private TextArea CTPDA;
    private Button CTbtnSave;
    
    //CTlayout
    private VBox CTRecord;
    private HeartHealthApp mainApp;
    
    public CTScanTechView(HeartHealthApp mainApp) {
    	this.mainApp = mainApp;
        initializeUI();
    }
    
    private void initializeUI()
    {
		//Label
		CTLbPatientID = new Label("Patient ID:");
		CTLbtotalCAC = new Label("The total Agatston CAC score");
		CTLbCAC = new Label("Vessel level Agatston CAC score");
		CTLbLM = new Label("LM:");
		CTLbLAD = new Label("LAD:");
		CTLbLCX = new Label("LCX:");
		CTLbRCA = new Label("RCA:");
		CTLbPDA = new Label("PDA:");
	
		//Text Area
		CTpatientId = new TextArea();
        CTtotalcacScore = new TextArea();
        CTpatientId.setPrefWidth(400);
        CTpatientId.setPrefHeight(50);
        CTtotalcacScore.setPrefWidth(400);
        CTtotalcacScore.setPrefHeight(50);
        CTLM = new TextArea();
        CTLAD = new TextArea();
        CTLCX = new TextArea();
        CTRCA = new TextArea();
        CTPDA = new TextArea();
        CTLM.setPrefWidth(250);
        CTLM.setPrefHeight(50);
        CTLAD.setPrefWidth(250);
        CTLAD.setPrefHeight(50);
        CTLCX.setPrefWidth(250);
        CTLCX.setPrefHeight(50);
        CTRCA.setPrefWidth(250);
        CTRCA.setPrefHeight(50);
        CTPDA.setPrefWidth(250);
        CTPDA.setPrefHeight(50);
        
        //Button
        CTbtnSave = new Button("Save");
        CTbtnSave.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
        CTbtnSave.setPrefWidth(100);
		CTbtnSave.setPrefHeight(50);
        // Attach event handlers to buttons
        CTbtnSave.setOnAction(event -> saveCTRecord());
        
        //Layout
        GridPane CTRecordgridPane = new GridPane();
        CTRecordgridPane.setHgap(20);
        CTRecordgridPane.setVgap(15);
        CTRecordgridPane.setPadding(new Insets(10));
        CTRecordgridPane.add(CTLbPatientID, 0, 0);
        CTRecordgridPane.add(CTLbtotalCAC, 0, 1);
        CTRecordgridPane.add(CTLbCAC, 0, 2);
        CTRecordgridPane.add(CTpatientId, 1, 0);
        CTRecordgridPane.add(CTtotalcacScore, 1, 1);
        GridPane CTRecordCACgridPane = new GridPane();
        CTRecordCACgridPane.setHgap(20);
        CTRecordCACgridPane.setVgap(15);
        CTRecordCACgridPane.setPadding(new Insets(10));
        CTRecordCACgridPane.add(CTLbLM, 0, 0);
        CTRecordCACgridPane.add(CTLbLAD, 0, 1);
        CTRecordCACgridPane.add(CTLbLCX, 0, 2);
        CTRecordCACgridPane.add(CTLbRCA, 0, 3);
        CTRecordCACgridPane.add(CTLbPDA, 0, 4);
        CTRecordCACgridPane.add(CTLM, 1, 0);
        CTRecordCACgridPane.add(CTLAD, 1, 1);
        CTRecordCACgridPane.add(CTLCX, 1, 2);
        CTRecordCACgridPane.add(CTRCA, 1, 3);
        CTRecordCACgridPane.add(CTPDA, 1, 4);
        CTRecordCACgridPane.add(CTbtnSave, 10, 4);
        CTRecord = new VBox(CTRecordgridPane,CTRecordCACgridPane);
        
        Button goBackButton = new Button("Go Back");
        goBackButton.setOnAction(e -> mainApp.showMainMenu());
        CTRecordCACgridPane.add(goBackButton, 1, 5);
        
    }
 
    //get root for the main button to use
    public VBox getRoot() {
        return CTRecord;
    }
	
	private void saveCTRecord() {
		//get CTScan record
		String patientID = CTpatientId.getText();
        String totalScore = CTtotalcacScore.getText();
        String lmScore = CTLM.getText();
        String ladScore = CTLAD.getText();
        String lcxScore = CTLCX.getText();
        String rcaScore = CTRCA.getText();
        String pdaScore = CTPDA.getText();
		
        //check if every blank has been filled
        if (patientID.isEmpty() || totalScore.isEmpty() || lmScore.isEmpty() || ladScore.isEmpty() ||
                lcxScore.isEmpty() || rcaScore.isEmpty() || pdaScore.isEmpty()) {
            return;
        }
        
        //if they are all filled, create the file
        try {
            FileWriter writer = new FileWriter(patientID + "CTResults.txt");
            writer.write("Patient ID: " + patientID + "\n");
            writer.write("Total Agatston CAC Score: " + totalScore + "\n");
            writer.write("LM: " + lmScore + "\n");
            writer.write("LAD: " + ladScore + "\n");
            writer.write("LCX: " + lcxScore + "\n");
            writer.write("RCA: " + rcaScore + "\n");
            writer.write("PDA: " + pdaScore + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
    
}