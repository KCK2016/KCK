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
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import kck.GUI.enums.ButtonNames;
import kck.GUI.enums.ControlButtonPosition;
import kck.GUI.enums.PositionOnGrid;

public class RestaurantWindow extends Application {
    private static final int boardSize = 8;
    private GridPane gridPane;
    private Stage primaryStage;
    private Button waiter;
    private TextArea textArea;
    private String inputText;

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
        addTextButton();
        createTextArea();
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
        primaryStage.setScene(new Scene(gridPane, 1200, 1200));
        primaryStage.show();
    }

    private void createButtonsToControlWaiter() {
        createButtonToControlWaiter(ButtonNames.TABLE1, PositionOnGrid.WAITER_TABLE_ONE_POSITION, ControlButtonPosition.FIRSTBUTTON);
        createButtonToControlWaiter(ButtonNames.TABLE2, PositionOnGrid.WAITER_TABLE_TWO_POSITION, ControlButtonPosition.SECONDBUTTON);
        createButtonToControlWaiter(ButtonNames.TABLE3, PositionOnGrid.WAITER_TABLE_THREE_POSITION, ControlButtonPosition.THIRDBUTTON);
        createButtonToControlWaiter(ButtonNames.KITCHEN, PositionOnGrid.WAITER_KITCHEN_POSITION, ControlButtonPosition.FOURFBUTTON);
    }

    private void createButtonsOnGrid(){
        addButtonToGrid(ButtonNames.TABLE1, PositionOnGrid.TABLE_ONE_POSITION);
        addButtonToGrid(ButtonNames.TABLE2, PositionOnGrid.TABLE_TWO_POSITION);
        addButtonToGrid(ButtonNames.TABLE3, PositionOnGrid.TABLE_THREE_POSITION);
        addButtonToGrid(ButtonNames.KITCHEN, PositionOnGrid.KITCHEN_POSITION);
    }

    private void addWaiterToBoard() {
        waiter = new Button(ButtonNames.WAITER_BUTTON.getName());
        gridPane.add(waiter, PositionOnGrid.WAITER_KITCHEN_POSITION.getPositionX(), PositionOnGrid.WAITER_KITCHEN_POSITION.getPositionY());
    }

    private void addButtonToGrid(ButtonNames buttonNames, PositionOnGrid positionOnGrid){
        Button button = new Button(buttonNames.getName());
        gridPane.add(button, positionOnGrid.getPositionX(), positionOnGrid.getPositionY());
    }

    private void createButtonToControlWaiter(ButtonNames buttonNames, PositionOnGrid positionOnGrid, ControlButtonPosition controlButtonPosition){
        Button button = new Button(buttonNames.getName());
        changeWaiterPositionAddButtonToGrid(button, event -> changeWaiterPosition(positionOnGrid.getPositionX(), positionOnGrid.getPositionY()),
                controlButtonPosition.getPositionX(), controlButtonPosition.getPositionY());
    }
    private void changeWaiterPositionAddButtonToGrid(Button button, EventHandler<MouseEvent> mouseEventEventHandler, int x, int y) {
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventEventHandler);
        gridPane.add(button, x, y);
    }

    private void changeWaiterPosition(int x, int y) {
        gridPane.getChildren().removeAll(waiter);
        gridPane.add(waiter, x, y);
    }

    private void createTextArea() {
        textArea = new TextArea("Type here");
        gridPane.add(textArea, ControlButtonPosition.TEXT_AREA_POSTION.getPositionX(), ControlButtonPosition.TEXT_AREA_POSTION.getPositionY());
    }

    private void addTextButton() {
        Button button = new Button(ButtonNames.TEXT_BUTTON.getName());
        gridPane.add(button, ControlButtonPosition.TEXTBUTTON.getPositionX(), ControlButtonPosition.TEXTBUTTON.getPositionY());
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            inputText = textArea.getText();
            textArea.clear();
        });
    }

}
