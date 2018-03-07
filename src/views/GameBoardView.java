package views;

import java.io.IOException;

import interfaces.Controllable;
import interfaces.Stageable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class GameBoardView implements Stageable {
	
	private static final String FXML_FILE = "GameBoard.fxml";
	private FXMLLoader loader;
	private Scene scene;
	
	public GameBoardView() throws IOException {
		loader = new FXMLLoader(getClass().getResource(FXML_FILE));
		AnchorPane root = loader.load();
		scene = new Scene(root);
		scene.getStylesheets().add("application/application.css");
	}
	
	public Scene getScene() {
		return scene;
	}

	@Override
	public Controllable getController() {
		return loader.getController();
	}

}
