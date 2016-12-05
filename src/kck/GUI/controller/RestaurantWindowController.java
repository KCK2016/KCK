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
import java.util.Optional;

public class RestaurantWindowController {

    // Reference to the main application.
    private RestaurantWindow mainApp;
    Action action;
    private String parserText;

    @FXML
    TextArea textAreaCommand;
    @FXML
    TextArea textAreaOutput;

    /**
     * Is called by the main application to give a reference back to itself.
     */
    public void setMainApp(RestaurantWindow mainApp) {
        this.mainApp = mainApp;
    }

    //Enter
    @FXML
    public void textAreaCommandKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER))  {
            buttonSubmitClick(null);
            keyEvent.consume();
        }
    }

    //Wyślij
    @FXML
    public void buttonSubmitClick(MouseEvent event){
        String command = textAreaCommand.getText();
        textAreaCommand.clear();

        KCKParser kckParser = new KCKParser();
        parserText = kckParser.getText(command);
        //TO DO
        //Sprawdzanie czy nie ma enterów, spacji i innego syfu
        //na początku i końcu stringa.
        if(!command.isEmpty()){
            command = command.trim();
        }
        else return;
        if (!command.isEmpty()){
            command += "\n";
            textAreaOutput.appendText(command);
            textAreaOutput.appendText("Zamowiono " + parserText);
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

        KAlert alert = new KAlert(
                Alert.AlertType.NONE,
                "Karta dań",
                dialogLayout,
                new ButtonType("Zamknij", ButtonBar.ButtonData.CANCEL_CLOSE)
        );
        alert.showAndWait();

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
