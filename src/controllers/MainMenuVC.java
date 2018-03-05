package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import views.Instructions;

public class MainMenuVC {
	
	@FXML private Button btnStart;
	@FXML private Button btnInstructions;
	
	public void displayInstructions(ActionEvent event) {
		Instructions instructions;
		try {
			instructions = new Instructions(new Stage());
			instructions.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
