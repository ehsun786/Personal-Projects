import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class JavaFXGraphics extends Application {
	
	public Parent root;
	
	@Override
	public void start(Stage stage) {
		try {
			root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root, 500, 150);
			scene.getStylesheets().add(getClass().getResource("Main.fxml").toExternalForm());
			stage.setScene(scene);
			stage.setTitle("Hangman Word Chooser");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}