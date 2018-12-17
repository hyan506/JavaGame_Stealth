package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
public class FinishController implements Initializable{
	@FXML private Button toMenu;
	@FXML private Label Score;
	@FXML private Label Escaped;
	@FXML private Label Died;

	@FXML public void closeFinished() {
		Stage s = Main.getPrimaryStage();
		s.close();
	}
	@FXML public void backToMain() {
		try {
			Stage score = Main.getPrimaryStage();
			Parent layout = FXMLLoader.load(getClass().getResource("go.fxml"));
			score.setTitle("Stealth");
			score.setScene(new Scene(layout, 1024, 768));
			
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}
	//helpCLick () is for the help button pressed response
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//System.out.println("View is now loaded!");
		User user = User.getInstance();
		Score.setText(Integer.toString(user.getScore()));
		String soundFile = "BackGround.mp3";
		Media sound = new Media(new File (soundFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
//		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
	}
}
