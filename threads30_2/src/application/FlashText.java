package application;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FlashText extends Application {
    private String text = ""; // Variable to hold the toggling text

    @Override
    public void start(Stage primaryStage) {
        // Create a StackPane to hold the label
        VBox pane = new VBox(20);

        // Create a label with initial text
        Label lblText = new Label("");
        Label lblText1 = new Label("Programming is fun");

        // Set the font to bold and larger size
        lblText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
       
        // Add the label to the pane
        pane.getChildren().addAll(lblText,lblText1);

        // Create a new thread to update the label text periodically
        new Thread(() -> {
            try {
                while (true) {
                    // Toggle the text between "Welcome" and an empty string
                    if (lblText.getText().trim().isEmpty()) {
                        text = "Welcome";
                    } else {
                        text = "";
                    }

                    // Update the label text on the JavaFX Application Thread
                    Platform.runLater(() -> lblText.setText(text));

                    // Pause for 200 milliseconds
                    Thread.sleep(200);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace(); // Print the stack trace if the thread is interrupted
            }
        }).start(); // Start the thread

        // Create a larger scene with specified dimensions
        Scene scene = new Scene(pane, 400, 150);

        // Set the title of the primary stage
        primaryStage.setTitle("FlashText");

        // Place the scene in the stage
        primaryStage.setScene(scene);

        // Display the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}
