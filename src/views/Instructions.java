package views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Instructions {
	
	private static final String FXML_FILE = "instructions.fxml";
	
	private Stage stage;
	private Scene scene;
	
	public Instructions(Stage stage) throws IOException {
		this.stage = stage;
		AnchorPane root = FXMLLoader.load(getClass().getResource(FXML_FILE));
		scene = new Scene(root);
	}
	
	public void show() {
		stage.setScene(scene);
		stage.show();
	}

}
