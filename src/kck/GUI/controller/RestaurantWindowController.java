package kck.GUI.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import kck.GUI.RestaurantWindow;
import kck.GUI.view.JavaFX.KAlert;
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

    private static final String soups = "Zupy";
    private static final String mainDish = "Dania główne";
    private static final int TEXT_FIELD_MAX_LENGTH = 200;

    // Reference to the main application.
    private RestaurantWindow mainApp;
    Action action;


    @FXML
    TextArea textAreaCommand;
    @FXML
    TextArea textAreaOutput;

    /**
     * Is called by the main application to give a reference back to itself.
     */
    public void setMainApp(RestaurantWindow mainApp) {
        this.mainApp = mainApp;
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

    //Enter
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
        textAreaOutput.appendText("zupa dnia: " + getDishOfTheDay(soups) + "\n");
        textAreaOutput.appendText("danie dnia: " + getDishOfTheDay(mainDish) + "\n");
    }
    //Wyślij
    @FXML
    public void buttonSubmitClick(MouseEvent event){
        String command = textAreaCommand.getText();
        textAreaCommand.clear();

        //TO DO
        //Sprawdzanie czy nie ma enterów, spacji
        //na początku i końcu stringa.
        if(!command.isEmpty()){
            command = command.trim();
        }
        else return;
        if (!command.isEmpty()){
            command += "\n";
            textAreaOutput.appendText(command);
            textAreaOutput.appendText(getCommandResult(command));
        }
    }

    private String getCommandResult(String command){
        String result = parseAndGetParsedText(command);
        if (result.isEmpty()){
            return "Niestety nie zrozumiałem. Czy możesz sparafrazować?\n";
        } else {
            return "Zamówiono " + result + ".\n";
        }
    }

    private String parseAndGetParsedText(String command) {
        KCKParser kckParser = new KCKParser();
        String parserText = kckParser.getTokenizedText(command);
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

    //Nowy klient
    @FXML
    public void buttonNewClientClick(MouseEvent event){
        //TO DO
        //Zerowanie i czyszczenie stanu stolików,
        //stanu zamówienia i pól tekstowych.
        textAreaOutput.clear();
        textAreaCommand.clear();
    }

    //Karta dań
    @FXML
    public void buttonDishesClick(MouseEvent event){
        //TO DO
        //Wyświetlanie okna dialogowego z listą dań.
        GridPane dialogLayout = new GridPane();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RestaurantWindow.class.getResource("view/DialogDishes.fxml"));
        try {
            dialogLayout = loader.load();
        }
        catch (IOException e){
            // TO DO
            //throw new UserException(e.getMessage());
        }
        Label menu = new Label();

        try {
            menu.setText(getMenu());
            dialogLayout.getChildren().add(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Label l = new Label();

        try {
            l.setText(getMenu());
            dialogLayout.getChildren().add(l);
        } catch (IOException e) {
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

    String getMenu() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("baza.txt")));
        String menu="";
        Pattern p = Pattern.compile("(?<=[\\n,:])(.*?)(?=[:/])");
        Matcher m = p.matcher(content);
        while(m.find()) {
            menu+=m.group()+'\n';
        }
        System.out.println(menu);
        return menu;
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
