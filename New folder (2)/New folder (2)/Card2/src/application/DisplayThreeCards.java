/*
 * Display Three Cards
 * Exercise: 14.3
 * Developed by PRINCE
 * ID: 12105007
 */

package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DisplayThreeCards extends Application {

    @Override
    public void start(Stage primaryStage) {
        // List of card numbers from 1 to 52
        List<Integer> cardNumbers = new ArrayList<>();
        for (int i = 1; i <= 52; i++) {
            cardNumbers.add(i);
        }

        // Shuffle the card numbers
        Collections.shuffle(cardNumbers);

        // Create an HBox to hold the cards, centered in the middle
        HBox hBox = new HBox(20);
        hBox.setStyle("-fx-alignment: center;");

        // Load the first three random card images and add glow effects
        for (int i = 0; i < 3; i++) {
            String imagePath = getClass().getResource("/image/card/" + cardNumbers.get(i) + ".png").toExternalForm();
            Image cardImage = new Image(imagePath);
            ImageView cardView = new ImageView(cardImage);

            // Set fixed size for card images
            cardView.setFitWidth(170);
            cardView.setFitHeight(260);

            
            hBox.getChildren().add(cardView);
        }

        // Create a gradient background
        StackPane root = new StackPane();
        root.getChildren().add(hBox);
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #f4d03f, #16a085);");

        // Create the scene and set it on the stage
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Display Three Cards");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
