package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class HilbertCurve6 extends Application {
    public static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void rot(int n, boolean rx, boolean ry) {
            if (!ry) {
                if (rx) {
                    x = (n - 1) - x;
                    y = (n - 1) - y;
                }

                int t = x;
                x = y;
                y = t;
            }
        }

        public int calcD(int n) {
            boolean rx, ry;
            int d = 0;
            for (int s = n >>> 1; s > 0; s >>>= 1) {
                rx = ((x & s) != 0);
                ry = ((y & s) != 0);
                d += s * s * ((rx ? 3 : 0) ^ (ry ? 1 : 0));
                rot(s, rx, ry);
            }

            return d;
        }
    }

    public static Point fromD(int n, int d) {
        Point p = new Point(0, 0);
        boolean rx, ry;
        int t = d;
        for (int s = 1; s < n; s <<= 1) {
            rx = ((t & 2) != 0);
            ry = (((t ^ (rx ? 1 : 0)) & 1) != 0);
            p.rot(s, rx, ry);
            p.x += (rx ? s : 0);
            p.y += (ry ? s : 0);
            t >>>= 2;
        }
        return p;
    }

    public static List<Point> getPointsForCurve(int n) {
        List<Point> points = new ArrayList<>();
        for (int d = 0; d < (n * n); d++) {
            points.add(fromD(n, d));
        }
        return points;
    }

    @Override
    public void start(Stage stage) {
        TextField inputField = new TextField();
        inputField.setPromptText("Enter order (1, 2, 3, ...)");
        inputField.setFont(Font.font("Arial", 16));
        inputField.setStyle("-fx-text-fill: white;");
        inputField.setBackground(new Background(new BackgroundFill(
                new LinearGradient(0, 0, 1, 0, true, null, new Stop(0, Color.BLUE), new Stop(1, Color.LIGHTBLUE)),
                CornerRadii.EMPTY, null)));

        Button drawButton = new Button("Draw Hilbert Curve");
        drawButton.setFont(Font.font("Arial", 16));
        drawButton.setTextFill(Color.WHITE);
        drawButton.setStyle("-fx-background-color: linear-gradient(to right, red, orange);");

        Canvas canvas = new Canvas(700, 700);
        VBox layout = new VBox(10, inputField, drawButton, canvas);
        layout.setSpacing(10);

        drawButton.setOnAction(event -> {
            try {
                int order = Integer.parseInt(inputField.getText());
                int n = 1 << order;
                List<Point> points = getPointsForCurve(n);

                drawCurve(canvas, points, n);
            } catch (NumberFormatException e) {
                inputField.setText("Invalid input! Enter a number.");
            }
        });

        Scene scene = new Scene(layout, 810, 810);
        stage.setScene(scene);
        stage.setTitle("Hilbert Curve Viewer");
        stage.show();
    }

    private void drawCurve(Canvas canvas, List<Point> points, int n) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        double cellSize = Math.min(canvas.getWidth(), canvas.getHeight()) / n;
        double offsetX = (canvas.getWidth() - n * cellSize) / 2;
        double offsetY = (canvas.getHeight() - n * cellSize) / 2;

        for (int i = 1; i < points.size(); i++) {
            Point lastPoint = points.get(i - 1);
            Point curPoint = points.get(i);

            double x1 = lastPoint.x * cellSize + cellSize / 2 + offsetX;
            double y1 = lastPoint.y * cellSize + cellSize / 2 + offsetY;
            double x2 = curPoint.x * cellSize + cellSize / 2 + offsetX;
            double y2 = curPoint.y * cellSize + cellSize / 2 + offsetY;

            gc.setStroke(Color.hsb((i * 360.0) / points.size(), 1, 1));
            gc.strokeLine(x1, y1, x2, y2);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
