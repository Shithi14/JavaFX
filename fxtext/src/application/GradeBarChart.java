package application;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GradeBarChart extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create a pane to hold the bars and labels
        Pane pane = new Pane();
        
        // Base X and Y positions for the first bar
        double baseX = 50;
        double baseY = 250;

        // Create the bar for Projects (20%)
        Rectangle projectBar = new Rectangle(baseX, baseY - 20 * 5, 50, 20 * 5);
        projectBar.setFill(Color.RED);
        Text projectText = new Text(baseX, baseY + 20, "Projects (20%)");
        
        // Create the bar for Quizzes (10%)
        Rectangle quizBar = new Rectangle(baseX + 80, baseY - 10 * 5, 50, 10 * 5);
        quizBar.setFill(Color.BLUE);
        Text quizText = new Text(baseX + 80, baseY + 20, "Quizzes (10%)");

        // Create the bar for Midterm Exams (30%)
        Rectangle midtermBar = new Rectangle(baseX + 160, baseY - 30 * 5, 50, 30 * 5);
        midtermBar.setFill(Color.GREEN);
        Text midtermText = new Text(baseX + 160, baseY + 20, "Midterms (30%)");

        // Create the bar for Final Exam (40%)
        Rectangle finalBar = new Rectangle(baseX + 240, baseY - 40 * 5, 50, 40 * 5);
        finalBar.setFill(Color.ORANGE);
        Text finalText = new Text(baseX + 240, baseY + 20, "Final Exam (40%)");

        // Add all bars and labels to the pane
        pane.getChildren().addAll(projectBar, projectText, quizBar, quizText, midtermBar, midtermText, finalBar, finalText);
        
        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 400, 300);
        primaryStage.setTitle("Grade Distribution Bar Chart");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}