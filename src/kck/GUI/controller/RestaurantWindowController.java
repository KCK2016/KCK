package kck.GUI.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import kck.GUI.RestaurantWindow;
import kck.GUI.view.JavaFX.KAlert;
import kck.GUI.view.JavaFX.KAnimation;
import kck.KCKParser;

import javax.xml.ws.Action;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RestaurantWindowController {

    private static final String VIEW_DIALOG_DISHES_FXML = "view/DialogDishes.fxml";
    private static final String SOUPS = "Zupy";
    private static final String MAIN_DISH = "Dania główne";
    private static final String TABLE_ENABLED = "Wolny";
    private static final String TABLE_DISABLED = "Zajęty";
    private static final int TEXT_FIELD_MAX_LENGTH = 200;
    private static Button tableChoosenInstance = null;

    // Reference to the main application.
    private RestaurantWindow mainApp;
    Action action;

    @FXML
    TextArea textAreaCommand;
    @FXML
    TextArea textAreaOutput;
    @FXML
    Circle waiterCicle;

    /**
     * Is called by the main application to give a reference back to itself.
     */
    public void setMainApp(RestaurantWindow mainApp) {
        this.mainApp = mainApp;
        waiterCicle.toFront();
        setCharacterLimit(textAreaCommand, TEXT_FIELD_MAX_LENGTH);
        setDishOfTheDay();
    }

    private void setCharacterLimit(TextArea txtArea, int limit){
        // adds character limit filter to textAreaCommand
        final UnaryOperator<TextFormatter.Change> filter
                = (TextFormatter.Change change) -> {
            if (change.getControlNewText().length() > limit) {
                return null;
            }
            return change;
        };
        txtArea.setTextFormatter(new TextFormatter(filter));
    }

    //KeyEvents on textAreaCommand
    @FXML
    public void textAreaCommandKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER))  {
            buttonSubmitClick(null);
            keyEvent.consume();
        }
        else if (keyEvent.getCode().equals(KeyCode.ESCAPE))  {
            buttonCloseAppClick(null);
            keyEvent.consume();
        }
    }

    public void setDishOfTheDay() {
        textAreaOutput.appendText("zupa dnia: " + getDishOfTheDay(SOUPS) + "\n");
        textAreaOutput.appendText("danie dnia: " + getDishOfTheDay(MAIN_DISH) + "\n");
    }

    //Wyślij
    @FXML
    public void buttonSubmitClick(MouseEvent event){
        String command = textAreaCommand.getText();
        textAreaCommand.clear();
        if(!command.isEmpty()){
            command = command.trim();
        }
        else return;
        if (!command.isEmpty()){
            command += "\n";
            textAreaOutput.appendText("Klient: " + command);
            textAreaOutput.appendText(getCommandResult(command));
        }
    }

    private String getCommandResult(String command){
        return "Kelner: " + parseAndGetParsedText(command) + "\n";
    }

    private String parseAndGetParsedText(String command) {
        KCKParser kckParser = new KCKParser();
        String parserText = null;
        try {
            parserText = kckParser.getTokenizedText(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parserText;
    }

    private String getDishOfTheDay(String group) {
        KCKParser kckParser = new KCKParser();
        try {
            return  kckParser.getDishOfTheDay(group);
        } catch (IOException e) {
            return "error";
        }
    }

    //Stolik
    @FXML
    public void buttonTableClick(MouseEvent event){
        resetTables();
        setTable((Button) event.getSource());
    }

    private void resetTables(){
        if (tableChoosenInstance != null) {
            //TODO
            //CALL THE BACKING ANIMATION OF WAITER
            tableChoosenInstance.setText(TABLE_ENABLED);
            tableChoosenInstance.setDisable(false);
            tableChoosenInstance = null;
        }
    }

    private void setTable(Button buttonTable){
        Point2D startPoint;
        if (tableChoosenInstance != null){
            //startPoint = new Point2D(waiterCicle.getLayoutX(),
            //   waiterCicle.getLayoutY());
        }
        else{
            //TODO
            //ADD A STARTING POINT FOR NOT YET SELECTED TABLE
            startPoint = new Point2D(
                buttonTable.getParent().getLayoutX(),
                buttonTable.getParent().getLayoutY());
        }

        Point2D aimPoint = new Point2D(buttonTable.getParent().getLayoutX(),
            buttonTable.getParent().getLayoutY());
        //textAreaOutput.appendText(startPoint.toString() + "\n");
        //KAnimation.pathTransition(buttonTable, startPoint, aimPoint);
        buttonTable.setText(TABLE_DISABLED);
        buttonTable.setDisable(true);
        tableChoosenInstance = buttonTable;
    }

    //Nowy klient
    @FXML
    public void buttonNewClientClick(MouseEvent event){
        resetTables();
        textAreaOutput.clear();
        textAreaCommand.clear();
        KCKParser.makeNewClient();
    }

    //Karta dań
    @FXML
    public void buttonDishesClick(MouseEvent event){
        GridPane dialogLayout = new GridPane();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RestaurantWindow.class.getResource(VIEW_DIALOG_DISHES_FXML));
        try {
            dialogLayout = loader.load();
            fillMenu(dialogLayout, 4);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        KAlert alert = new KAlert(
                Alert.AlertType.NONE,
                "Karta dań",
                dialogLayout,
                new ButtonType("Zamknij", ButtonBar.ButtonData.CANCEL_CLOSE)
        );
        alert.showAndWait();

    }

    private void fillMenu(GridPane grid, int columnsAmount) throws IOException {
        int cols =  columnsAmount;
        int col = 0, row[] = new int[cols];
        String file = new String(Files.readAllBytes(Paths.get("baza.txt")));
        String[] content=file.split("[\n]");
        String[] menu=new String[content.length];

        Pattern p = Pattern.compile("(?<=[\\n,:])(.*?)(?=[:/])");
        for(int i=0;i<content.length;i++){
            menu[i]="";
            Matcher m = p.matcher(content[i]);
            while(m.find()) {
                grid.add(new Label(m.group().trim()+"\n"), col, row[col]++);
            }
            grid.add(new Label("\n"), col, row[col]++);
            col++;
            if (col + 1 > cols ) col = 0;
        }
    }

    //Wyjście
    @FXML
    public void buttonCloseAppClick(MouseEvent event) {
        KAlert alert = new KAlert(
                Alert.AlertType.CONFIRMATION,
                "Wyjście",
                "Czy na pewno chcesz wyjść z aplikacji?",
                new ButtonType("Tak", ButtonBar.ButtonData.YES),
                new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE)
        );
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get().getButtonData() == ButtonBar.ButtonData.YES) {
            Platform.exit();
        }
    }
}
