package views;

import java.io.IOException;

import interfaces.Stageable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class GameBoardView implements Stageable {
	
	private static final String FXML_FILE = "GameBoard.fxml";
	private Scene scene;
	
	public GameBoardView() throws IOException {
		AnchorPane root = FXMLLoader.load(getClass().getResource(FXML_FILE));
		scene = new Scene(root);
	}
	
	public Scene getScene() {
		return scene;
	}

}
