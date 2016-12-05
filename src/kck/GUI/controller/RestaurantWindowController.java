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

import javax.xml.ws.Action;
import java.io.IOException;
import java.util.Optional;

public class RestaurantWindowController {

    // Reference to the main application.
    private RestaurantWindow mainApp;
    Action action;

    @FXML
    TextArea TextAreaCommand;
    @FXML
    TextArea TextAreaOutput;

    /**
     * Is called by the main application to give a reference back to itself.
     */
    public void setMainApp(RestaurantWindow mainApp) {
        this.mainApp = mainApp;
    }

    //Enter
    @FXML
    public void TextAreaCommand_KeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER))  {
            ButtonSubmit_Click(null);
            keyEvent.consume();
        }
    }

    //Wyślij
    @FXML
    public void ButtonSubmit_Click(MouseEvent event){
        String Command = TextAreaCommand.getText();
        TextAreaCommand.clear();

        //TO DO
        //Sprawdzanie czy nie ma enterów, spacji i innego syfu
        //na początku i końcu stringa.
        if(!Command.isEmpty()){
            Command = Command.trim();
        }
        else return;
        if (!Command.isEmpty()){
            Command += "\n";
            TextAreaOutput.appendText(Command);
        }
    }

    //Nowy klient
    @FXML
    public void ButtonNewClient_Click(MouseEvent event){
        //TO DO
        //Zerowanie i czyszczenie stanu stolików,
        //stanu zamówienia i pól tekstowych.
        TextAreaOutput.clear();
        TextAreaCommand.clear();
    }

    //Karta dań
    @FXML
    public void ButtonDishes_Click(MouseEvent event){
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
    public void ButtonCloseApp_Click(MouseEvent event) {
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
