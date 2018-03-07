package views;

import java.io.IOException;

import interfaces.Controllable;
import interfaces.Stageable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class SetUpView implements Stageable {
	
	private static final String FXML_FILE = "SetUp.fxml";
	private FXMLLoader loader;
	private Scene scene;
	
	public SetUpView() throws IOException {
		loader = new FXMLLoader(getClass().getResource(FXML_FILE));
		AnchorPane root = loader.load();
		scene = new Scene(root);
	}
	
	public Scene getScene() {
		return scene;
	}

	@Override
	public Controllable getController() {
		return loader.getController();
	}

}
