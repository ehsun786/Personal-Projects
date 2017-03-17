import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameSavedController {

	@FXML
	private Button quitGame, mainScreen;
	
	public void quitGameAction() {
		 // get a handle to the stage
        Stage stage = (Stage) quitGame.getScene().getWindow();
        // do what you have to do
        stage.close();
	}
	
	public void mainScreenAction() {
		Stage stage = (Stage) mainScreen.getScene().getWindow();
		try {
		    Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root,600,400);
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
