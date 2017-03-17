import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class DiffcultyController {

	@FXML
	private Button playButton;
	@FXML
	private ToggleGroup difficultyGroup; // I called it myGroup in SceneBuilder as well.
	
	@FXML
	public void playButtonAction() {
		RadioButton radioButtonName = (RadioButton) difficultyGroup.getSelectedToggle();
		if (radioButtonName.getText().equals("Very Easy")) {
			Stage stage = (Stage) radioButtonName.getScene().getWindow();
			try {
			    Parent root = FXMLLoader.load(getClass().getResource("VeryEasy.fxml"));
				Scene scene = new Scene(root,1920,1080);
				stage.setScene(scene);
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if (radioButtonName.getText().equals("Easy")) {
			Stage stage = (Stage) radioButtonName.getScene().getWindow();
			try {
			    Parent root = FXMLLoader.load(getClass().getResource("Easy.fxml"));
				Scene scene = new Scene(root,1920,1080);
				stage.setScene(scene);
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if (radioButtonName.getText().equals("Medium")) {
			Stage stage = (Stage) radioButtonName.getScene().getWindow();
			try {
			    Parent root = FXMLLoader.load(getClass().getResource("Medium.fxml"));
				Scene scene = new Scene(root,1920,1080);
				stage.setScene(scene);
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if (radioButtonName.getText().equals("Hard")) {
			Stage stage = (Stage) radioButtonName.getScene().getWindow();
			try {
			    Parent root = FXMLLoader.load(getClass().getResource("Hard.fxml"));
				Scene scene = new Scene(root,1920,1080);
				stage.setScene(scene);
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			
		}
	}

}
