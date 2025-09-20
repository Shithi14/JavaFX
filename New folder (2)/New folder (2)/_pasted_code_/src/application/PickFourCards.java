package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class PickFourCards extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a BorderPane
        BorderPane pane = new BorderPane();

        // Create an HBox to hold the card images
        HBox cardBox = new HBox(10);
        pane.setCenter(cardBox);

        // Create a Refresh button
        Button refreshButton = new Button("Refresh");
        pane.setBottom(refreshButton);

        // Display four random cards initially
        displayRandomCards(cardBox);

        // Set the button action to refresh the cards
        refreshButton.setOnAction(e -> displayRandomCards(cardBox));

        // Create the scene and set the stage
        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setTitle("Pick Four Cards");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to display four random cards
    private void displayRandomCards(HBox cardBox) {
        cardBox.getChildren().clear(); // Clear previous cards

        // Get a shuffled deck of card indices (1-52)
        ArrayList<Integer> deck = new ArrayList<>();
        for (int i = 1; i <= 52; i++) {
            deck.add(i);
        }
        Collections.shuffle(deck);

        // Load the first four cards as images
        for (int i = 0; i < 4; i++) {
            int cardNumber = deck.get(i);
            String imagePath = "/cards/" + cardNumber + ".png";

            try {
                // Load the card image using getResource
                Image cardImage = new Image(getClass().getResource(imagePath).toExternalForm());
                ImageView cardView = new ImageView(cardImage);
                cardBox.getChildren().add(cardView);
            } catch (Exception e) {
                System.out.println("Error loading image: " + imagePath);
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
