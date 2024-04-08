import java.util.Optional;
import javax.swing.border.Border;
import javafx.scene.control.TextInputDialog;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HealthSystem extends Application {

    // Variables 
    BorderPane root; // The main layout pane

    Label header; // The header label

    VBox buttonVbox; // The VBox for organizing buttons

    Button patientIntakeButton; // Button for patient intake
    Button ctScanTechViewButton; // Button for CT scan technician view
    Button patientViewButton; // Button for patient view

    TextInputDialog dialog; // Dialog for user input

    @Override
    public void start(Stage primaryStage) 
    {
        root = new BorderPane(); // Initialize the main layout pane

        // Styling the header
        header = new Label("Welcome to Heart Health Imaging and Recording System");
        header.setStyle("-fx-font-size: 15");
        header.setPadding(new Insets(50)); // Add padding to the header

        buttonVbox = new VBox(10); // Initialize the VBox for buttons with spacing of 10
        buttonVbox.setAlignment(Pos.CENTER); // Center align the VBox

        // Creating the Buttons
        patientIntakeButton = new Button("Patient Intake");
        ctScanTechViewButton = new Button("CT Scan Tech View");
        patientViewButton = new Button("Patient View");

        // Styling the Buttons
        patientIntakeButton.setStyle("-fx-background-color: blue; -fx-min-width: 200px;-fx-font-weight: bold; -fx-padding: 10px;");
        ctScanTechViewButton.setStyle("-fx-background-color: blue; -fx-min-width: 200px; -fx-font-weight: bold; -fx-padding: 10px;");
        patientViewButton.setStyle("-fx-background-color: blue; -fx-min-width: 200px; -fx-font-weight: bold; -fx-padding: 10px;");        

        // Adding elements to the VBox
        buttonVbox.getChildren().addAll(patientIntakeButton, ctScanTechViewButton, patientViewButton);

        // Setting and alinging the panes
        root.setCenter(buttonVbox); // Set the VBox as the center of the root BorderPane
        root.setTop(header); // Set the header label at the top of the root BorderPane
        BorderPane.setAlignment(header, Pos.CENTER); // Align the header label to the center

        // Handling the patient intake button
        patientIntakeButton.setOnAction(e -> 
        {
            PatientIntakeForm intakeForm = new PatientIntakeForm();
            intakeForm.show(); // Show the patient intake form
        });

        // Handling the Technician view button
        ctScanTechViewButton.setOnAction(e -> 
        {
            CTScanView ctScan = new CTScanView();
            ctScan.show(); // Show the CT scan technician view
        });

        // Handling the patient view 
        patientViewButton.setOnAction(e -> 
        {
            // Create a dialog to prompt for the patient ID
            dialog = new TextInputDialog();
            dialog.setTitle("Patient ID Input");
            dialog.setHeaderText(null);
            dialog.setContentText("Please enter the patient ID:");
        
            // Checking if the patientID exists then show information to patient
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(patientID -> 
            {
                PatientView patientView = new PatientView(patientID);
                patientView.show(); // Show the patient view based on the entered patient ID
            });
        });

        // Create the scene
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene); // Set the scene to the primary stage
        primaryStage.show(); // Show the primary stage
    }

    public static void main(String[] args) {
        launch(args); // Launch the application
    }
}
