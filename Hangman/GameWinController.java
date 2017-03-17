import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameWinController {

	@FXML
	Button newGame, quit;
	
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
	
	public void quitGameAction() {
		 // get a handle to the stage
        Stage stage = (Stage) quit.getScene().getWindow();
        // do what you have to do
        stage.close();
	}

}
