package application;
	
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
public class Main extends Application{
	public static String soundFile = "Game_BackGround.mp3";
	public static Media sound = new Media(new File (soundFile).toURI().toString());
	public static MediaPlayer mediaPlayer = new MediaPlayer(sound);
//	mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	
	private static Stage primaryStage;
	private static void setPrimaryStage(Stage stage) {
		Main.primaryStage = stage;
	}
	public static Stage getPrimaryStage() {
		return Main.primaryStage;
	}
	
	@Override
	public void start(Stage primaryStage) {
		 try {
			 	setPrimaryStage(primaryStage);
        		Parent root = FXMLLoader.load(getClass().getResource("go.fxml"));
        		primaryStage.setTitle("Stealth");
        		primaryStage.setScene(new Scene(root, 1024, 768));
	            primaryStage.setMaxHeight(900);
	            primaryStage.setMaxWidth(1440);
	            primaryStage.setResizable(false);
	            primaryStage.show();
	            
				mediaPlayer.play();
	        } catch (IOException e) {
	            e.printStackTrace(System.err);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
