package application;
	
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			String result = JOptionPane.showInputDialog("Select the language, please:");
			Locale locale = new Locale(result, result.toUpperCase());
			ResourceBundle resources = ResourceBundle.getBundle("lang", locale);
			VBox root = FXMLLoader.load(getClass().getResource("App.fxml"), resources);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle(resources.getString("product"));
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
