package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class L14_13 extends Application {

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a BorderPane
        BorderPane pane = new BorderPane();

        // Place nodes in the pane
        pane.setTop(getHBox());
        pane.setLeft(getVBox());

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("ShowHBoxVBox"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    private HBox getHBox() {
        HBox hBox = new HBox(15); // Spacing between nodes in HBox
        hBox.setPadding(new Insets(15, 15, 15, 15));
        hBox.setStyle("-fx-background-color: green;");  // Set background color

        // Add buttons and image view to the HBox
        Button button = new Button("Computer Science");
        hBox.getChildren().add(button);

        hBox.getChildren().add(new Button("Chemistry"));

        // Load the image
        Image image = new Image(getClass().getResource("/application/image/ban.gif").toExternalForm());
        ImageView imageView = new ImageView(image); // Wrap the image in an ImageView

        hBox.getChildren().add(imageView); // Add the ImageView to the HBox

        return hBox;
    }

    private VBox getVBox() {
        VBox vBox = new VBox(15); // Spacing between nodes in VBox
        vBox.setPadding(new Insets(15, 5, 5, 5));

        Label[] courses = {new Label("CSCI 1301"), new Label("CSCI 1302"),
            new Label("CSCI 2410"), new Label("CSCI 3720")};

        for (Label course : courses) {
            VBox.setMargin(course, new Insets(0, 0, 0, 15)); // Set margin for each label
            vBox.getChildren().add(course);
        }
        return vBox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
