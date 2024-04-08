import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;

public class HeartHealthApp extends Application {
	
	private PatientIntake PatientIntake;
    private CTScanTechView CTScanTechView;
    private PatientView PatientView;
	
	// UI Components
	//main
    private Button btnpatientIntake;
    private Button btnctScanTechView;
    private Button btnpatientView;
    private Label MainTitle;
    private Scene mainScene; 
    private Stage primaryStage;
    
	// Scene and Stage
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		//Main UI
		MainTitle = new Label("Welcome to Heart Health Imaging and Recording System");
		btnpatientIntake = new Button("Nurse View");
		btnctScanTechView = new Button("Doctor View");
		btnpatientView = new Button("Patient View");
        
		//set button size
		btnpatientIntake.setPrefWidth(200);
		btnpatientIntake.setPrefHeight(80);
		btnctScanTechView.setPrefWidth(200);
		btnctScanTechView.setPrefHeight(80);
		btnpatientView.setPrefWidth(200);
		btnpatientView.setPrefHeight(80);
		
		//set button color
		btnpatientIntake.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
		btnctScanTechView.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
		btnpatientView.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
		// Attach event handlers to buttons
		btnpatientIntake.setOnAction(event -> openPatientIntake(primaryStage));
		btnctScanTechView.setOnAction(event -> openTechnicianView(primaryStage));
		btnpatientView.setOnAction(event -> openPatientView(primaryStage));
		VBox buttonSection = new VBox(20, btnpatientIntake, btnctScanTechView, btnpatientView);
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
	
	private void openPatientIntake(Stage stage) {
		PatientIntake PatientIntake = new PatientIntake(this);
        Scene scene = new Scene(PatientIntake.getRoot(), 800, 600);
        stage.setScene(scene);
		stage.show();
    }

    private void openTechnicianView(Stage stage) {
    	 CTScanTechView ctScanTechView = new CTScanTechView(this);
		 Scene scene = new Scene(ctScanTechView.getRoot(), 700, 500);
		 stage.setScene(scene);
		 stage.show();
    }

    private void openPatientView(Stage stage) {
        PatientView patientView = new PatientView(this);
        Scene scene = new Scene(patientView.getRoot(), 800, 600);
        stage.setScene(scene);
		stage.show();
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
