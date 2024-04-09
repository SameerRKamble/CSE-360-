import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import java.io.*;

public class PediatircAutoSystem extends Application {
	
    private NurseView NurseView;
    private DoctorView DoctorView;
    private PatientView PatientView;
	
	// UI Components
	//main
    private Button btnNurseView;
    private Button btnDoctorView;
    private Button btnpatientView;
    private Label MainTitle;
    private Scene mainScene; 
    private Stage primaryStage;
    
	// Scene and Stage
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		//Main UI
		MainTitle = new Label("Welcome to Pediatric Doctor’s Office Automation System");
		btnNurseView = new Button("Nurse View");
		btnDoctorView = new Button("Doctor View");
		btnpatientView = new Button("Patient View");
        
		//set button size
		btnNurseView.setPrefWidth(200);
		btnNurseView.setPrefHeight(80);
		btnDoctorView.setPrefWidth(200);
		btnDoctorView.setPrefHeight(80);
		btnpatientView.setPrefWidth(200);
		btnpatientView.setPrefHeight(80);
		
		//set button color
		btnNurseView.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
		btnDoctorView.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
		btnpatientView.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
		// Attach event handlers to buttons
		btnNurseView.setOnAction(event -> openNurseView(primaryStage));
		btnDoctorView.setOnAction(event -> openDoctorView(primaryStage));
		btnpatientView.setOnAction(event -> openPatientView(primaryStage));
		VBox buttonSection = new VBox(20, btnNurseView, btnDoctorView, btnpatientView);
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
	
	private void openNurseView(Stage stage) {
		NurseView NurseView = new NurseView(this);
        Scene scene = new Scene(NurseView.getRoot(), 800, 600);
        stage.setScene(scene);
		stage.show();
    }

    private void openDoctorView(Stage stage) {
    	DoctorView DoctorView = new DoctorView(this);
		 Scene scene = new Scene(DoctorView.getRoot(), 700, 500);
		 stage.setScene(scene);
		 stage.show();
    }

    private void openPatientView(Stage stage) {
        PatientView patientView = new PatientView(this);
        Scene scene = new Scene(patientView.getRoot(), 800, 600);
        stage.setScene(scene);
		stage.show();
    }

	public void openHistory(boolean isHistory) 
    {
		if (isHistory) 
		{
			String filename = ""; // will decide later 
			try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
				String line;
				while ((line = reader.readLine()) != null) {
					System.out.println(line); // Print the for now
				}
			} catch (IOException e) {
				// Handle exceptions here
				e.printStackTrace();
			}
		}
		else
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No history found");
            alert.showAndWait();
		}


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
