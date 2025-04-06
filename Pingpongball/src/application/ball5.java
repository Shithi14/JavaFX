package application;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ball5 extends Application {

    // Variables
    private static final double WIDTH = 1000;
    private static final double HEIGHT = 700;
    private static final double PLAYER_HEIGHT = 150;
    private static final double PLAYER_WIDTH = 20;
    private static final double BALL_RADIUS = 20;
    private double playerOneXPos = 0;
    private double playerOneYPos = HEIGHT / 2 - PLAYER_HEIGHT / 2;
    private double playerTwoXPos = WIDTH - PLAYER_WIDTH;
    private double playerTwoYPos = HEIGHT / 2 - PLAYER_HEIGHT / 2;
    private double ballXPos = WIDTH / 2;
    private double ballYPos = HEIGHT / 2;
    private boolean gameStarted;
    private int ballXSpeed = 3;
    private int ballYSpeed = 3;
    private int scoresP1 = 0;
    private int scoresP2 = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Colorful Pong Game");
        primaryStage.setResizable(false);

        BorderPane borderPane = new BorderPane();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        borderPane.setCenter(canvas);
        HBox hbButtons = new HBox();
        hbButtons.setSpacing(20.0);
        borderPane.setBottom(hbButtons);
        HBox hbTitle = new HBox();
        borderPane.setTop(hbTitle);
        Scene scene1 = new Scene(new StackPane(borderPane));

        Label titleText = new Label("COLORFUL PONG GAME");
        titleText.setId("titleText");
        titleText.setFont(Font.font("Arial", 30));
        titleText.setTextFill(Color.CYAN);

        hbTitle.getChildren().addAll(titleText);
        hbTitle.setAlignment(Pos.CENTER);

        Button startButton = new Button("START");
        startButton.setId("startButton");
        startButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px;");

        Button resetButton = new Button("RESET");
        resetButton.setId("resetButton");
        resetButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: 16px;");

        hbButtons.getChildren().addAll(startButton, resetButton);
        hbButtons.setAlignment(Pos.CENTER);

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> runGame(graphicsContext, canvas)));
        timeline.setCycleCount(Timeline.INDEFINITE);

        startButton.setOnAction(e -> {
            gameStarted = true;
            if (scoresP1 == 5 || scoresP2 == 5) {
                scoresP1 = 0;
                scoresP2 = 0;
            }
        });

        resetButton.setOnAction(e -> {
            gameStarted = false;
            scoresP1 = 0;
            scoresP2 = 0;
            resetPositions();
        });

        scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene1);
        primaryStage.show();
        timeline.play();
    }

    private void runGame(GraphicsContext graphicsContext, Canvas canvas) {
        graphicsContext.setFill(Color.DARKSLATEGRAY);
        graphicsContext.fillRect(0, 0, WIDTH, HEIGHT);

        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setFont(Font.font("Verdana", 25));

        graphicsContext.setFill(Color.ORANGE);
        graphicsContext.fillRect(playerOneXPos, playerOneYPos, PLAYER_WIDTH, PLAYER_HEIGHT);

        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillRect(playerTwoXPos, playerTwoYPos, PLAYER_WIDTH, PLAYER_HEIGHT);

        graphicsContext.setFill(Color.LIGHTGREEN);
        graphicsContext.fillText(scoresP1 + " \u2013 " + scoresP2, WIDTH / 2 - 30, 50);

        if (gameStarted) {
            graphicsContext.setStroke(Color.YELLOW);
            graphicsContext.setLineWidth(2);
            graphicsContext.strokeLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);

            canvas.setOnMouseMoved(e -> playerOneYPos = e.getY() - PLAYER_HEIGHT / 2);

            if (ballXPos < WIDTH - WIDTH / 4) {
                playerTwoYPos = ballYPos - PLAYER_HEIGHT / 2;
            } else {
                playerTwoYPos = ballYPos > playerTwoYPos + PLAYER_HEIGHT / 2 ? playerTwoYPos += 2 : playerTwoYPos - 2;
            }

            graphicsContext.setFill(Color.RED);
            graphicsContext.fillOval(ballXPos, ballYPos, BALL_RADIUS, BALL_RADIUS);

            ballXPos += ballXSpeed;
            ballYPos += ballYSpeed;
        } else if (scoresP1 == 5 || scoresP2 == 5) {
            gameStarted = false;
            graphicsContext.setFill(Color.RED);
            graphicsContext.setFont(new Font("Verdana", 30));
            graphicsContext.setTextAlign(TextAlignment.CENTER);
            graphicsContext.fillText("Game Over", WIDTH / 2, HEIGHT / 2);

            Reflection r = new Reflection();
            r.setFraction(0.7f);
            graphicsContext.setEffect(r);

            resetPositions();
            canvas.setOnMouseMoved(null);
        } else {
            graphicsContext.setFill(Color.YELLOW);
            graphicsContext.setFont(new Font("Verdana", 30));
            graphicsContext.setTextAlign(TextAlignment.CENTER);
            graphicsContext.fillText("Press START to Play", WIDTH / 2, HEIGHT / 2);

            Reflection r = new Reflection();
            r.setFraction(0.7f);
            graphicsContext.setEffect(r);

            resetPositions();
            canvas.setOnMouseMoved(null);
        }

        if (ballYPos > HEIGHT || ballYPos < 0) ballYSpeed *= -1;

        if (((ballXPos + BALL_RADIUS > playerTwoXPos) && ballYPos >= playerTwoYPos && ballYPos <= playerTwoYPos + PLAYER_HEIGHT) ||
            ((ballXPos < playerOneXPos + PLAYER_WIDTH) && ballYPos >= playerOneYPos && ballYPos <= playerOneYPos + PLAYER_HEIGHT)) {
            ballXSpeed += 1 * Math.signum(ballXSpeed);
            ballYSpeed += 1 * Math.signum(ballYSpeed);
            ballXSpeed *= -1;
            ballYSpeed *= -1;
        }

        if (ballXPos < playerOneXPos - PLAYER_WIDTH) {
            scoresP2++;
            gameStarted = false;
        }

        if (ballXPos > playerTwoXPos + PLAYER_WIDTH) {
            scoresP1++;
            gameStarted = false;
        }
    }

    private void resetPositions() {
        ballXPos = WIDTH / 2;
        ballYPos = HEIGHT / 2;
        ballXSpeed = new Random().nextInt(2) == 0 ? 3 : -3;
        ballYSpeed = new Random().nextInt(2) == 0 ? 3 : -3;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
