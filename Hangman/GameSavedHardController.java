import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import com.sun.prism.paint.Color;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GameSavedHardController implements Initializable, EventHandler<ActionEvent> {

	@FXML
	Button a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z;
	@FXML
	Button saveGame, quit;
	@FXML
	TextField first, second, third, fourth, fifth, sixth, seventh, eigth;
	@FXML
	ImageView imgContainer;
	
	String[] array = (String[]) new String[11];
	int imgCount = 0;
		
	StringBuilder winningString = new StringBuilder("");
	StringBuilder copyString = new StringBuilder("");
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try (FileInputStream fi = new FileInputStream("savedGame.txt"); ObjectInputStream obj = new ObjectInputStream(fi);) {
			array = (String[]) obj.readObject();
			imgCount = Integer.parseInt((String) array[2]);
			copyString = new StringBuilder((String) array[1]);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException c) {
			System.out.println(c.getMessage());
		}
		
		String tmpX = "stage"+ imgCount + ".png";
		File file = new File(tmpX);
		Image image = new Image(file.toURI().toString());
		imgContainer.setImage(image);
		
		if(!array[3].isEmpty()) {
			first.setText(array[3]);
			if(array[3].equals("A")) {
				a.setDisable(true);
			}
			if(array[3].equals("B")) {
				b.setDisable(true);
			}
			if(array[3].equals("C")) {
				c.setDisable(true);
			}
			if(array[3].equals("D")) {
				d.setDisable(true);
			}
			if(array[3].equals("E")) {
				e.setDisable(true);
			}
			if(array[3].equals("F")) {
				f.setDisable(true);
			}
			if(array[3].equals("G")) {
				g.setDisable(true);
			}
			if(array[3].equals("H")) {
				h.setDisable(true);
			}
			if(array[3].equals("I")) {
				i.setDisable(true);
			}
			if(array[3].equals("J")) {
				j.setDisable(true);
			}
			if(array[3].equals("K")) {
				k.setDisable(true);
			}
			if(array[3].equals("L")) {
				l.setDisable(true);
			}
			if(array[3].equals("M")) {
				m.setDisable(true);
			}
			if(array[3].equals("N")) {
				n.setDisable(true);
			}
			if(array[3].equals("O")) {
				m.setDisable(true);
			}
			if(array[3].equals("P")) {
				p.setDisable(true);
			}
			if(array[3].equals("Q")) {
				q.setDisable(true);
			}
			if(array[3].equals("R")) {
				r.setDisable(true);
			}
			if(array[3].equals("S")) {
				s.setDisable(true);
			}
			if(array[3].equals("T")) {
				t.setDisable(true);
			}
			if(array[3].equals("U")) {
				u.setDisable(true);
			}
			if(array[3].equals("V")) {
				v.setDisable(true);
			}
			if(array[3].equals("W")) {
				w.setDisable(true);
			}
			if(array[3].equals("X")) {
				x.setDisable(true);
			}
			if(array[3].equals("Y")) {
				y.setDisable(true);
			}
			if(array[3].equals("Z")) {
				z.setDisable(true);
			}
		}
		if(!array[4].isEmpty()) {
			second.setText(array[4]);
			if(array[4].equals("A")) {
				a.setDisable(true);
			}
			if(array[4].equals("B")) {
				b.setDisable(true);
			}
			if(array[4].equals("C")) {
				c.setDisable(true);
			}
			if(array[4].equals("D")) {
				d.setDisable(true);
			}
			if(array[4].equals("E")) {
				e.setDisable(true);
			}
			if(array[4].equals("F")) {
				f.setDisable(true);
			}
			if(array[4].equals("G")) {
				g.setDisable(true);
			}
			if(array[4].equals("H")) {
				h.setDisable(true);
			}
			if(array[4].equals("I")) {
				i.setDisable(true);
			}
			if(array[4].equals("J")) {
				j.setDisable(true);
			}
			if(array[4].equals("K")) {
				k.setDisable(true);
			}
			if(array[4].equals("L")) {
				l.setDisable(true);
			}
			if(array[4].equals("M")) {
				m.setDisable(true);
			}
			if(array[4].equals("N")) {
				n.setDisable(true);
			}
			if(array[4].equals("O")) {
				m.setDisable(true);
			}
			if(array[4].equals("P")) {
				p.setDisable(true);
			}
			if(array[4].equals("Q")) {
				q.setDisable(true);
			}
			if(array[4].equals("R")) {
				r.setDisable(true);
			}
			if(array[4].equals("S")) {
				s.setDisable(true);
			}
			if(array[4].equals("T")) {
				t.setDisable(true);
			}
			if(array[4].equals("U")) {
				u.setDisable(true);
			}
			if(array[4].equals("V")) {
				v.setDisable(true);
			}
			if(array[4].equals("W")) {
				w.setDisable(true);
			}
			if(array[4].equals("X")) {
				x.setDisable(true);
			}
			if(array[4].equals("Y")) {
				y.setDisable(true);
			}
			if(array[4].equals("Z")) {
				z.setDisable(true);
			}
		}
		if(!array[5].isEmpty()) {
			third.setText(array[5]);
			if(array[5].equals("A")) {
				a.setDisable(true);
			}
			if(array[5].equals("B")) {
				b.setDisable(true);
			}
			if(array[5].equals("C")) {
				c.setDisable(true);
			}
			if(array[5].equals("D")) {
				d.setDisable(true);
			}
			if(array[5].equals("E")) {
				e.setDisable(true);
			}
			if(array[5].equals("F")) {
				f.setDisable(true);
			}
			if(array[5].equals("G")) {
				g.setDisable(true);
			}
			if(array[5].equals("H")) {
				h.setDisable(true);
			}
			if(array[5].equals("I")) {
				i.setDisable(true);
			}
			if(array[5].equals("J")) {
				j.setDisable(true);
			}
			if(array[5].equals("K")) {
				k.setDisable(true);
			}
			if(array[5].equals("L")) {
				l.setDisable(true);
			}
			if(array[5].equals("M")) {
				m.setDisable(true);
			}
			if(array[5].equals("N")) {
				n.setDisable(true);
			}
			if(array[5].equals("O")) {
				m.setDisable(true);
			}
			if(array[5].equals("P")) {
				p.setDisable(true);
			}
			if(array[5].equals("Q")) {
				q.setDisable(true);
			}
			if(array[5].equals("R")) {
				r.setDisable(true);
			}
			if(array[5].equals("S")) {
				s.setDisable(true);
			}
			if(array[5].equals("T")) {
				t.setDisable(true);
			}
			if(array[5].equals("U")) {
				u.setDisable(true);
			}
			if(array[5].equals("V")) {
				v.setDisable(true);
			}
			if(array[5].equals("W")) {
				w.setDisable(true);
			}
			if(array[5].equals("X")) {
				x.setDisable(true);
			}
			if(array[5].equals("Y")) {
				y.setDisable(true);
			}
			if(array[5].equals("Z")) {
				z.setDisable(true);
			}
		}
		if(!array[6].isEmpty()) {
			fourth.setText(array[6]);
			if(array[6].equals("A")) {
				a.setDisable(true);
			}
			if(array[6].equals("B")) {
				b.setDisable(true);
			}
			if(array[6].equals("C")) {
				c.setDisable(true);
			}
			if(array[6].equals("D")) {
				d.setDisable(true);
			}
			if(array[6].equals("E")) {
				e.setDisable(true);
			}
			if(array[6].equals("F")) {
				f.setDisable(true);
			}
			if(array[6].equals("G")) {
				g.setDisable(true);
			}
			if(array[6].equals("H")) {
				h.setDisable(true);
			}
			if(array[6].equals("I")) {
				i.setDisable(true);
			}
			if(array[6].equals("J")) {
				j.setDisable(true);
			}
			if(array[6].equals("K")) {
				k.setDisable(true);
			}
			if(array[6].equals("L")) {
				l.setDisable(true);
			}
			if(array[6].equals("M")) {
				m.setDisable(true);
			}
			if(array[6].equals("N")) {
				n.setDisable(true);
			}
			if(array[6].equals("O")) {
				m.setDisable(true);
			}
			if(array[6].equals("P")) {
				p.setDisable(true);
			}
			if(array[6].equals("Q")) {
				q.setDisable(true);
			}
			if(array[6].equals("R")) {
				r.setDisable(true);
			}
			if(array[6].equals("S")) {
				s.setDisable(true);
			}
			if(array[6].equals("T")) {
				t.setDisable(true);
			}
			if(array[6].equals("U")) {
				u.setDisable(true);
			}
			if(array[6].equals("V")) {
				v.setDisable(true);
			}
			if(array[6].equals("W")) {
				w.setDisable(true);
			}
			if(array[6].equals("X")) {
				x.setDisable(true);
			}
			if(array[6].equals("Y")) {
				y.setDisable(true);
			}
			if(array[6].equals("Z")) {
				z.setDisable(true);
			}
		}
		if(!array[7].isEmpty()) {
			fifth.setText(array[7]);
			if(array[7].equals("A")) {
				a.setDisable(true);
			}
			if(array[7].equals("B")) {
				b.setDisable(true);
			}
			if(array[7].equals("C")) {
				c.setDisable(true);
			}
			if(array[7].equals("D")) {
				d.setDisable(true);
			}
			if(array[7].equals("E")) {
				e.setDisable(true);
			}
			if(array[7].equals("F")) {
				f.setDisable(true);
			}
			if(array[7].equals("G")) {
				g.setDisable(true);
			}
			if(array[7].equals("H")) {
				h.setDisable(true);
			}
			if(array[7].equals("I")) {
				i.setDisable(true);
			}
			if(array[7].equals("J")) {
				j.setDisable(true);
			}
			if(array[7].equals("K")) {
				k.setDisable(true);
			}
			if(array[7].equals("L")) {
				l.setDisable(true);
			}
			if(array[7].equals("M")) {
				m.setDisable(true);
			}
			if(array[7].equals("N")) {
				n.setDisable(true);
			}
			if(array[7].equals("O")) {
				m.setDisable(true);
			}
			if(array[7].equals("P")) {
				p.setDisable(true);
			}
			if(array[7].equals("Q")) {
				q.setDisable(true);
			}
			if(array[7].equals("R")) {
				r.setDisable(true);
			}
			if(array[7].equals("S")) {
				s.setDisable(true);
			}
			if(array[7].equals("T")) {
				t.setDisable(true);
			}
			if(array[7].equals("U")) {
				u.setDisable(true);
			}
			if(array[7].equals("V")) {
				v.setDisable(true);
			}
			if(array[7].equals("W")) {
				w.setDisable(true);
			}
			if(array[7].equals("X")) {
				x.setDisable(true);
			}
			if(array[7].equals("Y")) {
				y.setDisable(true);
			}
			if(array[7].equals("Z")) {
				z.setDisable(true);
			}
		}
		if(!array[8].isEmpty()) {
			sixth.setText(array[8]);
			if(array[8].equals("A")) {
				a.setDisable(true);
			}
			if(array[8].equals("B")) {
				b.setDisable(true);
			}
			if(array[8].equals("C")) {
				c.setDisable(true);
			}
			if(array[8].equals("D")) {
				d.setDisable(true);
			}
			if(array[8].equals("E")) {
				e.setDisable(true);
			}
			if(array[8].equals("F")) {
				f.setDisable(true);
			}
			if(array[8].equals("G")) {
				g.setDisable(true);
			}
			if(array[8].equals("H")) {
				h.setDisable(true);
			}
			if(array[8].equals("I")) {
				i.setDisable(true);
			}
			if(array[8].equals("J")) {
				j.setDisable(true);
			}
			if(array[8].equals("K")) {
				k.setDisable(true);
			}
			if(array[8].equals("L")) {
				l.setDisable(true);
			}
			if(array[8].equals("M")) {
				m.setDisable(true);
			}
			if(array[8].equals("N")) {
				n.setDisable(true);
			}
			if(array[8].equals("O")) {
				m.setDisable(true);
			}
			if(array[8].equals("P")) {
				p.setDisable(true);
			}
			if(array[8].equals("Q")) {
				q.setDisable(true);
			}
			if(array[8].equals("R")) {
				r.setDisable(true);
			}
			if(array[8].equals("S")) {
				s.setDisable(true);
			}
			if(array[8].equals("T")) {
				t.setDisable(true);
			}
			if(array[8].equals("U")) {
				u.setDisable(true);
			}
			if(array[8].equals("V")) {
				v.setDisable(true);
			}
			if(array[8].equals("W")) {
				w.setDisable(true);
			}
			if(array[8].equals("X")) {
				x.setDisable(true);
			}
			if(array[8].equals("Y")) {
				y.setDisable(true);
			}
			if(array[8].equals("Z")) {
				z.setDisable(true);
			}
		}
		if(!array[9].isEmpty()) {
			seventh.setText(array[9]);
			if(array[9].equals("A")) {
				a.setDisable(true);
			}
			if(array[9].equals("B")) {
				b.setDisable(true);
			}
			if(array[9].equals("C")) {
				c.setDisable(true);
			}
			if(array[9].equals("D")) {
				d.setDisable(true);
			}
			if(array[9].equals("E")) {
				e.setDisable(true);
			}
			if(array[9].equals("F")) {
				f.setDisable(true);
			}
			if(array[9].equals("G")) {
				g.setDisable(true);
			}
			if(array[9].equals("H")) {
				h.setDisable(true);
			}
			if(array[9].equals("I")) {
				i.setDisable(true);
			}
			if(array[9].equals("J")) {
				j.setDisable(true);
			}
			if(array[9].equals("K")) {
				k.setDisable(true);
			}
			if(array[9].equals("L")) {
				l.setDisable(true);
			}
			if(array[9].equals("M")) {
				m.setDisable(true);
			}
			if(array[9].equals("N")) {
				n.setDisable(true);
			}
			if(array[9].equals("O")) {
				m.setDisable(true);
			}
			if(array[9].equals("P")) {
				p.setDisable(true);
			}
			if(array[9].equals("Q")) {
				q.setDisable(true);
			}
			if(array[9].equals("R")) {
				r.setDisable(true);
			}
			if(array[9].equals("S")) {
				s.setDisable(true);
			}
			if(array[9].equals("T")) {
				t.setDisable(true);
			}
			if(array[9].equals("U")) {
				u.setDisable(true);
			}
			if(array[9].equals("V")) {
				v.setDisable(true);
			}
			if(array[9].equals("W")) {
				w.setDisable(true);
			}
			if(array[9].equals("X")) {
				x.setDisable(true);
			}
			if(array[9].equals("Y")) {
				y.setDisable(true);
			}
			if(array[9].equals("Z")) {
				z.setDisable(true);
			}
		}
		if(!array[10].isEmpty()) {
			eigth.setText(array[10]);
			if(array[10].equals("A")) {
				a.setDisable(true);
			}
			if(array[10].equals("B")) {
				b.setDisable(true);
			}
			if(array[10].equals("C")) {
				c.setDisable(true);
			}
			if(array[10].equals("D")) {
				d.setDisable(true);
			}
			if(array[10].equals("E")) {
				e.setDisable(true);
			}
			if(array[10].equals("F")) {
				f.setDisable(true);
			}
			if(array[10].equals("G")) {
				g.setDisable(true);
			}
			if(array[10].equals("H")) {
				h.setDisable(true);
			}
			if(array[10].equals("I")) {
				i.setDisable(true);
			}
			if(array[10].equals("J")) {
				j.setDisable(true);
			}
			if(array[10].equals("K")) {
				k.setDisable(true);
			}
			if(array[10].equals("L")) {
				l.setDisable(true);
			}
			if(array[10].equals("M")) {
				m.setDisable(true);
			}
			if(array[10].equals("N")) {
				n.setDisable(true);
			}
			if(array[10].equals("O")) {
				m.setDisable(true);
			}
			if(array[10].equals("P")) {
				p.setDisable(true);
			}
			if(array[10].equals("Q")) {
				q.setDisable(true);
			}
			if(array[10].equals("R")) {
				r.setDisable(true);
			}
			if(array[10].equals("S")) {
				s.setDisable(true);
			}
			if(array[10].equals("T")) {
				t.setDisable(true);
			}
			if(array[10].equals("U")) {
				u.setDisable(true);
			}
			if(array[10].equals("V")) {
				v.setDisable(true);
			}
			if(array[10].equals("W")) {
				w.setDisable(true);
			}
			if(array[10].equals("X")) {
				x.setDisable(true);
			}
			if(array[10].equals("Y")) {
				y.setDisable(true);
			}
			if(array[10].equals("Z")) {
				z.setDisable(true);
			}
		}
	}
	
	@FXML
	public void handle(ActionEvent myEvent) {
		Button pressedButton = (Button)myEvent.getSource();
		
		if(pressedButton == saveGame) {
			String[] incompleteString = new String[11];
			incompleteString[0] = "Hard";
			incompleteString[1] = copyString.toString();
			incompleteString[2] = Integer.toString(imgCount);
			
			if(first.getText() != null && !first.getText().isEmpty()) {
				incompleteString[3] = first.getText();
			} else {
				incompleteString[3] = "";
			}
			if(second.getText() != null && !second.getText().isEmpty()) {
				incompleteString[4] = second.getText();
			} else {
				incompleteString[4] = "";
			}
			if(third.getText() != null && !third.getText().isEmpty()) {
				incompleteString[5] = third.getText();
			} else {
				incompleteString[5] = "";
			}
			if(fourth.getText() != null && !fourth.getText().isEmpty()) {
				incompleteString[6] = fourth.getText();
			} else {
				incompleteString[6] = "";
			}
			if(fifth.getText() != null && !fifth.getText().isEmpty()) {
				incompleteString[7] = fifth.getText();
			} else {
				incompleteString[7] = "";
			}
			if(sixth.getText() != null && !sixth.getText().isEmpty()) {
				incompleteString[8] = sixth.getText();
			} else {
				incompleteString[8] = "";
			}
			if(seventh.getText() != null && !seventh.getText().isEmpty()) {
				incompleteString[9] = seventh.getText();
			} else {
				incompleteString[9] = "";
			}
			if(eigth.getText() != null && !eigth.getText().isEmpty()) {
				incompleteString[10] = eigth.getText();
			} else {
				incompleteString[10] = "";
			}
			try (FileOutputStream fo = new FileOutputStream("savedGame.txt"); ObjectOutputStream obj = new ObjectOutputStream(fo);) {
				obj.writeObject(incompleteString);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			Stage stage = (Stage) saveGame.getScene().getWindow();
			try {
			    Parent root = FXMLLoader.load(getClass().getResource("GameSaved.fxml"));
				Scene scene = new Scene(root,600,400);
				stage.setScene(scene);
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(pressedButton == quit) {
			Stage stage = (Stage) quit.getScene().getWindow();
			stage.close();
		}
		
		if(pressedButton == a) {
			if(copyString.charAt(0) == 'a') {
				first.setText("A");
			}
			if(copyString.charAt(1) == 'a') {
				second.setText("A");
			}
			if(copyString.charAt(2) == 'a') {
				third.setText("A");
			}
			if(copyString.charAt(3) == 'a') {
				fourth.setText("A");
			}
			if(copyString.charAt(4) == 'a') {
				fifth.setText("A");
			}
			if(copyString.charAt(5) == 'a') {
				sixth.setText("A");
			}
			if(copyString.charAt(6) == 'a') {
				seventh.setText("A");
			}
			if(copyString.charAt(7) == 'a') {
				eigth.setText("A");
			}
			if (first.getText().equals(String.valueOf(copyString.charAt(0))) && second.getText().equals(String.valueOf(copyString.charAt(1))) && third.getText().equals(String.valueOf(copyString.charAt(2))) && fourth.getText().equals(String.valueOf(copyString.charAt(3))) && fifth.getText().equals(String.valueOf(copyString.charAt(4))) && sixth.getText().equals(String.valueOf(copyString.charAt(5))) && seventh.getText().equals(String.valueOf(copyString.charAt(6))) && eigth.getText().equals(String.valueOf(copyString.charAt(7)))) {
				Stage stage = (Stage) z.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(copyString.charAt(0) != 'a' && copyString.charAt(1) != 'a' && copyString.charAt(2) != 'a' && copyString.charAt(3) != 'a' && copyString.charAt(4) != 'a' && copyString.charAt(5) != 'a' && copyString.charAt(6) != 'a' && copyString.charAt(7) != 'a') {
				imgCount++;
				if(imgCount == 9) {
					first.setText(String.valueOf(copyString.charAt(0)));
					second.setText(String.valueOf(copyString.charAt(1)));
					third.setText(String.valueOf(copyString.charAt(2)));
					fourth.setText(String.valueOf(copyString.charAt(3)));
					fifth.setText(String.valueOf(copyString.charAt(4)));
					sixth.setText(String.valueOf(copyString.charAt(5)));
					seventh.setText(String.valueOf(copyString.charAt(6)));
					eigth.setText(String.valueOf(copyString.charAt(7)));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Stage stage = (Stage) a.getScene().getWindow();
					try {
					    Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
						Scene scene = new Scene(root,1280,760);
						stage.setScene(scene);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				String tmpX = "stage"+ imgCount + ".png";
				File file = new File(tmpX);
				Image image = new Image(file.toURI().toString());
				imgContainer.setImage(image);
			}
			a.setStyle("-fx-background-color: #DC143C;");
		 	a.setDisable(true);
		}
		
		if(pressedButton == b) {
			if(copyString.charAt(0) == 'b') {
				first.setText("B");
			}
			if(copyString.charAt(1) == 'b') {
				second.setText("B");
			}
			if(copyString.charAt(2) == 'b') {
				third.setText("B");
			}
			if(copyString.charAt(3) == 'b') {
				fourth.setText("B");
			}
			if(copyString.charAt(4) == 'b') {
				fifth.setText("B");
			}
			if(copyString.charAt(5) == 'b') {
				sixth.setText("B");
			}
			if(copyString.charAt(6) == 'b') {
				seventh.setText("B");
			}
			if(copyString.charAt(7) == 'b') {
				eigth.setText("B");
			}
			if (first.getText().equals(String.valueOf(copyString.charAt(0))) && second.getText().equals(String.valueOf(copyString.charAt(1))) && third.getText().equals(String.valueOf(copyString.charAt(2))) && fourth.getText().equals(String.valueOf(copyString.charAt(3))) && fifth.getText().equals(String.valueOf(copyString.charAt(4))) && sixth.getText().equals(String.valueOf(copyString.charAt(5))) && seventh.getText().equals(String.valueOf(copyString.charAt(6))) && eigth.getText().equals(String.valueOf(copyString.charAt(7)))) {
				Stage stage = (Stage) z.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(copyString.charAt(0) != 'b' && copyString.charAt(1) != 'b' && copyString.charAt(2) != 'b' && copyString.charAt(3) != 'b' && copyString.charAt(4) != 'b' && copyString.charAt(5) != 'b' && copyString.charAt(6) != 'b' && copyString.charAt(7) != 'b') {
				imgCount++;
				if(imgCount == 9) {
					first.setText(String.valueOf(copyString.charAt(0)));
					second.setText(String.valueOf(copyString.charAt(1)));
					third.setText(String.valueOf(copyString.charAt(2)));
					fourth.setText(String.valueOf(copyString.charAt(3)));
					fifth.setText(String.valueOf(copyString.charAt(4)));
					sixth.setText(String.valueOf(copyString.charAt(5)));
					seventh.setText(String.valueOf(copyString.charAt(6)));
					eigth.setText(String.valueOf(copyString.charAt(7)));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Stage stage = (Stage) b.getScene().getWindow();
					try {
					    Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
					    Scene scene = new Scene(root,1280,760);
						stage.setScene(scene);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				String tmpX = "stage"+ imgCount + ".png";
				File file = new File(tmpX);
				Image image = new Image(file.toURI().toString());
				imgContainer.setImage(image);
			}
			b.setStyle("-fx-background-color: #DC143C;");
		 	b.setDisable(true);
		}
		
		if(pressedButton == c) {
			if(copyString.charAt(0) == 'c') {
				first.setText("C");
			}
			if(copyString.charAt(1) == 'c') {
				second.setText("C");
			}
			if(copyString.charAt(2) == 'c') {
				third.setText("C");
			}
			if(copyString.charAt(3) == 'c') {
				fourth.setText("C");
			}
			if(copyString.charAt(4) == 'c') {
				fifth.setText("C");
			}
			if(copyString.charAt(5) == 'c') {
				sixth.setText("C");
			}
			if(copyString.charAt(6) == 'c') {
				seventh.setText("C");
			}
			if(copyString.charAt(7) == 'c') {
				eigth.setText("C");
			}
			if (first.getText().equals(String.valueOf(copyString.charAt(0))) && second.getText().equals(String.valueOf(copyString.charAt(1))) && third.getText().equals(String.valueOf(copyString.charAt(2))) && fourth.getText().equals(String.valueOf(copyString.charAt(3))) && fifth.getText().equals(String.valueOf(copyString.charAt(4))) && sixth.getText().equals(String.valueOf(copyString.charAt(5))) && seventh.getText().equals(String.valueOf(copyString.charAt(6))) && eigth.getText().equals(String.valueOf(copyString.charAt(7)))) {
				Stage stage = (Stage) z.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(copyString.charAt(0) != 'c' && copyString.charAt(1) != 'c' && copyString.charAt(2) != 'c' && copyString.charAt(3) != 'c' && copyString.charAt(4) != 'c' && copyString.charAt(5) != 'c' && copyString.charAt(6) != 'c' && copyString.charAt(7) != 'c') {
				imgCount++;
				if(imgCount == 9) {
					first.setText(String.valueOf(copyString.charAt(0)));
					second.setText(String.valueOf(copyString.charAt(1)));
					third.setText(String.valueOf(copyString.charAt(2)));
					fourth.setText(String.valueOf(copyString.charAt(3)));
					fifth.setText(String.valueOf(copyString.charAt(4)));
					sixth.setText(String.valueOf(copyString.charAt(5)));
					seventh.setText(String.valueOf(copyString.charAt(6)));
					eigth.setText(String.valueOf(copyString.charAt(7)));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Stage stage = (Stage) c.getScene().getWindow();
					try {
					    Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
					    Scene scene = new Scene(root,1280,760);
						stage.setScene(scene);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				String tmpX = "stage"+ imgCount + ".png";
				File file = new File(tmpX);
				Image image = new Image(file.toURI().toString());
				imgContainer.setImage(image);
			}
			c.setStyle("-fx-background-color: #DC143C;");
		 	c.setDisable(true);
		}
		
		if(pressedButton == d) {
			if(copyString.charAt(0) == 'd') {
				first.setText("D");
			}
			if(copyString.charAt(1) == 'd') {
				second.setText("D");
			}
			if(copyString.charAt(2) == 'd') {
				third.setText("D");
			}
			if(copyString.charAt(3) == 'd') {
				fourth.setText("D");
			}
			if(copyString.charAt(4) == 'd') {
				fifth.setText("D");
			}
			if(copyString.charAt(5) == 'd') {
				sixth.setText("D");
			}
			if(copyString.charAt(6) == 'd') {
				seventh.setText("D");
			}
			if(copyString.charAt(7) == 'd') {
				eigth.setText("D");
			}
			if (first.getText().equals(String.valueOf(copyString.charAt(0))) && second.getText().equals(String.valueOf(copyString.charAt(1))) && third.getText().equals(String.valueOf(copyString.charAt(2))) && fourth.getText().equals(String.valueOf(copyString.charAt(3))) && fifth.getText().equals(String.valueOf(copyString.charAt(4))) && sixth.getText().equals(String.valueOf(copyString.charAt(5))) && seventh.getText().equals(String.valueOf(copyString.charAt(6))) && eigth.getText().equals(String.valueOf(copyString.charAt(7)))) {
				Stage stage = (Stage) z.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(copyString.charAt(0) != 'd' && copyString.charAt(1) != 'd' && copyString.charAt(2) != 'd' && copyString.charAt(3) != 'd' && copyString.charAt(4) != 'd' && copyString.charAt(5) != 'd' && copyString.charAt(6) != 'd' && copyString.charAt(7) != 'd') {
				imgCount++;
				if(imgCount == 9) {
					first.setText(String.valueOf(copyString.charAt(0)));
					second.setText(String.valueOf(copyString.charAt(1)));
					third.setText(String.valueOf(copyString.charAt(2)));
					fourth.setText(String.valueOf(copyString.charAt(3)));
					fifth.setText(String.valueOf(copyString.charAt(4)));
					sixth.setText(String.valueOf(copyString.charAt(5)));
					seventh.setText(String.valueOf(copyString.charAt(6)));
					eigth.setText(String.valueOf(copyString.charAt(7)));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Stage stage = (Stage) d.getScene().getWindow();
					try {
					    Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
					    Scene scene = new Scene(root,1280,760);
						stage.setScene(scene);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				String tmpX = "stage"+ imgCount + ".png";
				File file = new File(tmpX);
				Image image = new Image(file.toURI().toString());
				imgContainer.setImage(image);
			}
			d.setStyle("-fx-background-color: #DC143C;");
		 	d.setDisable(true);
		}
		
		if(pressedButton == e) {
			if(copyString.charAt(0) == 'e') {
				first.setText("E");
			}
			if(copyString.charAt(1) == 'e') {
				second.setText("E");
			}
			if(copyString.charAt(2) == 'e') {
				third.setText("E");
			}
			if(copyString.charAt(3) == 'e') {
				fourth.setText("E");
			}
			if(copyString.charAt(4) == 'e') {
				fifth.setText("E");
			}
			if(copyString.charAt(5) == 'e') {
				sixth.setText("E");
			}
			if(copyString.charAt(6) == 'e') {
				seventh.setText("E");
			}
			if(copyString.charAt(7) == 'e') {
				eigth.setText("E");
			}
			if (first.getText().equals(String.valueOf(copyString.charAt(0))) && second.getText().equals(String.valueOf(copyString.charAt(1))) && third.getText().equals(String.valueOf(copyString.charAt(2))) && fourth.getText().equals(String.valueOf(copyString.charAt(3))) && fifth.getText().equals(String.valueOf(copyString.charAt(4))) && sixth.getText().equals(String.valueOf(copyString.charAt(5))) && seventh.getText().equals(String.valueOf(copyString.charAt(6))) && eigth.getText().equals(String.valueOf(copyString.charAt(7)))) {
				Stage stage = (Stage) z.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(copyString.charAt(0) != 'e' && copyString.charAt(1) != 'e' && copyString.charAt(2) != 'e' && copyString.charAt(3) != 'e' && copyString.charAt(4) != 'e' && copyString.charAt(5) != 'e' && copyString.charAt(6) != 'e' && copyString.charAt(7) != 'e') {
				imgCount++;
				if(imgCount == 9) {
					first.setText(String.valueOf(copyString.charAt(0)));
					second.setText(String.valueOf(copyString.charAt(1)));
					third.setText(String.valueOf(copyString.charAt(2)));
					fourth.setText(String.valueOf(copyString.charAt(3)));
					fifth.setText(String.valueOf(copyString.charAt(4)));
					sixth.setText(String.valueOf(copyString.charAt(5)));
					seventh.setText(String.valueOf(copyString.charAt(6)));
					eigth.setText(String.valueOf(copyString.charAt(7)));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Stage stage = (Stage) e.getScene().getWindow();
					try {
					    Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
					    Scene scene = new Scene(root,1280,760);
						stage.setScene(scene);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				String tmpX = "stage"+ imgCount + ".png";
				File file = new File(tmpX);
				Image image = new Image(file.toURI().toString());
				imgContainer.setImage(image);
			}
			e.setStyle("-fx-background-color: #DC143C;");
		 	e.setDisable(true);
		}
		
		if(pressedButton == f) {
			if(copyString.charAt(0) == 'f') {
				first.setText("F");
			}
			if(copyString.charAt(1) == 'f') {
				second.setText("F");
			}
			if(copyString.charAt(2) == 'f') {
				third.setText("F");
			}
			if(copyString.charAt(3) == 'f') {
				fourth.setText("F");
			}
			if(copyString.charAt(4) == 'f') {
				fifth.setText("F");
			}
			if(copyString.charAt(5) == 'f') {
				sixth.setText("F");
			}
			if(copyString.charAt(6) == 'f') {
				seventh.setText("F");
			}
			if(copyString.charAt(7) == 'f') {
				eigth.setText("F");
			}
			if (first.getText().equals(String.valueOf(copyString.charAt(0))) && second.getText().equals(String.valueOf(copyString.charAt(1))) && third.getText().equals(String.valueOf(copyString.charAt(2))) && fourth.getText().equals(String.valueOf(copyString.charAt(3))) && fifth.getText().equals(String.valueOf(copyString.charAt(4))) && sixth.getText().equals(String.valueOf(copyString.charAt(5))) && seventh.getText().equals(String.valueOf(copyString.charAt(6))) && eigth.getText().equals(String.valueOf(copyString.charAt(7)))) {
				Stage stage = (Stage) z.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(copyString.charAt(0) != 'f' && copyString.charAt(1) != 'f' && copyString.charAt(2) != 'f' && copyString.charAt(3) != 'f' && copyString.charAt(4) != 'f' && copyString.charAt(5) != 'f' && copyString.charAt(6) != 'f' && copyString.charAt(7) != 'f') {
				imgCount++;
				if(imgCount == 9) {
					first.setText(String.valueOf(copyString.charAt(0)));
					second.setText(String.valueOf(copyString.charAt(1)));
					third.setText(String.valueOf(copyString.charAt(2)));
					fourth.setText(String.valueOf(copyString.charAt(3)));
					fifth.setText(String.valueOf(copyString.charAt(4)));
					sixth.setText(String.valueOf(copyString.charAt(5)));
					seventh.setText(String.valueOf(copyString.charAt(6)));
					eigth.setText(String.valueOf(copyString.charAt(7)));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Stage stage = (Stage) f.getScene().getWindow();
					try {
					    Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
					    Scene scene = new Scene(root,1280,760);
						stage.setScene(scene);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				String tmpX = "stage"+ imgCount + ".png";
				File file = new File(tmpX);
				Image image = new Image(file.toURI().toString());
				imgContainer.setImage(image);
			}
			f.setStyle("-fx-background-color: #DC143C;");
		 	f.setDisable(true);
		}
		
		if(pressedButton == g) {
			if(copyString.charAt(0) == 'g') {
				first.setText("G");
			}
			if(copyString.charAt(1) == 'g') {
				second.setText("G");
			}
			if(copyString.charAt(2) == 'g') {
				third.setText("G");
			}
			if(copyString.charAt(3) == 'g') {
				fourth.setText("G");
			}
			if(copyString.charAt(4) == 'g') {
				fifth.setText("G");
			}
			if(copyString.charAt(5) == 'g') {
				sixth.setText("G");
			}
			if(copyString.charAt(6) == 'g') {
				seventh.setText("G");
			}
			if(copyString.charAt(7) == 'g') {
				eigth.setText("G");
			}
			if (first.getText().equals(String.valueOf(copyString.charAt(0))) && second.getText().equals(String.valueOf(copyString.charAt(1))) && third.getText().equals(String.valueOf(copyString.charAt(2))) && fourth.getText().equals(String.valueOf(copyString.charAt(3))) && fifth.getText().equals(String.valueOf(copyString.charAt(4))) && sixth.getText().equals(String.valueOf(copyString.charAt(5))) && seventh.getText().equals(String.valueOf(copyString.charAt(6))) && eigth.getText().equals(String.valueOf(copyString.charAt(7)))) {
				Stage stage = (Stage) z.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(copyString.charAt(0) != 'g' && copyString.charAt(1) != 'g' && copyString.charAt(2) != 'g' && copyString.charAt(3) != 'g' && copyString.charAt(4) != 'g' && copyString.charAt(5) != 'g' && copyString.charAt(6) != 'g' && copyString.charAt(7) != 'q') {
				imgCount++;
				if(imgCount == 9) {
					first.setText(String.valueOf(copyString.charAt(0)));
					second.setText(String.valueOf(copyString.charAt(1)));
					third.setText(String.valueOf(copyString.charAt(2)));
					fourth.setText(String.valueOf(copyString.charAt(3)));
					fifth.setText(String.valueOf(copyString.charAt(4)));
					sixth.setText(String.valueOf(copyString.charAt(5)));
					seventh.setText(String.valueOf(copyString.charAt(6)));
					eigth.setText(String.valueOf(copyString.charAt(7)));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Stage stage = (Stage) g.getScene().getWindow();
					try {
					    Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
					    Scene scene = new Scene(root,1280,760);
						stage.setScene(scene);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				String tmpX = "stage"+ imgCount + ".png";
				File file = new File(tmpX);
				Image image = new Image(file.toURI().toString());
				imgContainer.setImage(image);
			}
			g.setStyle("-fx-background-color: #DC143C;");
		 	g.setDisable(true);
		}
		
		if(pressedButton == h) {
			if(copyString.charAt(0) == 'h') {
				first.setText("H");
			}
			if(copyString.charAt(1) == 'h') {
				second.setText("H");
			}
			if(copyString.charAt(2) == 'h') {
				third.setText("H");
			}
			if(copyString.charAt(3) == 'h') {
				fourth.setText("H");
			}
			if(copyString.charAt(4) == 'h') {
				fifth.setText("H");
			}
			if(copyString.charAt(5) == 'h') {
				sixth.setText("H");
			}
			if(copyString.charAt(6) == 'h') {
				seventh.setText("H");
			}
			if(copyString.charAt(7) == 'h') {
				eigth.setText("H");
			}
			if (first.getText().equals(String.valueOf(copyString.charAt(0))) && second.getText().equals(String.valueOf(copyString.charAt(1))) && third.getText().equals(String.valueOf(copyString.charAt(2))) && fourth.getText().equals(String.valueOf(copyString.charAt(3))) && fifth.getText().equals(String.valueOf(copyString.charAt(4))) && sixth.getText().equals(String.valueOf(copyString.charAt(5))) && seventh.getText().equals(String.valueOf(copyString.charAt(6))) && eigth.getText().equals(String.valueOf(copyString.charAt(7)))) {
				Stage stage = (Stage) z.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(copyString.charAt(0) != 'h' && copyString.charAt(1) != 'h' && copyString.charAt(2) != 'h' && copyString.charAt(3) != 'h' && copyString.charAt(4) != 'h' && copyString.charAt(5) != 'h' && copyString.charAt(6) != 'h' && copyString.charAt(7) != 'h') {
				imgCount++;
				if(imgCount == 9) {
					first.setText(String.valueOf(copyString.charAt(0)));
					second.setText(String.valueOf(copyString.charAt(1)));
					third.setText(String.valueOf(copyString.charAt(2)));
					fourth.setText(String.valueOf(copyString.charAt(3)));
					fifth.setText(String.valueOf(copyString.charAt(4)));
					sixth.setText(String.valueOf(copyString.charAt(5)));
					seventh.setText(String.valueOf(copyString.charAt(6)));
					eigth.setText(String.valueOf(copyString.charAt(7)));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Stage stage = (Stage) h.getScene().getWindow();
					try {
					    Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
					    Scene scene = new Scene(root,1280,760);
						stage.setScene(scene);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				String tmpX = "stage"+ imgCount + ".png";
				File file = new File(tmpX);
				Image image = new Image(file.toURI().toString());
				imgContainer.setImage(image);
			}
			h.setStyle("-fx-background-color: #DC143C;");
		 	h.setDisable(true);
		}
		
		if(pressedButton == i) {
			if(copyString.charAt(0) == 'i') {
				first.setText("I");
			}
			if(copyString.charAt(1) == 'i') {
				second.setText("I");
			}
			if(copyString.charAt(2) == 'i') {
				third.setText("I");
			}
			if(copyString.charAt(3) == 'i') {
				fourth.setText("I");
			}
			if(copyString.charAt(4) == 'i') {
				fifth.setText("I");
			}
			if(copyString.charAt(5) == 'i') {
				sixth.setText("I");
			}
			if(copyString.charAt(6) == 'i') {
				seventh.setText("I");
			}
			if(copyString.charAt(7) == 'i') {
				eigth.setText("I");
			}
			if (first.getText().equals(String.valueOf(copyString.charAt(0))) && second.getText().equals(String.valueOf(copyString.charAt(1))) && third.getText().equals(String.valueOf(copyString.charAt(2))) && fourth.getText().equals(String.valueOf(copyString.charAt(3))) && fifth.getText().equals(String.valueOf(copyString.charAt(4))) && sixth.getText().equals(String.valueOf(copyString.charAt(5))) && seventh.getText().equals(String.valueOf(copyString.charAt(6))) && eigth.getText().equals(String.valueOf(copyString.charAt(7)))) {
				Stage stage = (Stage) z.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(copyString.charAt(0) != 'i' && copyString.charAt(1) != 'i' && copyString.charAt(2) != 'i' && copyString.charAt(3) != 'i' && copyString.charAt(4) != 'i' && copyString.charAt(5) != 'i' && copyString.charAt(6) != 'i' && copyString.charAt(7) != 'i') {
				imgCount++;
				if(imgCount == 9) {
					first.setText(String.valueOf(copyString.charAt(0)));
					second.setText(String.valueOf(copyString.charAt(1)));
					third.setText(String.valueOf(copyString.charAt(2)));
					fourth.setText(String.valueOf(copyString.charAt(3)));
					fifth.setText(String.valueOf(copyString.charAt(4)));
					sixth.setText(String.valueOf(copyString.charAt(5)));
					seventh.setText(String.valueOf(copyString.charAt(6)));
					eigth.setText(String.valueOf(copyString.charAt(7)));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Stage stage = (Stage) i.getScene().getWindow();
					try {
					    Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
					    Scene scene = new Scene(root,1280,760);
						stage.setScene(scene);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				String tmpX = "stage"+ imgCount + ".png";
				File file = new File(tmpX);
				Image image = new Image(file.toURI().toString());
				imgContainer.setImage(image);
			}
			i.setStyle("-fx-background-color: #DC143C;");
		 	i.setDisable(true);
		}
		
		if(pressedButton == j) {
			if(copyString.charAt(0) == 'j') {
				first.setText("J");
			}
			if(copyString.charAt(1) == 'j') {
				second.setText("J");
			}
			if(copyString.charAt(2) == 'j') {
				third.setText("J");
			}
			if(copyString.charAt(3) == 'j') {
				fourth.setText("J");
			}
			if(copyString.charAt(4) == 'j') {
				fifth.setText("J");
			}
			if(copyString.charAt(5) == 'j') {
				sixth.setText("J");
			}
			if(copyString.charAt(6) == 'j') {
				seventh.setText("J");
			}
			if(copyString.charAt(7) == 'j') {
				eigth.setText("J");
			}
			if (first.getText().equals(String.valueOf(copyString.charAt(0))) && second.getText().equals(String.valueOf(copyString.charAt(1))) && third.getText().equals(String.valueOf(copyString.charAt(2))) && fourth.getText().equals(String.valueOf(copyString.charAt(3))) && fifth.getText().equals(String.valueOf(copyString.charAt(4))) && sixth.getText().equals(String.valueOf(copyString.charAt(5))) && seventh.getText().equals(String.valueOf(copyString.charAt(6))) && eigth.getText().equals(String.valueOf(copyString.charAt(7)))) {
				Stage stage = (Stage) z.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(copyString.charAt(0) != 'j' && copyString.charAt(1) != 'j' && copyString.charAt(2) != 'j' && copyString.charAt(3) != 'j' && copyString.charAt(4) != 'j' && copyString.charAt(0) != 'j' && copyString.charAt(6) != 'j' && copyString.charAt(7) != 'j') {
				imgCount++;
				if(imgCount == 9) {
					first.setText(String.valueOf(copyString.charAt(0)));
					second.setText(String.valueOf(copyString.charAt(1)));
					third.setText(String.valueOf(copyString.charAt(2)));
					fourth.setText(String.valueOf(copyString.charAt(3)));
					fifth.setText(String.valueOf(copyString.charAt(4)));
					sixth.setText(String.valueOf(copyString.charAt(5)));
					seventh.setText(String.valueOf(copyString.charAt(6)));
					eigth.setText(String.valueOf(copyString.charAt(7)));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Stage stage = (Stage) j.getScene().getWindow();
					try {
					    Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
					    Scene scene = new Scene(root,1280,760);
						stage.setScene(scene);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				String tmpX = "stage"+ imgCount + ".png";
				File file = new File(tmpX);
				Image image = new Image(file.toURI().toString());
				imgContainer.setImage(image);
			}
			j.setStyle("-fx-background-color: #DC143C;");
		 	j.setDisable(true);
		}
		
		if(pressedButton == k) {
			if(copyString.charAt(0) == 'k') {
				first.setText("K");
			}
			if(copyString.charAt(1) == 'k') {
				second.setText("K");
			}
			if(copyString.charAt(2) == 'k') {
				third.setText("K");
			}
			if(copyString.charAt(3) == 'k') {
				fourth.setText("K");
			}
			if(copyString.charAt(4) == 'k') {
				fifth.setText("K");
			}
			if(copyString.charAt(5) == 'k') {
				sixth.setText("K");
			}
			if(copyString.charAt(6) == 'k') {
				seventh.setText("K");
			}
			if(copyString.charAt(7) == 'k') {
				eigth.setText("K");
			}
			if (first.getText().equals(String.valueOf(copyString.charAt(0))) && second.getText().equals(String.valueOf(copyString.charAt(1))) && third.getText().equals(String.valueOf(copyString.charAt(2))) && fourth.getText().equals(String.valueOf(copyString.charAt(3))) && fifth.getText().equals(String.valueOf(copyString.charAt(4))) && sixth.getText().equals(String.valueOf(copyString.charAt(5))) && seventh.getText().equals(String.valueOf(copyString.charAt(6))) && eigth.getText().equals(String.valueOf(copyString.charAt(7)))) {
				Stage stage = (Stage) z.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(copyString.charAt(0) != 'k' && copyString.charAt(1) != 'k' && copyString.charAt(2) != 'k' && copyString.charAt(3) != 'k' && copyString.charAt(4) != 'k' && copyString.charAt(5) != 'k' && copyString.charAt(6) != 'k' && copyString.charAt(7) != 'k') {
				imgCount++;
				if(imgCount == 9) {
					first.setText(String.valueOf(copyString.charAt(0)));
					second.setText(String.valueOf(copyString.charAt(1)));
					third.setText(String.valueOf(copyString.charAt(2)));
					fourth.setText(String.valueOf(copyString.charAt(3)));
					fifth.setText(String.valueOf(copyString.charAt(4)));
					sixth.setText(String.valueOf(copyString.charAt(5)));
					seventh.setText(String.valueOf(copyString.charAt(6)));
					eigth.setText(String.valueOf(copyString.charAt(7)));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Stage stage = (Stage) k.getScene().getWindow();
					try {
					    Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
					    Scene scene = new Scene(root,1280,760);
						stage.setScene(scene);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				String tmpX = "stage"+ imgCount + ".png";
				File file = new File(tmpX);
				Image image = new Image(file.toURI().toString());
				imgContainer.setImage(image);
			}
			k.setStyle("-fx-background-color: #DC143C;");
		 	k.setDisable(true);
		}
		
		if(pressedButton == l) {
			if(copyString.charAt(0) == 'l') {
				first.setText("L");
			}
			if(copyString.charAt(1) == 'l') {
				second.setText("L");
			}
			if(copyString.charAt(2) == 'l') {
				third.setText("L");
			}
			if(copyString.charAt(3) == 'l') {
				fourth.setText("L");
			}
			if(copyString.charAt(4) == 'l') {
				fifth.setText("L");
			}
			if(copyString.charAt(5) == 'l') {
				sixth.setText("L");
			}
			if(copyString.charAt(6) == 'l') {
				seventh.setText("L");
			}
			if(copyString.charAt(7) == 'l') {
				eigth.setText("L");
			}
			if (first.getText().equals(String.valueOf(copyString.charAt(0))) && second.getText().equals(String.valueOf(copyString.charAt(1))) && third.getText().equals(String.valueOf(copyString.charAt(2))) && fourth.getText().equals(String.valueOf(copyString.charAt(3))) && fifth.getText().equals(String.valueOf(copyString.charAt(4))) && sixth.getText().equals(String.valueOf(copyString.charAt(5))) && seventh.getText().equals(String.valueOf(copyString.charAt(6))) && eigth.getText().equals(String.valueOf(copyString.charAt(7)))) {
				Stage stage = (Stage) z.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(copyString.charAt(0) != 'l' && copyString.charAt(1) != 'l' && copyString.charAt(2) != 'l' && copyString.charAt(3) != 'l' && copyString.charAt(4) != 'l' && copyString.charAt(5) != 'l' && copyString.charAt(6) != 'l' && copyString.charAt(7) != 'l') {
				imgCount++;
				if(imgCount == 9) {
					first.setText(String.valueOf(copyString.charAt(0)));
					second.setText(String.valueOf(copyString.charAt(1)));
					third.setText(String.valueOf(copyString.charAt(2)));
					fourth.setText(String.valueOf(copyString.charAt(3)));
					fifth.setText(String.valueOf(copyString.charAt(4)));
					sixth.setText(String.valueOf(copyString.charAt(5)));
					seventh.setText(String.valueOf(copyString.charAt(6)));
					eigth.setText(String.valueOf(copyString.charAt(7)));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Stage stage = (Stage) l.getScene().getWindow();
					try {
					    Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
					    Scene scene = new Scene(root,1280,760);
						stage.setScene(scene);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				String tmpX = "stage"+ imgCount + ".png";
				File file = new File(tmpX);
				Image image = new Image(file.toURI().toString());
				imgContainer.setImage(image);
			}
			l.setStyle("-fx-background-color: #DC143C;");
		 	l.setDisable(true);
		}
		
		if(pressedButton == m) {
			if(copyString.charAt(0) == 'm') {
				first.setText("M");
			}
			if(copyString.charAt(1) == 'm') {
				second.setText("M");
			}
			if(copyString.charAt(2) == 'm') {
				third.setText("M");
			}
			if(copyString.charAt(3) == 'm') {
				fourth.setText("M");
			}
			if(copyString.charAt(4) == 'm') {
				fifth.setText("M");
			}
			if(copyString.charAt(5) == 'm') {
				sixth.setText("M");
			}
			if(copyString.charAt(6) == 'm') {
				seventh.setText("M");
			}
			if(copyString.charAt(7) == 'm') {
				eigth.setText("M");
			}
			if (first.getText().equals(String.valueOf(copyString.charAt(0))) && second.getText().equals(String.valueOf(copyString.charAt(1))) && third.getText().equals(String.valueOf(copyString.charAt(2))) && fourth.getText().equals(String.valueOf(copyString.charAt(3))) && fifth.getText().equals(String.valueOf(copyString.charAt(4))) && sixth.getText().equals(String.valueOf(copyString.charAt(5))) && seventh.getText().equals(String.valueOf(copyString.charAt(6))) && eigth.getText().equals(String.valueOf(copyString.charAt(7)))) {
				Stage stage = (Stage) z.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(copyString.charAt(0) != 'm' && copyString.charAt(1) != 'm' && copyString.charAt(2) != 'm' && copyString.charAt(3) != 'm' && copyString.charAt(4) != 'm' && copyString.charAt(5) != 'm' && copyString.charAt(6) != 'm' && copyString.charAt(7) != 'm') {
				imgCount++;
				if(imgCount == 9) {
					first.setText(String.valueOf(copyString.charAt(0)));
					second.setText(String.valueOf(copyString.charAt(1)));
					third.setText(String.valueOf(copyString.charAt(2)));
					fourth.setText(String.valueOf(copyString.charAt(3)));
					fifth.setText(String.valueOf(copyString.charAt(4)));
					sixth.setText(String.valueOf(copyString.charAt(5)));
					seventh.setText(String.valueOf(copyString.charAt(6)));
					eigth.setText(String.valueOf(copyString.charAt(7)));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Stage stage = (Stage) m.getScene().getWindow();
					try {
					    Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
					    Scene scene = new Scene(root,1280,760);
						stage.setScene(scene);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				String tmpX = "stage"+ imgCount + ".png";
				File file = new File(tmpX);
				Image image = new Image(file.toURI().toString());
				imgContainer.setImage(image);
			}
			m.setStyle("-fx-background-color: #DC143C;");
		 	m.setDisable(true);
		}
		
		if(pressedButton == n) {
			if(copyString.charAt(0) == 'n') {
				first.setText("N");
			}
			if(copyString.charAt(1) == 'n') {
				second.setText("N");
			}
			if(copyString.charAt(2) == 'n') {
				third.setText("N");
			}
			if(copyString.charAt(3) == 'n') {
				fourth.setText("N");
			}
			if(copyString.charAt(4) == 'n') {
				fifth.setText("N");
			}
			if(copyString.charAt(5) == 'n') {
				sixth.setText("N");
			}
			if(copyString.charAt(6) == 'n') {
				seventh.setText("N");
			}
			if(copyString.charAt(7) == 'n') {
				eigth.setText("N");
			}
			if (first.getText().equals(String.valueOf(copyString.charAt(0))) && second.getText().equals(String.valueOf(copyString.charAt(1))) && third.getText().equals(String.valueOf(copyString.charAt(2))) && fourth.getText().equals(String.valueOf(copyString.charAt(3))) && fifth.getText().equals(String.valueOf(copyString.charAt(4))) && sixth.getText().equals(String.valueOf(copyString.charAt(5))) && seventh.getText().equals(String.valueOf(copyString.charAt(6))) && eigth.getText().equals(String.valueOf(copyString.charAt(7)))) {
				Stage stage = (Stage) z.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(copyString.charAt(0) != 'n' && copyString.charAt(1) != 'n' && copyString.charAt(2) != 'n' && copyString.charAt(3) != 'n' && copyString.charAt(4) != 'n' && copyString.charAt(5) != 'n' && copyString.charAt(6) != 'n' && copyString.charAt(7) != 'n') {
				imgCount++;
				if(imgCount == 9) {
					first.setText(String.valueOf(copyString.charAt(0)));
					second.setText(String.valueOf(copyString.charAt(1)));
					third.setText(String.valueOf(copyString.charAt(2)));
					fourth.setText(String.valueOf(copyString.charAt(3)));
					fifth.setText(String.valueOf(copyString.charAt(4)));
					sixth.setText(String.valueOf(copyString.charAt(5)));
					seventh.setText(String.valueOf(copyString.charAt(6)));
					eigth.setText(String.valueOf(copyString.charAt(7)));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Stage stage = (Stage) n.getScene().getWindow();
					try {
					    Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
					    Scene scene = new Scene(root,1280,760);
						stage.setScene(scene);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				String tmpX = "stage"+ imgCount + ".png";
				File file = new File(tmpX);
				Image image = new Image(file.toURI().toString());
				imgContainer.setImage(image);
			}
			n.setStyle("-fx-background-color: #DC143C;");
		 	n.setDisable(true);
		}
		
		if(pressedButton == o) {
			if(copyString.charAt(0) == 'o') {
				first.setText("O");
			}
			if(copyString.charAt(1) == 'o') {
				second.setText("O");
			}
			if(copyString.charAt(2) == 'o') {
				third.setText("O");
			}
			if(copyString.charAt(3) == 'o') {
				fourth.setText("O");
			}
			if(copyString.charAt(4) == 'o') {
				fifth.setText("O");
			}
			if(copyString.charAt(5) == 'o') {
				sixth.setText("O");
			}
			if(copyString.charAt(6) == 'o') {
				seventh.setText("O");
			}
			if(copyString.charAt(7) == 'o') {
				eigth.setText("O");
			}
			if (first.getText().equals(String.valueOf(copyString.charAt(0))) && second.getText().equals(String.valueOf(copyString.charAt(1))) && third.getText().equals(String.valueOf(copyString.charAt(2))) && fourth.getText().equals(String.valueOf(copyString.charAt(3))) && fifth.getText().equals(String.valueOf(copyString.charAt(4))) && sixth.getText().equals(String.valueOf(copyString.charAt(5))) && seventh.getText().equals(String.valueOf(copyString.charAt(6))) && eigth.getText().equals(String.valueOf(copyString.charAt(7)))) {
				Stage stage = (Stage) z.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(copyString.charAt(0) != 'o' && copyString.charAt(1) != 'o' && copyString.charAt(2) != 'o' && copyString.charAt(3) != 'o' && copyString.charAt(4) != 'o' && copyString.charAt(5) != 'o' && copyString.charAt(6) != 'o' && copyString.charAt(7) != 'o') {
				imgCount++;
				if(imgCount == 9) {
					first.setText(String.valueOf(copyString.charAt(0)));
					second.setText(String.valueOf(copyString.charAt(1)));
					third.setText(String.valueOf(copyString.charAt(2)));
					fourth.setText(String.valueOf(copyString.charAt(3)));
					fifth.setText(String.valueOf(copyString.charAt(4)));
					sixth.setText(String.valueOf(copyString.charAt(5)));
					seventh.setText(String.valueOf(copyString.charAt(6)));
					eigth.setText(String.valueOf(copyString.charAt(7)));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Stage stage = (Stage) o.getScene().getWindow();
					try {
					    Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
					    Scene scene = new Scene(root,1280,760);
						stage.setScene(scene);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				String tmpX = "stage"+ imgCount + ".png";
				File file = new File(tmpX);
				Image image = new Image(file.toURI().toString());
				imgContainer.setImage(image);
			}
			o.setStyle("-fx-background-color: #DC143C;");
		 	o.setDisable(true);
		}
		
		if(pressedButton == p) {
			if(copyString.charAt(0) == 'p') {
				first.setText("P");
			}
			if(copyString.charAt(1) == 'p') {
				second.setText("P");
			}
			if(copyString.charAt(2) == 'p') {
				third.setText("P");
			}
			if(copyString.charAt(3) == 'p') {
				fourth.setText("P");
			}
			if(copyString.charAt(4) == 'p') {
				fifth.setText("P");
			}
			if(copyString.charAt(5) == 'p') {
				sixth.setText("P");
			}
			if(copyString.charAt(6) == 'p') {
				seventh.setText("P");
			}
			if(copyString.charAt(7) == 'p') {
				eigth.setText("P");
			}
			if (first.getText().equals(String.valueOf(copyString.charAt(0))) && second.getText().equals(String.valueOf(copyString.charAt(1))) && third.getText().equals(String.valueOf(copyString.charAt(2))) && fourth.getText().equals(String.valueOf(copyString.charAt(3))) && fifth.getText().equals(String.valueOf(copyString.charAt(4))) && sixth.getText().equals(String.valueOf(copyString.charAt(5))) && seventh.getText().equals(String.valueOf(copyString.charAt(6))) && eigth.getText().equals(String.valueOf(copyString.charAt(7)))) {
				Stage stage = (Stage) z.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(copyString.charAt(0) != 'p' && copyString.charAt(1) != 'p' && copyString.charAt(2) != 'p' && copyString.charAt(3) != 'p' && copyString.charAt(4) != 'p' && copyString.charAt(5) != 'p' && copyString.charAt(6) != 'p' && copyString.charAt(7) != 'p') {
				imgCount++;
				if(imgCount == 9) {
					first.setText(String.valueOf(copyString.charAt(0)));
					second.setText(String.valueOf(copyString.charAt(1)));
					third.setText(String.valueOf(copyString.charAt(2)));
					fourth.setText(String.valueOf(copyString.charAt(3)));
					fifth.setText(String.valueOf(copyString.charAt(4)));
					sixth.setText(String.valueOf(copyString.charAt(5)));
					seventh.setText(String.valueOf(copyString.charAt(6)));
					eigth.setText(String.valueOf(copyString.charAt(7)));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Stage stage = (Stage) p.getScene().getWindow();
					try {
					    Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
					    Scene scene = new Scene(root,1280,760);
						stage.setScene(scene);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				String tmpX = "stage"+ imgCount + ".png";
				File file = new File(tmpX);
				Image image = new Image(file.toURI().toString());
				imgContainer.setImage(image);
			}
			p.setStyle("-fx-background-color: #DC143C;");
		 	p.setDisable(true);
		}
		
		if(pressedButton == q) {
			if(copyString.charAt(0) == 'q') {
				first.setText("Q");
			}
			if(copyString.charAt(1) == 'q') {
				second.setText("Q");
			}
			if(copyString.charAt(2) == 'q') {
				third.setText("Q");
			}
			if(copyString.charAt(3) == 'q') {
				fourth.setText("Q");
			}
			if(copyString.charAt(4) == 'q') {
				fifth.setText("Q");
			}
			if(copyString.charAt(5) == 'q') {
				sixth.setText("Q");
			}
			if(copyString.charAt(6) == 'q') {
				seventh.setText("Q");
			}
			if(copyString.charAt(7) == 'q') {
				eigth.setText("Q");
			}
			if (first.getText().equals(String.valueOf(copyString.charAt(0))) && second.getText().equals(String.valueOf(copyString.charAt(1))) && third.getText().equals(String.valueOf(copyString.charAt(2))) && fourth.getText().equals(String.valueOf(copyString.charAt(3))) && fifth.getText().equals(String.valueOf(copyString.charAt(4))) && sixth.getText().equals(String.valueOf(copyString.charAt(5))) && seventh.getText().equals(String.valueOf(copyString.charAt(6))) && eigth.getText().equals(String.valueOf(copyString.charAt(7)))) {
				Stage stage = (Stage) z.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(copyString.charAt(0) != 'q' && copyString.charAt(1) != 'q' && copyString.charAt(2) != 'q' && copyString.charAt(3) != 'q' && copyString.charAt(4) != 'q' && copyString.charAt(5) != 'q' && copyString.charAt(6) != 'q' && copyString.charAt(7) != 'q') {
				imgCount++;
				if(imgCount == 9) {
					first.setText(String.valueOf(copyString.charAt(0)));
					second.setText(String.valueOf(copyString.charAt(1)));
					third.setText(String.valueOf(copyString.charAt(2)));
					fourth.setText(String.valueOf(copyString.charAt(3)));
					fifth.setText(String.valueOf(copyString.charAt(4)));
					sixth.setText(String.valueOf(copyString.charAt(5)));
					seventh.setText(String.valueOf(copyString.charAt(6)));
					eigth.setText(String.valueOf(copyString.charAt(7)));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Stage stage = (Stage) q.getScene().getWindow();
					try {
					    Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
					    Scene scene = new Scene(root,1280,760);
						stage.setScene(scene);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				String tmpX = "stage"+ imgCount + ".png";
				File file = new File(tmpX);
				Image image = new Image(file.toURI().toString());
				imgContainer.setImage(image);
			}
			q.setStyle("-fx-background-color: #DC143C;");
		 	q.setDisable(true);
		}
		
		if(pressedButton == r) {
			if(copyString.charAt(0) == 'r') {
				first.setText("R");
			}
			if(copyString.charAt(1) == 'r') {
				second.setText("R");
			}
			if(copyString.charAt(2) == 'r') {
				third.setText("R");
			}
			if(copyString.charAt(3) == 'r') {
				fourth.setText("R");
			}
			if(copyString.charAt(4) == 'r') {
				fifth.setText("R");
			}
			if(copyString.charAt(5) == 'r') {
				sixth.setText("R");
			}
			if(copyString.charAt(6) == 'r') {
				seventh.setText("R");
			}
			if(copyString.charAt(7) == 'r') {
				eigth.setText("R");
			}
			if (first.getText().equals(String.valueOf(copyString.charAt(0))) && second.getText().equals(String.valueOf(copyString.charAt(1))) && third.getText().equals(String.valueOf(copyString.charAt(2))) && fourth.getText().equals(String.valueOf(copyString.charAt(3))) && fifth.getText().equals(String.valueOf(copyString.charAt(4))) && sixth.getText().equals(String.valueOf(copyString.charAt(5))) && seventh.getText().equals(String.valueOf(copyString.charAt(6))) && eigth.getText().equals(String.valueOf(copyString.charAt(7)))) {
				Stage stage = (Stage) z.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(copyString.charAt(0) != 'r' && copyString.charAt(1) != 'r' && copyString.charAt(2) != 'r' && copyString.charAt(3) != 'r' && copyString.charAt(4) != 'r' && copyString.charAt(5) != 'r' && copyString.charAt(6) != 'r' && copyString.charAt(7) != 'r') {
				imgCount++;
				if(imgCount == 9) {
					first.setText(String.valueOf(copyString.charAt(0)));
					second.setText(String.valueOf(copyString.charAt(1)));
					third.setText(String.valueOf(copyString.charAt(2)));
					fourth.setText(String.valueOf(copyString.charAt(3)));
					fifth.setText(String.valueOf(copyString.charAt(4)));
					sixth.setText(String.valueOf(copyString.charAt(5)));
					seventh.setText(String.valueOf(copyString.charAt(6)));
					eigth.setText(String.valueOf(copyString.charAt(7)));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Stage stage = (Stage) r.getScene().getWindow();
					try {
					    Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
					    Scene scene = new Scene(root,1280,760);
						stage.setScene(scene);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				String tmpX = "stage"+ imgCount + ".png";
				File file = new File(tmpX);
				Image image = new Image(file.toURI().toString());
				imgContainer.setImage(image);
			}
			r.setStyle("-fx-background-color: #DC143C;");
		 	r.setDisable(true);
		}
		
		if(pressedButton == s) {
			if(copyString.charAt(0) == 's') {
				first.setText("S");
			}
			if(copyString.charAt(1) == 's') {
				second.setText("S");
			}
			if(copyString.charAt(2) == 's') {
				third.setText("S");
			}
			if(copyString.charAt(3) == 's') {
				fourth.setText("S");
			}
			if(copyString.charAt(4) == 's') {
				fifth.setText("S");
			}
			if(copyString.charAt(5) == 's') {
				sixth.setText("S");
			}
			if(copyString.charAt(6) == 's') {
				seventh.setText("S");
			}
			if(copyString.charAt(7) == 's') {
				eigth.setText("S");
			}
			if (first.getText().equals(String.valueOf(copyString.charAt(0))) && second.getText().equals(String.valueOf(copyString.charAt(1))) && third.getText().equals(String.valueOf(copyString.charAt(2))) && fourth.getText().equals(String.valueOf(copyString.charAt(3))) && fifth.getText().equals(String.valueOf(copyString.charAt(4))) && sixth.getText().equals(String.valueOf(copyString.charAt(5))) && seventh.getText().equals(String.valueOf(copyString.charAt(6))) && eigth.getText().equals(String.valueOf(copyString.charAt(7)))) {
				Stage stage = (Stage) z.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(copyString.charAt(0) != 's' && copyString.charAt(1) != 's' && copyString.charAt(2) != 's' && copyString.charAt(3) != 's' && copyString.charAt(4) != 's' && copyString.charAt(5) != 's' && copyString.charAt(6) != 's' && copyString.charAt(7) != 's') {
				imgCount++;
				if(imgCount == 9) {
					first.setText(String.valueOf(copyString.charAt(0)));
					second.setText(String.valueOf(copyString.charAt(1)));
					third.setText(String.valueOf(copyString.charAt(2)));
					fourth.setText(String.valueOf(copyString.charAt(3)));
					fifth.setText(String.valueOf(copyString.charAt(4)));
					sixth.setText(String.valueOf(copyString.charAt(5)));
					seventh.setText(String.valueOf(copyString.charAt(6)));
					eigth.setText(String.valueOf(copyString.charAt(7)));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Stage stage = (Stage) s.getScene().getWindow();
					try {
					    Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
					    Scene scene = new Scene(root,1280,760);
						stage.setScene(scene);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				String tmpX = "stage"+ imgCount + ".png";
				File file = new File(tmpX);
				Image image = new Image(file.toURI().toString());
				imgContainer.setImage(image);
			}
			s.setStyle("-fx-background-color: #DC143C;");
		 	s.setDisable(true);
		}
		
		if(pressedButton == t) {
			if(copyString.charAt(0) == 't') {
				first.setText("T");
			}
			if(copyString.charAt(1) == 't') {
				second.setText("T");
			}
			if(copyString.charAt(2) == 't') {
				third.setText("T");
			}
			if(copyString.charAt(3) == 't') {
				fourth.setText("T");
			}
			if(copyString.charAt(4) == 't') {
				fifth.setText("T");
			}
			if(copyString.charAt(5) == 't') {
				sixth.setText("T");
			}
			if(copyString.charAt(6) == 't') {
				seventh.setText("T");
			}
			if(copyString.charAt(7) == 't') {
				eigth.setText("T");
			}
			if (first.getText().equals(String.valueOf(copyString.charAt(0))) && second.getText().equals(String.valueOf(copyString.charAt(1))) && third.getText().equals(String.valueOf(copyString.charAt(2))) && fourth.getText().equals(String.valueOf(copyString.charAt(3))) && fifth.getText().equals(String.valueOf(copyString.charAt(4))) && sixth.getText().equals(String.valueOf(copyString.charAt(5))) && seventh.getText().equals(String.valueOf(copyString.charAt(6))) && eigth.getText().equals(String.valueOf(copyString.charAt(7)))) {
				Stage stage = (Stage) z.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(copyString.charAt(0) != 't' && copyString.charAt(1) != 't' && copyString.charAt(2) != 't' && copyString.charAt(3) != 't' && copyString.charAt(4) != 't' && copyString.charAt(5) != 't' && copyString.charAt(6) != 't' && copyString.charAt(7) != 't') {
				imgCount++;
				if(imgCount == 9) {
					first.setText(String.valueOf(copyString.charAt(0)));
					second.setText(String.valueOf(copyString.charAt(1)));
					third.setText(String.valueOf(copyString.charAt(2)));
					fourth.setText(String.valueOf(copyString.charAt(3)));
					fifth.setText(String.valueOf(copyString.charAt(4)));
					sixth.setText(String.valueOf(copyString.charAt(5)));
					seventh.setText(String.valueOf(copyString.charAt(6)));
					eigth.setText(String.valueOf(copyString.charAt(7)));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Stage stage = (Stage) t.getScene().getWindow();
					try {
					    Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
					    Scene scene = new Scene(root,1280,760);
						stage.setScene(scene);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				String tmpX = "stage"+ imgCount + ".png";
				File file = new File(tmpX);
				Image image = new Image(file.toURI().toString());
				imgContainer.setImage(image);
			}
			t.setStyle("-fx-background-color: #DC143C;");
		 	t.setDisable(true);
		}
		
		if(pressedButton == u) {
			if(copyString.charAt(0) == 'u') {
				first.setText("U");
			}
			if(copyString.charAt(1) == 'u') {
				second.setText("U");
			}
			if(copyString.charAt(2) == 'u') {
				third.setText("U");
			}
			if(copyString.charAt(3) == 'u') {
				fourth.setText("U");
			}
			if(copyString.charAt(4) == 'u') {
				fifth.setText("U");
			}
			if(copyString.charAt(5) == 'u') {
				sixth.setText("U");
			}
			if(copyString.charAt(6) == 'u') {
				seventh.setText("U");
			}
			if(copyString.charAt(7) == 'u') {
				eigth.setText("U");
			}
			if (first.getText().equals(String.valueOf(copyString.charAt(0))) && second.getText().equals(String.valueOf(copyString.charAt(1))) && third.getText().equals(String.valueOf(copyString.charAt(2))) && fourth.getText().equals(String.valueOf(copyString.charAt(3))) && fifth.getText().equals(String.valueOf(copyString.charAt(4))) && sixth.getText().equals(String.valueOf(copyString.charAt(5))) && seventh.getText().equals(String.valueOf(copyString.charAt(6))) && eigth.getText().equals(String.valueOf(copyString.charAt(7)))) {
				Stage stage = (Stage) z.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(copyString.charAt(0) != 'u' && copyString.charAt(1) != 'u' && copyString.charAt(2) != 'u' && copyString.charAt(3) != 'u' && copyString.charAt(4) != 'u' && copyString.charAt(5) != 'u' && copyString.charAt(6) != 'u' && copyString.charAt(7) != 'u') {
				imgCount++;
				if(imgCount == 9) {
					first.setText(String.valueOf(copyString.charAt(0)));
					second.setText(String.valueOf(copyString.charAt(1)));
					third.setText(String.valueOf(copyString.charAt(2)));
					fourth.setText(String.valueOf(copyString.charAt(3)));
					fifth.setText(String.valueOf(copyString.charAt(4)));
					sixth.setText(String.valueOf(copyString.charAt(5)));
					seventh.setText(String.valueOf(copyString.charAt(6)));
					eigth.setText(String.valueOf(copyString.charAt(7)));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Stage stage = (Stage) u.getScene().getWindow();
					try {
					    Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
					    Scene scene = new Scene(root,1280,760);
						stage.setScene(scene);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				String tmpX = "stage"+ imgCount + ".png";
				File file = new File(tmpX);
				Image image = new Image(file.toURI().toString());
				imgContainer.setImage(image);
			}
			u.setStyle("-fx-background-color: #DC143C;");
		 	u.setDisable(true);
		}
		
		if(pressedButton == v) {
			if(copyString.charAt(0) == 'v') {
				first.setText("V");
			}
			if(copyString.charAt(1) == 'v') {
				second.setText("V");
			}
			if(copyString.charAt(2) == 'v') {
				third.setText("V");
			}
			if(copyString.charAt(3) == 'v') {
				fourth.setText("V");
			}
			if(copyString.charAt(4) == 'v') {
				fifth.setText("V");
			}
			if(copyString.charAt(5) == 'v') {
				sixth.setText("V");
			}
			if(copyString.charAt(6) == 'v') {
				seventh.setText("V");
			}
			if(copyString.charAt(7) == 'v') {
				eigth.setText("V");
			}
			if (first.getText().equals(String.valueOf(copyString.charAt(0))) && second.getText().equals(String.valueOf(copyString.charAt(1))) && third.getText().equals(String.valueOf(copyString.charAt(2))) && fourth.getText().equals(String.valueOf(copyString.charAt(3))) && fifth.getText().equals(String.valueOf(copyString.charAt(4))) && sixth.getText().equals(String.valueOf(copyString.charAt(5))) && seventh.getText().equals(String.valueOf(copyString.charAt(6))) && eigth.getText().equals(String.valueOf(copyString.charAt(7)))) {
				Stage stage = (Stage) z.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(copyString.charAt(0) != 'v' && copyString.charAt(1) != 'v' && copyString.charAt(2) != 'v' && copyString.charAt(3) != 'v' && copyString.charAt(4) != 'v' && copyString.charAt(5) != 'v' && copyString.charAt(6) != 'v' && copyString.charAt(7) != 'v') {
				imgCount++;
				if(imgCount == 9) {
					first.setText(String.valueOf(copyString.charAt(0)));
					second.setText(String.valueOf(copyString.charAt(1)));
					third.setText(String.valueOf(copyString.charAt(2)));
					fourth.setText(String.valueOf(copyString.charAt(3)));
					fifth.setText(String.valueOf(copyString.charAt(4)));
					sixth.setText(String.valueOf(copyString.charAt(5)));
					seventh.setText(String.valueOf(copyString.charAt(6)));
					eigth.setText(String.valueOf(copyString.charAt(7)));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Stage stage = (Stage) v.getScene().getWindow();
					try {
					    Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
					    Scene scene = new Scene(root,1280,760);
						stage.setScene(scene);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				String tmpX = "stage"+ imgCount + ".png";
				File file = new File(tmpX);
				Image image = new Image(file.toURI().toString());
				imgContainer.setImage(image);
			}
			v.setStyle("-fx-background-color: #DC143C;");
		 	v.setDisable(true);
		}
		
		if(pressedButton == w) {
			if(copyString.charAt(0) == 'w') {
				first.setText("W");
			}
			if(copyString.charAt(1) == 'w') {
				second.setText("W");
			}
			if(copyString.charAt(2) == 'w') {
				third.setText("W");
			}
			if(copyString.charAt(3) == 'w') {
				fourth.setText("W");
			}
			if(copyString.charAt(4) == 'w') {
				fifth.setText("W");
			}
			if(copyString.charAt(5) == 'w') {
				sixth.setText("W");
			}
			if(copyString.charAt(6) == 'w') {
				seventh.setText("W");
			}
			if(copyString.charAt(7) == 'w') {
				eigth.setText("W");
			}
			if (first.getText().equals(String.valueOf(copyString.charAt(0))) && second.getText().equals(String.valueOf(copyString.charAt(1))) && third.getText().equals(String.valueOf(copyString.charAt(2))) && fourth.getText().equals(String.valueOf(copyString.charAt(3))) && fifth.getText().equals(String.valueOf(copyString.charAt(4))) && sixth.getText().equals(String.valueOf(copyString.charAt(5))) && seventh.getText().equals(String.valueOf(copyString.charAt(6))) && eigth.getText().equals(String.valueOf(copyString.charAt(7)))) {
				Stage stage = (Stage) z.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(copyString.charAt(0) != 'w' && copyString.charAt(1) != 'w' && copyString.charAt(2) != 'w' && copyString.charAt(3) != 'w' && copyString.charAt(4) != 'w' && copyString.charAt(5) != 'w' && copyString.charAt(6) != 'w' && copyString.charAt(7) != 'w') {
				imgCount++;
				if(imgCount == 9) {
					first.setText(String.valueOf(copyString.charAt(0)));
					second.setText(String.valueOf(copyString.charAt(1)));
					third.setText(String.valueOf(copyString.charAt(2)));
					fourth.setText(String.valueOf(copyString.charAt(3)));
					fifth.setText(String.valueOf(copyString.charAt(4)));
					sixth.setText(String.valueOf(copyString.charAt(5)));
					seventh.setText(String.valueOf(copyString.charAt(6)));
					eigth.setText(String.valueOf(copyString.charAt(7)));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Stage stage = (Stage) w.getScene().getWindow();
					try {
					    Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
					    Scene scene = new Scene(root,1280,760);
						stage.setScene(scene);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				String tmpX = "stage"+ imgCount + ".png";
				File file = new File(tmpX);
				Image image = new Image(file.toURI().toString());
				imgContainer.setImage(image);
			}
			w.setStyle("-fx-background-color: #DC143C;");
		 	w.setDisable(true);
		}
		
		if(pressedButton == x) {
			if(copyString.charAt(0) == 'x') {
				first.setText("X");
			}
			if(copyString.charAt(1) == 'x') {
				second.setText("X");
			}
			if(copyString.charAt(2) == 'x') {
				third.setText("X");
			}
			if(copyString.charAt(3) == 'x') {
				fourth.setText("X");
			}
			if(copyString.charAt(4) == 'x') {
				fifth.setText("X");
			}
			if(copyString.charAt(5) == 'x') {
				sixth.setText("X");
			}
			if(copyString.charAt(6) == 'x') {
				seventh.setText("X");
			}
			if(copyString.charAt(7) == 'x') {
				eigth.setText("X");
			}
			if (first.getText().equals(String.valueOf(copyString.charAt(0))) && second.getText().equals(String.valueOf(copyString.charAt(1))) && third.getText().equals(String.valueOf(copyString.charAt(2))) && fourth.getText().equals(String.valueOf(copyString.charAt(3))) && fifth.getText().equals(String.valueOf(copyString.charAt(4))) && sixth.getText().equals(String.valueOf(copyString.charAt(5))) && seventh.getText().equals(String.valueOf(copyString.charAt(6))) && eigth.getText().equals(String.valueOf(copyString.charAt(7)))) {
				Stage stage = (Stage) z.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(copyString.charAt(0) != 'x' && copyString.charAt(1) != 'x' && copyString.charAt(2) != 'x' && copyString.charAt(3) != 'x' && copyString.charAt(4) != 'x' && copyString.charAt(5) != 'x' && copyString.charAt(6) != 'x' && copyString.charAt(7) != 'x') {
				imgCount++;
				if(imgCount == 9) {
					first.setText(String.valueOf(copyString.charAt(0)));
					second.setText(String.valueOf(copyString.charAt(1)));
					third.setText(String.valueOf(copyString.charAt(2)));
					fourth.setText(String.valueOf(copyString.charAt(3)));
					fifth.setText(String.valueOf(copyString.charAt(4)));
					sixth.setText(String.valueOf(copyString.charAt(5)));
					seventh.setText(String.valueOf(copyString.charAt(6)));
					eigth.setText(String.valueOf(copyString.charAt(7)));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Stage stage = (Stage) x.getScene().getWindow();
					try {
					    Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
					    Scene scene = new Scene(root,1280,760);
						stage.setScene(scene);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				String tmpX = "stage"+ imgCount + ".png";
				File file = new File(tmpX);
				Image image = new Image(file.toURI().toString());
				imgContainer.setImage(image);
			}
			x.setStyle("-fx-background-color: #DC143C;");
		 	x.setDisable(true);
		}
		
		if(pressedButton == y) {
			if(copyString.charAt(0) == 'y') {
				first.setText("Y");
			}
			if(copyString.charAt(1) == 'y') {
				second.setText("Y");
			}
			if(copyString.charAt(2) == 'y') {
				third.setText("Y");
			}
			if(copyString.charAt(3) == 'y') {
				fourth.setText("Y");
			}
			if(copyString.charAt(4) == 'y') {
				fifth.setText("Y");
			}
			if(copyString.charAt(5) == 'y') {
				sixth.setText("Y");
			}
			if(copyString.charAt(6) == 'y') {
				seventh.setText("Y");
			}
			if(copyString.charAt(7) == 'y') {
				eigth.setText("Y");
			}
			if (first.getText().equals(String.valueOf(copyString.charAt(0))) && second.getText().equals(String.valueOf(copyString.charAt(1))) && third.getText().equals(String.valueOf(copyString.charAt(2))) && fourth.getText().equals(String.valueOf(copyString.charAt(3))) && fifth.getText().equals(String.valueOf(copyString.charAt(4))) && sixth.getText().equals(String.valueOf(copyString.charAt(5))) && seventh.getText().equals(String.valueOf(copyString.charAt(6))) && eigth.getText().equals(String.valueOf(copyString.charAt(7)))) {
				Stage stage = (Stage) z.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(copyString.charAt(0) != 'y' && copyString.charAt(1) != 'y' && copyString.charAt(2) != 'y' && copyString.charAt(3) != 'y' && copyString.charAt(4) != 'y' && copyString.charAt(5) != 'y' && copyString.charAt(6) != 'y' && copyString.charAt(7) != 'y') {
				imgCount++;
				if(imgCount == 9) {
					first.setText(String.valueOf(copyString.charAt(0)));
					second.setText(String.valueOf(copyString.charAt(1)));
					third.setText(String.valueOf(copyString.charAt(2)));
					fourth.setText(String.valueOf(copyString.charAt(3)));
					fifth.setText(String.valueOf(copyString.charAt(4)));
					sixth.setText(String.valueOf(copyString.charAt(5)));
					seventh.setText(String.valueOf(copyString.charAt(6)));
					eigth.setText(String.valueOf(copyString.charAt(7)));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Stage stage = (Stage) y.getScene().getWindow();
					try {
					    Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
					    Scene scene = new Scene(root,1280,760);
						stage.setScene(scene);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				String tmpX = "stage"+ imgCount + ".png";
				File file = new File(tmpX);
				Image image = new Image(file.toURI().toString());
				imgContainer.setImage(image);
			}
			y.setStyle("-fx-background-color: #DC143C;");
		 	y.setDisable(true);
		}
		
		if(pressedButton == z) {
			if(copyString.charAt(0) == 'z') {
				first.setText("Z");
			}
			if(copyString.charAt(1) == 'z') {
				second.setText("Z");
			}
			if(copyString.charAt(2) == 'z') {
				third.setText("Z");
			}
			if(copyString.charAt(3) == 'z') {
				fourth.setText("Z");
			}
			if(copyString.charAt(4) == 'z') {
				fifth.setText("Z");
			}
			if(copyString.charAt(5) == 'z') {
				sixth.setText("Z");
			}
			if(copyString.charAt(6) == 'z') {
				seventh.setText("Z");
			}
			if(copyString.charAt(7) == 'z') {
				eigth.setText("Z");
			}
			if (first.getText().equals(String.valueOf(copyString.charAt(0))) && second.getText().equals(String.valueOf(copyString.charAt(1))) && third.getText().equals(String.valueOf(copyString.charAt(2))) && fourth.getText().equals(String.valueOf(copyString.charAt(3))) && fifth.getText().equals(String.valueOf(copyString.charAt(4))) && sixth.getText().equals(String.valueOf(copyString.charAt(5))) && seventh.getText().equals(String.valueOf(copyString.charAt(6))) && eigth.getText().equals(String.valueOf(copyString.charAt(7)))) {
				Stage stage = (Stage) z.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					Scene scene = new Scene(root,1280,760);
					stage.setScene(scene);
					stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(copyString.charAt(0) != 'z' && copyString.charAt(1) != 'z' && copyString.charAt(2) != 'z' && copyString.charAt(3) != 'z' && copyString.charAt(4) != 'z' && copyString.charAt(5) != 'z' && copyString.charAt(6) != 'z' && copyString.charAt(7) != 'z') {
				imgCount++;
				if(imgCount == 9) {
					first.setText(String.valueOf(copyString.charAt(0)));
					second.setText(String.valueOf(copyString.charAt(1)));
					third.setText(String.valueOf(copyString.charAt(2)));
					fourth.setText(String.valueOf(copyString.charAt(3)));
					fifth.setText(String.valueOf(copyString.charAt(4)));
					sixth.setText(String.valueOf(copyString.charAt(5)));
					seventh.setText(String.valueOf(copyString.charAt(6)));
					eigth.setText(String.valueOf(copyString.charAt(7)));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Stage stage = (Stage) z.getScene().getWindow();
					try {
					    Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
					    Scene scene = new Scene(root,1280,760);
						stage.setScene(scene);
						stage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				String tmpX = "stage"+ imgCount + ".png";
				File file = new File(tmpX);
				Image image = new Image(file.toURI().toString());
				imgContainer.setImage(image);
			}
			z.setStyle("-fx-background-color: #DC143C;");
		 	z.setDisable(true);
		}
	}

}
