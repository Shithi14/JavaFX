package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeGame extends Application {

    private static final int TILE_SIZE = 40;
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    private static final int GAME_SPEED = 130; // Milliseconds per frame
    private static final String[] FOODS_IMAGE = new String[]{
        "/resources/ic_orange.png", "/resources/ic_apple.png", "/resources/ic_cherry.png",
        "/resources/ic_berry.png", "/resources/ic_coconut_.png", "/resources/ic_peach.png",
        "/resources/ic_watermelon.png", "/resources/ic_pomegranate.png"
    };

    private Direction currentDirection = Direction.RIGHT;
    private boolean gameOver = false;

    private List<Point> snake = new ArrayList<>();
    private Point food;

    private Image foodImage;

    private int score = 0;
    private Random random = new Random();

    private Timeline timeline;

    public static void main(String[] args) {
        SnakeGame game = new SnakeGame();
        game.runGame(args);
    }

    public void runGame(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE + 100);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        timeline = new Timeline(new KeyFrame(Duration.millis(GAME_SPEED), e -> run(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);

        Button playAgainButton = new Button("Play Again");
        playAgainButton.setStyle("-fx-font-size: 50px; -fx-background-color: linear-gradient(#ff7f50, #ff6347); -fx-text-fill: white; -fx-padding: 20px 50px;");
        playAgainButton.setLayoutX(WIDTH * TILE_SIZE / 2 - 130);
        playAgainButton.setLayoutY(HEIGHT * TILE_SIZE / 2 + 50);
        playAgainButton.setVisible(false);
        playAgainButton.setOnAction(e -> {
            startGame();
            playAgainButton.setVisible(false);
            timeline.play();
            gc.clearRect(0, 0, WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE + 100);
            canvas.requestFocus(); // Ensure the canvas regains focus for key events
        });


        // Add Back button
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 30px; -fx-background-color: linear-gradient(#800080, #9932CC); -fx-text-fill: white; -fx-padding: 10px 20px;");

        backButton.setLayoutX(WIDTH * TILE_SIZE - 140); // Position it near the right edge
        backButton.setLayoutY(HEIGHT * TILE_SIZE + 30); // Align with Score section
        backButton.setOnAction(e -> stage.close()); // Close the window or navigate to the main menu

        Group root = new Group(canvas, playAgainButton, backButton);
        Scene scene = new Scene(root);

        // Ensure the Canvas has focus for key events
        scene.setOnKeyPressed(this::handleKeyPress);
        canvas.setFocusTraversable(true); // Allow canvas to capture key events
        canvas.requestFocus(); // Request focus explicitly

        stage.setScene(scene);
        stage.setTitle("Snake Game");
        stage.show();

        startGame();
        timeline.play();
    }


    private void startGame() {
        snake.clear();
        snake.add(new Point(WIDTH / 2, HEIGHT / 2));
        spawnFood();
        currentDirection = Direction.RIGHT;
        gameOver = false;
        score = 0;
    }

    private void run(GraphicsContext gc) {
        if (gameOver) {
            gc.setFill(Color.RED);
            gc.setFont(Font.font("Verdana", FontWeight.BOLD, 70));
            gc.fillText("Game Over!", WIDTH * TILE_SIZE / 3.5, HEIGHT * TILE_SIZE / 2.5);
            timeline.stop();
            Button playAgainButton = (Button) ((Group) gc.getCanvas().getParent()).getChildren().get(1);
            playAgainButton.setVisible(true);
            return;
        }

        update();
        draw(gc);
    }

    private void update() {
        Point head = snake.get(0);
        Point newHead = head.move(currentDirection);

        // Check for collisions
        if (newHead.x < 0 || newHead.y < 0 || newHead.x >= WIDTH || newHead.y >= HEIGHT || snake.contains(newHead)) {
            gameOver = true;
            return;
        }

        snake.add(0, newHead);

        // Check if food is eaten
        if (newHead.equals(food)) {
            spawnFood();
            score += 5;
        } else {
            snake.remove(snake.size() - 1);
        }
    }

    private void draw(GraphicsContext gc) {
        drawBackground(gc);
        drawFood(gc);
        drawSnake(gc);
        drawScore(gc);
    }

    private void drawBackground(GraphicsContext gc) {
        LinearGradient gradient = new LinearGradient(0, 0, 1, 1, true, null,
            new Stop(0, Color.DARKBLUE),
            new Stop(1, Color.DARKSLATEBLUE));

        gc.setFill(gradient);
        gc.fillRect(0, 0, WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                gc.setStroke(Color.web("555555"));
                gc.strokeRect(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    private void spawnFood() {
        do {
            food = new Point(random.nextInt(WIDTH), random.nextInt(HEIGHT));
        } while (snake.contains(food));

        foodImage = loadImage(FOODS_IMAGE[random.nextInt(FOODS_IMAGE.length)]);
    }

    private Image loadImage(String path) {
        try {
            System.out.println("Loading image: " + path);
            return new Image(SnakeGame.class.getResource(path).toExternalForm());
        } catch (Exception e) {
            System.err.println("Failed to load image: " + path + " Error: " + e.getMessage());
            return null;
        }
    }

    private void drawFood(GraphicsContext gc) {
        if (foodImage != null) {
            gc.drawImage(foodImage, food.x * TILE_SIZE, food.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
    }

    private void drawSnake(GraphicsContext gc) {
        for (int i = 0; i < snake.size(); i++) {
            Point p = snake.get(i);
            if (i == 0) {
                RadialGradient headGradient = new RadialGradient(0, 0, 0.5, 0.5, 1, true, null,
                    new Stop(0, Color.ORANGE),
                    new Stop(1, Color.YELLOW));
                gc.setFill(headGradient);
                gc.fillOval(p.x * TILE_SIZE, p.y * TILE_SIZE, TILE_SIZE - 2, TILE_SIZE - 2);
            } else {
                LinearGradient bodyGradient = new LinearGradient(0, 0, 1, 1, true, null,
                    new Stop(0, Color.CYAN),
                    new Stop(1, Color.LIGHTGREEN));
                gc.setFill(bodyGradient);
                gc.fillRoundRect(p.x * TILE_SIZE, p.y * TILE_SIZE, TILE_SIZE - 4, TILE_SIZE - 4, 15, 15);
            }
        }
    }

    private void drawScore(GraphicsContext gc) {
        gc.setFill(Color.DARKOLIVEGREEN);
        gc.fillRect(0, HEIGHT * TILE_SIZE, WIDTH * TILE_SIZE, 100);

        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        gc.fillText("Score: " + score, 10, HEIGHT * TILE_SIZE + 70);
    }

    private void handleKeyPress(KeyEvent event) {
        KeyCode key = event.getCode();

        switch (key) {
            case UP:
                if (currentDirection != Direction.DOWN) currentDirection = Direction.UP;
                break;
            case DOWN:
                if (currentDirection != Direction.UP) currentDirection = Direction.DOWN;
                break;
            case LEFT:
                if (currentDirection != Direction.RIGHT) currentDirection = Direction.LEFT;
                break;
            case RIGHT:
                if (currentDirection != Direction.LEFT) currentDirection = Direction.RIGHT;
                break;
        }
    }

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Point move(Direction direction) {
            return switch (direction) {
                case UP -> new Point(x, y - 1);
                case DOWN -> new Point(x, y + 1);
                case LEFT -> new Point(x - 1, y);
                case RIGHT -> new Point(x + 1, y);
            };
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Point other)) return false;
            return x == other.x && y == other.y;
        }

        @Override
        public int hashCode() {
            return x * 31 + y;
        }
    }
}
