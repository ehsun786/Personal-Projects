import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class MainController {

	@FXML
	private ToggleGroup myGroup; // I called it myGroup in SceneBuilder as well.
	@FXML
	private Button submitButton;
	@FXML
	private TextField readTimeTaken;
	@FXML
	private Button loadGame, newGame, exit;
	@FXML
	private long TIMING_VALUE;
	
	@FXML
	public void mySubmitButtonAction() {
		RadioButton radioButtonName = (RadioButton) myGroup.getSelectedToggle();
		if(radioButtonName.getText().equals("Parallel")) {
			long startTime = System.currentTimeMillis();
			HangFile.runParallel();
			long stopTime = System.currentTimeMillis();
			TIMING_VALUE = stopTime - startTime;
			HangFile.writeArrayContentsToFile();
		} else {
			long startTime = System.currentTimeMillis();
			HangFile.runSequential();
			long stopTime = System.currentTimeMillis();
			TIMING_VALUE = stopTime - startTime;
			HangFile.writeArrayContentsToFile();
		}
		readTimeTaken.setText(Long.toString(TIMING_VALUE));
	}
	
	@FXML
	public void newGameAction() {
		Stage stage = (Stage) newGame.getScene().getWindow();
		try {
		    Parent root = FXMLLoader.load(getClass().getResource("Difficulty.fxml"));
			Scene scene = new Scene(root,600,400);
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
    private void exitAction(){
        // get a handle to the stage
        Stage stage = (Stage) exit.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
	
	@FXML
	private void loadGameAction() {
		try (FileInputStream fi = new FileInputStream("savedGame.txt"); ObjectInputStream obj = new ObjectInputStream(fi);) {
			String[] newArray;
			newArray = (String[]) obj.readObject();
			if(newArray[0].equals("Very Easy")) {
				Stage stage = (Stage) loadGame.getScene().getWindow();
				try {
				    Parent root = FXMLLoader.load(getClass().getResource("GameSavedVeryEasy.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else if(newArray[0].equals("Easy")) {
				Stage stage = (Stage) loadGame.getScene().getWindow();
				try {
				    Parent root = FXMLLoader.load(getClass().getResource("GameSavedEasy.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else if(newArray[0].equals("Medium")) {
				Stage stage = (Stage) loadGame.getScene().getWindow();
				try {
				    Parent root = FXMLLoader.load(getClass().getResource("GameSavedMedium.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else {
				Stage stage = (Stage) loadGame.getScene().getWindow();
				try {
				    Parent root = FXMLLoader.load(getClass().getResource("GameSavedHard.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException c) {
			System.out.println(c.getMessage());
		}
	}
	

}
