package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class Main extends Application {

    private static final int ARRAY_SIZE = 50;
    private static final int BAR_WIDTH = 10;
    private static final int PANE_HEIGHT = 300;

    private Integer[] array1 = new Integer[ARRAY_SIZE];
    private Integer[] array2 = new Integer[ARRAY_SIZE];
    private Integer[] array3 = new Integer[ARRAY_SIZE];

    private Rectangle[] bars1 = new Rectangle[ARRAY_SIZE];
    private Rectangle[] bars2 = new Rectangle[ARRAY_SIZE];
    private Rectangle[] bars3 = new Rectangle[ARRAY_SIZE];

    @Override
    public void start(Stage primaryStage) {
        // Initialize and shuffle arrays
        ArrayList<Integer> tempList = new ArrayList<>();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            tempList.add(i + 1);
        }
        Collections.shuffle(tempList);

        for (int i = 0; i < ARRAY_SIZE; i++) {
            array1[i] = array2[i] = array3[i] = tempList.get(i);
        }

        // Create panes for each sorting algorithm
        Pane pane1 = new Pane();
        Pane pane2 = new Pane();
        Pane pane3 = new Pane();

        drawBars(pane1, bars1, array1);
        drawBars(pane2, bars2, array2);
        drawBars(pane3, bars3, array3);

        // Layout for all panes
        HBox hbox = new HBox(10, pane1, pane2, pane3);

        // Start sorting animations in separate threads
        new Thread(() -> bubbleSort(pane1, bars1, array1)).start();
        new Thread(() -> selectionSort(pane2, bars2, array2)).start();
        new Thread(() -> insertionSort(pane3, bars3, array3)).start();

        primaryStage.setTitle("Sorting Animation");
        primaryStage.setScene(new Scene(hbox, ARRAY_SIZE * BAR_WIDTH * 3 + 40, PANE_HEIGHT));
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
