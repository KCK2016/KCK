package kck.GUI.view.JavaFX;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.Pane;

public class KAlert extends Alert {

	public KAlert (Alert.AlertType alertType) {
		super(alertType);
		super.setHeaderText(null);
		LinkStylesheet();
	}

	public KAlert (Alert.AlertType alertType, String contentText, ButtonType... buttons){
		super(alertType, contentText, buttons);
		super.setHeaderText(null);
		LinkStylesheet();
	}

	public KAlert (Alert.AlertType alertType, String titleText, String contentText, ButtonType... buttons){
		super(alertType, contentText, buttons);
		super.setTitle(titleText);
		super.setHeaderText(null);
		LinkStylesheet();
	}

	public KAlert (Alert.AlertType alertType, String titleText, Pane pane, ButtonType... buttons){
		super(alertType, null, buttons);
		super.setTitle(titleText);
		super.setHeaderText(null);
		super.getDialogPane().setContent(pane);
		LinkStylesheet();
	}

	/**
	 * Links the stylesheet to dialog.
	 */
	public void LinkStylesheet(){
		DialogPane dialogPane = this.getDialogPane();
		dialogPane.getStylesheets().add(
				getClass().getResource("../style/dialog.css").toExternalForm());
	}

}