import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import javafx.geometry.*;

public class DoctorView {

    private Label docNote;
    private Label meds;
    private TextArea docNoteText;
    private TextArea medstext;
    private Button save;
    private Button sendMeds;
    private Button viewHistory;
    private HBox mainContent; // Use HBox for main content layout
    private VBox mainLayout;

    boolean isHistory = false;
    private PediatircAutoSystem mainApp;

    public DoctorView(PediatircAutoSystem mainApp) {
        this.mainApp = mainApp;
        initializeUI();
    }

    private void initializeUI() {
        // Labels
        docNote = new Label("Doctor Notes:");
        meds = new Label("Prescription:");

        // Text Areas
        docNoteText = new TextArea();
        medstext = new TextArea();

        docNoteText.setPrefWidth(400);
        docNoteText.setPrefHeight(400);
        medstext.setPrefWidth(400);
        medstext.setPrefHeight(400);

        // Buttons
        save = new Button("Save");
        save.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
        save.setPrefWidth(100);
        save.setPrefHeight(50);
        save.setOnAction(e -> saveDoctorNotes(docNoteText.getText()));

        sendMeds = new Button("Send Meds");
        sendMeds.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
        sendMeds.setPrefWidth(100);
        sendMeds.setPrefHeight(50);
        sendMeds.setOnAction(e -> saveMedication(medstext.getText()));

        // Doctor Notes VBox
        VBox doctorNotesVBox = new VBox(docNote, docNoteText);
        doctorNotesVBox.setSpacing(10);

        // Medications VBox
        VBox medicationsVBox = new VBox(meds, medstext);
        medicationsVBox.setSpacing(10);

        // Main HBox for content
        mainContent = new HBox(doctorNotesVBox, medicationsVBox);
        mainContent.setSpacing(20);

        // Bottom Buttons HBox
        HBox bottomBox = new HBox();
        bottomBox.setSpacing(50);
        //bottomBox.getChildren().addAll();

        // Go Back Button
        Button goBackButton = new Button("Go Back");
        goBackButton.setOnAction(e -> mainApp.showMainMenu());
        bottomBox.getChildren().addAll( save, sendMeds,goBackButton);

        // History Button
        viewHistory = new Button("View History");
        viewHistory.setStyle("-fx-background-color: #4c6fb5; -fx-text-fill: #111112;");
        viewHistory.setPrefWidth(100);
        viewHistory.setPrefHeight(50);
        if (!isHistory) {
            viewHistory.setOnAction(e -> openHistory());
        } else {
            viewHistory.setDisable(true);
        }
        bottomBox.getChildren().add(viewHistory);

        // Main VBox for layout
        mainLayout = new VBox();
        mainLayout.getChildren().addAll(mainContent, bottomBox);
        mainLayout.setPadding(new Insets(10));
    }

    // Get root for the main button to use
    public VBox getRoot() {
        return mainLayout;
    }

    private void saveDoctorNotes(String notes) {
        String fileName = "Notes.txt";
        try {
            FileWriter writer = new FileWriter(fileName, true); // Set true for append mode
            writer.write("Notes: " + notes + "\n");
            writer.close();
            showAlert("Saved", "Doctor notes saved successfully.");
        } catch (IOException e) {
            showAlert("Error", "An error occurred while saving doctor notes.");
            e.printStackTrace();
        }
    }

    private void saveMedication(String medication) {
        String fileName = "Medication.txt";
        try {
            FileWriter writer = new FileWriter(fileName, true); // Set true for append mode
            writer.write("Medication: " + medication + "\n");
            writer.close();
            showAlert("Saved", "Prescription saved successfully.");
        } catch (IOException e) {
            showAlert("Error", "An error occurred while saving the prescription.");
            e.printStackTrace();
        }
    }

    private void openHistory() {
        // Dummy implementation for history retrieval
        // The actual implementation will depend on how history data is stored and should be retrieved
        String historyData = "Patient history data...";
        showAlert("History", historyData);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Main method and the rest of the application logic would be here
}
