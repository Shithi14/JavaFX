package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FlashText extends Application {
    private String text = "";

    @Override
    public void start(Stage primaryStage) {
        // Main layout
        VBox pane = new VBox(20); // Add spacing of 20 between children
        pane.setStyle("-fx-alignment: center;"); // Center align children

        // Labels
        Label lblText = new Label("Programming is fun");
        Label lblText1 = new Label("");
        lblText1.setStyle("-fx-font-size: 24px; -fx-text-fill: blue;"); // Bigger font and color

        pane.getChildren().addAll(lblText, lblText1); // Add labels to pane

        // Flashing text logic
        new Thread(() -> {
            try {
                while (true) {
                    if (lblText1.getText().trim().length() == 0)
                        text = "Welcome to FLASHTEXT concept";
                    else
                        text = "";

                    Platform.runLater(() -> lblText1.setText(text));

                    Thread.sleep(200); // Flashing interval (500ms)
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();

        // Scene setup
        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setTitle("FlashText");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
