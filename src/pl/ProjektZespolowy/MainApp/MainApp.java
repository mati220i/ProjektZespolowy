package pl.ProjektZespolowy.MainApp;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.ProjektZespolowy.controllers.MainAppController;

public class MainApp extends Application {

	public static void main(String[] args) throws IOException {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/pl/ProjektZespolowy/resources/MainApp.fxml"));
		AnchorPane pane = loader.load();

		Image icon = new Image("/pl/ProjektZespolowy/resources/logo.png");
		
		Scene scene = new Scene(pane);

		primaryStage.setTitle("Projekt Zespołowy");
		primaryStage.setX(30);
		primaryStage.setY(30);
		primaryStage.setHeight(1020);
		primaryStage.setWidth(900);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(icon);
		primaryStage.show();
		
		MainAppController mainAppController = loader.getController();
		mainAppController.setPrimaryStage(primaryStage);
		mainAppController.setPrimaryScene(scene);
		mainAppController.setPane(pane);
		mainAppController.refresh();
	}
}