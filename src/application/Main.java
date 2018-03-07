package application;
	
import controllers.GameManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Neuopoly");
		primaryStage.setResizable(false);
		GameManager.initialize(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}