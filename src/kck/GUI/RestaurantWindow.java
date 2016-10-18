package kck.GUI;/**
 * Created by JK on 2016-10-17.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kck.model.Waiter;

import java.util.concurrent.TimeUnit;

public class RestaurantWindow extends Application {
    private static final String tableName = "Stolik";
    private static final int boardSize = 8;
    private GridPane gridPane;
    private StackPane waiterPane;
    private Stage primaryStage;
    private Waiter waiter;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        setBoard();
    }

    private void setBoard() {
        gridPane = new GridPane();
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                StackPane square = new StackPane();
                String color;
                if ((row + col) % 2 == 0) {
                    color = "white";
                } else {
                    color = "grey";
                }
                if ((row == 2) && (col == 2)) {
                    Text text = new Text(tableName);
                    square.getChildren().add(text);
                }
                if ((row == 4) && (col == 4)) {
                    Text text = new Text(tableName);
                    square.getChildren().add(text);
                }
                if ((row == 4) && (col == 7)) {
                    Text text = new Text(tableName);
                    square.getChildren().add(text);
                }
                square.setStyle("-fx-background-color: " + color + ";");
                gridPane.add(square, col, row);
            }

        }
        for (int i = 0; i < boardSize; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
            gridPane.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }
        addWaiterToBoard();
        createWaiterButton();
        primaryStage.setScene(new Scene(gridPane, 400, 400));
        primaryStage.show();
    }

    private void createWaiterButton() {
        waiter = new Waiter();
        waiter.setPositionX(0);
        Button button = new Button("przycisk");
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            moveWaiterToSomeLocationEast(2, 0);
        });
        gridPane.add(button, 9, 9);
    }

    private void moveWaiterToSomeLocationEast(int x, int y) {
        for (int i = 1; i <= x; i++) {
            changeWaiterPosition(i, y);
            waiter.setPositionX(i);
        }
    }

    private void addWaiterToBoard() {
        waiterPane = new StackPane();
        makeWaiterPane();
        gridPane.add(waiterPane, 0, 0);
    }

    private void makeWaiterPane() {
        Text text = new Text("KELNER");
        waiterPane.getChildren().add(text);
        waiterPane.setStyle("-fx-background-color: " + "yellow" + ";");
    }

    private void changeWaiterPosition(int x, int y) {
        gridPane.getChildren().remove(waiterPane);
        gridPane.add(waiterPane, x, y);
//        primaryStage.setScene(new Scene(gridPane, 400, 400));
        primaryStage.show();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
