package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class SortingAnimation extends Application {

    private static final int ARRAY_SIZE = 50;
    private static final int BAR_WIDTH = 10;
    private static final int PANE_HEIGHT = 300;

    private Integer[] arrayParallel1 = new Integer[ARRAY_SIZE];
    private Integer[] arrayParallel2 = new Integer[ARRAY_SIZE];
    private Integer[] arrayParallel3 = new Integer[ARRAY_SIZE];

    private Integer[] arraySequential1 = new Integer[ARRAY_SIZE];
    private Integer[] arraySequential2 = new Integer[ARRAY_SIZE];
    private Integer[] arraySequential3 = new Integer[ARRAY_SIZE];

    private Rectangle[] barsParallel1 = new Rectangle[ARRAY_SIZE];
    private Rectangle[] barsParallel2 = new Rectangle[ARRAY_SIZE];
    private Rectangle[] barsParallel3 = new Rectangle[ARRAY_SIZE];

    private Rectangle[] barsSequential1 = new Rectangle[ARRAY_SIZE];
    private Rectangle[] barsSequential2 = new Rectangle[ARRAY_SIZE];
    private Rectangle[] barsSequential3 = new Rectangle[ARRAY_SIZE];

    @Override
    public void start(Stage primaryStage) {
        // Initialize and shuffle arrays
        ArrayList<Integer> tempList = new ArrayList<>();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            tempList.add(i + 1);
        }
        Collections.shuffle(tempList);

        for (int i = 0; i < ARRAY_SIZE; i++) {
            arrayParallel1[i] = arrayParallel2[i] = arrayParallel3[i] = tempList.get(i);
            arraySequential1[i] = arraySequential2[i] = arraySequential3[i] = tempList.get(i);
        }

        // Create panes for parallel sorting
        Pane paneParallel1 = new Pane();
        Pane paneParallel2 = new Pane();
        Pane paneParallel3 = new Pane();
        drawBars(paneParallel1, barsParallel1, arrayParallel1);
        drawBars(paneParallel2, barsParallel2, arrayParallel2);
        drawBars(paneParallel3, barsParallel3, arrayParallel3);

        // Create panes for sequential sorting
        Pane paneSequential1 = new Pane();
        Pane paneSequential2 = new Pane();
        Pane paneSequential3 = new Pane();
        drawBars(paneSequential1, barsSequential1, arraySequential1);
        drawBars(paneSequential2, barsSequential2, arraySequential2);
        drawBars(paneSequential3, barsSequential3, arraySequential3);

        // Labels for parallel sorting
        Label parallelLabel1 = new Label("Parallel Bubble Sort");
        Label parallelLabel2 = new Label("Parallel Selection Sort");
        Label parallelLabel3 = new Label("Parallel Insertion Sort");

        // Labels for sequential sorting
        Label sequentialLabel1 = new Label("Sequential Bubble Sort");
        Label sequentialLabel2 = new Label("Sequential Selection Sort");
        Label sequentialLabel3 = new Label("Sequential Insertion Sort");

        // Combine graphs and labels into VBoxes
        VBox vboxParallel1 = new VBox(10, paneParallel1, parallelLabel1);
        VBox vboxParallel2 = new VBox(10, paneParallel2, parallelLabel2);
        VBox vboxParallel3 = new VBox(10, paneParallel3, parallelLabel3);

        VBox vboxSequential1 = new VBox(10, paneSequential1, sequentialLabel1);
        VBox vboxSequential2 = new VBox(10, paneSequential2, sequentialLabel2);
        VBox vboxSequential3 = new VBox(10, paneSequential3, sequentialLabel3);

        // Layout for parallel and sequential graphs
        HBox hboxParallel = new HBox(10, vboxParallel1, vboxParallel2, vboxParallel3);
        HBox hboxSequential = new HBox(10, vboxSequential1, vboxSequential2, vboxSequential3);

        VBox mainVBox = new VBox(20, hboxParallel, hboxSequential);

        // Start parallel sorting in threads
        new Thread(() -> bubbleSort(paneParallel1, barsParallel1, arrayParallel1)).start();
        new Thread(() -> selectionSort(paneParallel2, barsParallel2, arrayParallel2)).start();
        new Thread(() -> insertionSort(paneParallel3, barsParallel3, arrayParallel3)).start();

        // Start sequential sorting (one after another)
        new Thread(() -> {
            bubbleSort(paneSequential1, barsSequential1, arraySequential1);
            selectionSort(paneSequential2, barsSequential2, arraySequential2);
            insertionSort(paneSequential3, barsSequential3, arraySequential3);
        }).start();

        primaryStage.setTitle("Sorting Animation: Parallel vs Sequential");
        primaryStage.setScene(new Scene(mainVBox, ARRAY_SIZE * BAR_WIDTH * 3 + 100, PANE_HEIGHT * 2 + 100));
        primaryStage.show();
    }

    private void drawBars(Pane pane, Rectangle[] bars, Integer[] array) {
        pane.getChildren().clear();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            bars[i] = new Rectangle(i * BAR_WIDTH, PANE_HEIGHT - array[i] * 5, BAR_WIDTH - 1, array[i] * 5);
            bars[i].setFill(Color.BLUE);
            pane.getChildren().add(bars[i]);
        }
    }

    private void bubbleSort(Pane pane, Rectangle[] bars, Integer[] array) {
        try {
            for (int i = 0; i < ARRAY_SIZE - 1; i++) {
                for (int j = 0; j < ARRAY_SIZE - 1 - i; j++) {
                    if (array[j] > array[j + 1]) {
                        swap(array, j, j + 1);
                    }
                }
                updateBars(pane, bars, array, ARRAY_SIZE - 1 - i);
                Thread.sleep(100); // Adjust sorting animation speed
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void selectionSort(Pane pane, Rectangle[] bars, Integer[] array) {
        try {
            for (int i = 0; i < ARRAY_SIZE - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < ARRAY_SIZE; j++) {
                    if (array[j] < array[minIndex]) {
                        minIndex = j;
                    }
                }
                swap(array, i, minIndex);
                updateBars(pane, bars, array, i);
                Thread.sleep(100); // Adjust sorting animation speed
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void insertionSort(Pane pane, Rectangle[] bars, Integer[] array) {
        try {
            for (int i = 1; i < ARRAY_SIZE; i++) {
                int key = array[i];
                int j = i - 1;
                while (j >= 0 && array[j] > key) {
                    array[j + 1] = array[j];
                    j--;
                }
                array[j + 1] = key;
                updateBars(pane, bars, array, i);
                Thread.sleep(100); // Adjust sorting animation speed
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void updateBars(Pane pane, Rectangle[] bars, Integer[] array, int sortedIndex) {
        for (int i = 0; i < ARRAY_SIZE; i++) {
            bars[i].setHeight(array[i] * 5);
            bars[i].setY(PANE_HEIGHT - array[i] * 5);
            bars[i].setFill(i > sortedIndex ? Color.BLUE : Color.GREEN);
        }
    }

    private void swap(Integer[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
