package views;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainMenu {
	
	private static final String FXML_FILE = "MainMenu.fxml";
	private Stage stage;
	private Scene scene;
	
	/**
	 * 
	 * @param stage
	 * @throws IOException
	 */
	public MainMenu(Stage stage) throws IOException {
		this.stage = stage;
		AnchorPane root = FXMLLoader.load(getClass().getResource(FXML_FILE));
		scene = new Scene(root);
	}
	
	
	/**
	 * 
	 */
	public void show() {
		stage.setScene(scene);
		stage.show();
	}

}
