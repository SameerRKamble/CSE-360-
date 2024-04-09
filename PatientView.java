import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;
import javafx.geometry.*;

public class PatientView{
		
	//PatientView
    private Label PLbtotalCAC;
    private Label PLbLM;
    private Label PLbLAD;
    private Label PLbLCX;
    private Label PLbRCA;
    private Label PLbPDA;
    private TextArea PtotalcacScore;
    private TextArea PLM;
    private TextArea PLAD;
    private TextArea PLCX ;
    private TextArea PRCA;
    private TextArea PPDA;
    
    private TextField patientIdInput;  // TextField for patient ID input
    private Button submitButton;       // Button to submit patient ID
    private Text patientName;          // Text to display the patient's name
    
    //layout
    private VBox PatientView;
    private VBox inputLayout;
    private VBox dataLayout;
    private HeartHealthApp mainApp;
    
    public PatientView(HeartHealthApp mainApp) {
    	this.mainApp = mainApp;
    	initializeInputUI();
    	initializeUI();
    }
    
    private void initializeInputUI() {
        // Patient ID input section   
    	patientName = new Text("<Patient Name>");
        patientIdInput = new TextField();
        patientIdInput.setPromptText("Enter Patient ID");

        submitButton = new Button("Load Patient Data");
        submitButton.setOnAction(event -> loadPatientData(patientIdInput.getText()));

        inputLayout = new VBox(20);
        inputLayout.setAlignment(Pos.CENTER);
        inputLayout.getChildren().addAll(new Label("Patient ID:"), patientIdInput, submitButton);
        PatientView = inputLayout;
    }
    
    private void initializeUI()
    {
    	//Label
		PLbtotalCAC = new Label("The total Agatston CAC score");	
		PLbLM = new Label("LM:");
		PLbLAD = new Label("LAD:");
		PLbLCX = new Label("LCX:");
		PLbRCA = new Label("RCA:");
		PLbPDA = new Label("PDA:");
		
		//Text Area
		PtotalcacScore = new TextArea();
		PtotalcacScore.setPrefWidth(400);
		PtotalcacScore.setPrefHeight(50);
        PLM = new TextArea();
        PLAD = new TextArea();
        PLCX = new TextArea();
        PRCA = new TextArea();
        PPDA = new TextArea();
        PLM.setPrefWidth(250);
        PLM.setPrefHeight(50);
        PLAD.setPrefWidth(250);
        PLAD.setPrefHeight(50);
        PLCX.setPrefWidth(250);
        PLCX.setPrefHeight(50);
        PRCA.setPrefWidth(250);
        PRCA.setPrefHeight(50);
        PPDA.setPrefWidth(250);
        PPDA.setPrefHeight(50);    
        
        //Layout
        HBox Pabove = new HBox(50,PLbtotalCAC,PtotalcacScore);
        GridPane PCACgridPane = new GridPane();
        PCACgridPane.setHgap(20);
        PCACgridPane.setVgap(20);
        PCACgridPane.setPadding(new Insets(10));
        PCACgridPane.add(PLbLM, 0, 0);
        PCACgridPane.add(PLbLAD, 0, 1);
        PCACgridPane.add(PLbLCX, 0, 2);
        PCACgridPane.add(PLbRCA, 0, 3);
        PCACgridPane.add(PLbPDA, 0, 4);
        PCACgridPane.add(PLM, 1, 0);
        PCACgridPane.add(PLAD, 1, 1);
        PCACgridPane.add(PLCX, 1, 2);
        PCACgridPane.add(PRCA, 1, 3);
        PCACgridPane.add(PPDA, 1, 4);

        Button goBackButton = new Button("Go Back");
        goBackButton.setOnAction(e -> mainApp.showMainMenu());
        dataLayout = new VBox(20);
        dataLayout.setPadding(new Insets(20));
        HBox patientabove = new HBox(patientName);
        patientabove.setAlignment(Pos.CENTER);
        dataLayout.getChildren().addAll(patientabove, Pabove, PCACgridPane, goBackButton);
    }
    
    
    //get root for the main button to use
    public VBox getRoot() {
        return PatientView;
    }
    
    public void viewCTScanData(String patientID) {
    	if (patientID == null || !patientID.matches("\\d{5}")) {
            showAlert("Invalid patient ID format.");
            return;
        }
        
        boolean patientInfoLoaded = readPatientInfo(patientID);
        boolean ctScanResultsLoaded = readCTScanResults(patientID);
        
        if (!patientInfoLoaded || !ctScanResultsLoaded) {
            showAlert("Data is not available for patient ID: " + patientID);
        }
    }

    private boolean readPatientInfo(String patientID) {
        File patientInfoFile = new File(patientID + "_PatientInfo.txt");
        if (!patientInfoFile.exists()) {
            System.err.println("Patient info file not found for ID: " + patientID);
            return false; // File not found
        }
        
        try (Scanner scanner = new Scanner(patientInfoFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Extend this parsing logic as needed
                if (line.startsWith("First Name:")) {
                    String firstName = line.substring(line.indexOf(":") + 2).trim();
                    patientName.setText("Hello " + firstName);
                }
            }
        } catch (FileNotFoundException e) {
            // Log the exception
            e.printStackTrace();
            return false; // Even though the file exists, an error occurred while reading
        }
        
        return true; // Successfully read the file
    }

    private boolean readCTScanResults(String patientID) {
        File ctResultsFile = new File(patientID + "CTResults.txt");
        if (!ctResultsFile.exists()) {
            System.err.println("CT scan results file not found for ID: " + patientID);
            return false; // File not found
        }
        
        try (Scanner scanner = new Scanner(ctResultsFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("Total Agatston CAC Score:")) {
                	PtotalcacScore.setText(line.substring(line.indexOf(":") + 2).trim());
                }
                else if (line.startsWith("LM:")) {
                	PLM.setText(line.substring(line.indexOf(":") + 2).trim());
                }
                else if (line.startsWith("LAD:")) {
                	PLAD.setText(line.substring(line.indexOf(":") + 2).trim());
                }
                else if (line.startsWith("LCX:")) {
                	PLCX.setText(line.substring(line.indexOf(":") + 2).trim());
                }
                else if (line.startsWith("RCA:")) {
                	PRCA.setText(line.substring(line.indexOf(":") + 2).trim());
                }
                else if (line.startsWith("PDA:")) {
                	PPDA.setText(line.substring(line.indexOf(":") + 2).trim());
                	System.out.println(line.substring(line.indexOf(":") + 2).trim());
                }
            }
        } catch (FileNotFoundException e) {
            // Log the exception
            e.printStackTrace();
            return false; // Even though the file exists, an error occurred while reading
        }
        
        return true; // Successfully read the file
    }
    
    private void loadPatientData(String patientID) {
        // Validate patient ID and load data
        if (patientID == null || !patientID.matches("\\d{5}")) {
            showAlert("Invalid patient ID format.");
            return;
        }
        
        boolean patientInfoLoaded = readPatientInfo(patientID);
        boolean ctScanResultsLoaded = readCTScanResults(patientID);

        if (patientInfoLoaded && ctScanResultsLoaded) {
        	PatientView.getChildren().clear();
        	PatientView.getChildren().add(dataLayout);   	     
        } else {
            showAlert("Data is not available for patient ID: " + patientID);
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
	
}