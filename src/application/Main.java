package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import views.MainMenu;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Neuopoly");
		MainMenu mainMenu;
		try {
			mainMenu = new MainMenu(primaryStage);
			mainMenu.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}