package kck.GUI;/**
 * Created by JK on 2016-10-17.
 */

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import kck.GUI.enums.ButtonNames;
import kck.GUI.enums.WaiterButtonPosition;
import kck.GUI.enums.PositionOnGrid;

public class RestaurantWindow extends Application {
    private static final int boardSize = 8;
    private GridPane gridPane;
    private Stage primaryStage;
    private Button waiter;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        setBoard();
    }

    private void setBoard() {
        setGridPane();
        addWaiterToBoard();
        createButtonsOnGrid();
        createButtonsToControlWaiter();
        setPrimaryStage();
    }

    private void setGridPane() {
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
                square.setStyle("-fx-background-color: " + color + ";");
                gridPane.add(square, col, row);
            }

        }
        for (int i = 0; i < boardSize; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
            gridPane.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }
    }

    private void setPrimaryStage() {
        primaryStage.setScene(new Scene(gridPane, 800, 800));
        primaryStage.show();
    }

    private void createButtonsToControlWaiter() {
        createButtonToControlWaiter(ButtonNames.TABLE1, PositionOnGrid.WAITER_TABLE_ONE_POSITION, WaiterButtonPosition.FIRSTBUTTON);
        createButtonToControlWaiter(ButtonNames.TABLE2, PositionOnGrid.WAITER_TABLE_TWO_POSITION, WaiterButtonPosition.SECONDBUTTON);
        createButtonToControlWaiter(ButtonNames.TABLE3, PositionOnGrid.WAITER_TABLE_THREE_POSITION, WaiterButtonPosition.THIRDBUTTON);
        createButtonToControlWaiter(ButtonNames.KITCHEN, PositionOnGrid.WAITER_KITCHEN_POSITION, WaiterButtonPosition.FOURFBUTTON);
    }

    private void createButtonsOnGrid(){
        addButtonToGrid(ButtonNames.TABLE1, PositionOnGrid.TABLE_ONE_POSITION);
        addButtonToGrid(ButtonNames.TABLE2, PositionOnGrid.TABLE_TWO_POSITION);
        addButtonToGrid(ButtonNames.TABLE3, PositionOnGrid.TABLE_THREE_POSITION);
        addButtonToGrid(ButtonNames.KITCHEN, PositionOnGrid.KITCHEN_POSTION);
    }

    private void addWaiterToBoard() {
        waiter = new Button(ButtonNames.WAITER_BUTTON.getName());
        gridPane.add(waiter, PositionOnGrid.WAITER_KITCHEN_POSITION.getPositionX(), PositionOnGrid.WAITER_KITCHEN_POSITION.getPositionY());
    }

    private void addButtonToGrid(ButtonNames buttonNames, PositionOnGrid positionOnGrid){
        Button button = new Button(buttonNames.getName());
        gridPane.add(button, positionOnGrid.getPositionX(), positionOnGrid.getPositionY());
    }

    private void createButtonToControlWaiter(ButtonNames buttonNames, PositionOnGrid positionOnGrid, WaiterButtonPosition waiterButtonPosition ){
        Button button = new Button(buttonNames.getName());
        changeWaiterPositionAddButtonToGrid(button, event -> changeWaiterPosition(positionOnGrid.getPositionX(), positionOnGrid.getPositionY()),
                waiterButtonPosition.getPositionX(), waiterButtonPosition.getPositionY());
    }
    private void changeWaiterPositionAddButtonToGrid(Button button, EventHandler<MouseEvent> mouseEventEventHandler, int x, int y) {
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventEventHandler);
        gridPane.add(button, x, y);
    }

    private void changeWaiterPosition(int x, int y) {
        gridPane.getChildren().removeAll(waiter);
        gridPane.add(waiter, x, y);
    }


}
