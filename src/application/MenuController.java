package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
public class MenuController implements Initializable{
	@FXML private AnchorPane Menu_Pane;
	@FXML private VBox login;
	@FXML private VBox mainmenu;
	@FXML private VBox helpmenu;
	@FXML private VBox helpmenu_2;
	@FXML private Button confirm;
	@FXML private Button start;
	@FXML private Button help;
	@FXML private Button back;
	@FXML private Button next;
	@FXML private Button previous;
	@FXML private Button exit;
	@FXML private TextField NameField;
	@FXML private Label Title;

	@FXML public void closeApp() {
		Stage s = Main.getPrimaryStage();
		s.close();
	}
	//helpCLick () is for the help button pressed response
	@FXML public void helpClick() {
		mainmenu.setVisible(false);
		Title.setVisible(false);
		helpmenu.setVisible(true);
		Stage s = Main.getPrimaryStage();
		s.setTitle("Stealth - Help");
	}
	
	@FXML public void backClick() {
		mainmenu.setVisible(true);
		Title.setVisible(true);
		helpmenu.setVisible(false);
		helpmenu_2.setVisible(false);
		Stage s = Main.getPrimaryStage();
		s.setTitle("Stealth");
	}
	@FXML public void previousClick() {
		mainmenu.setVisible(false);
		Title.setVisible(false);
		helpmenu.setVisible(true);
		helpmenu_2.setVisible(false);
		Stage s = Main.getPrimaryStage();
		s.setTitle("Stealth - Help");
	}
	@FXML public void nextClick() {
		mainmenu.setVisible(false);
		Title.setVisible(false);
		helpmenu.setVisible(false);
		helpmenu_2.setVisible(true);
		Stage s = Main.getPrimaryStage();
		s.setTitle("Stealth - Help");
	}
	
	@FXML public void confirmClick() {
		try {
			User user = User.getInstance();
			user.setName(NameField.getText());
		}
		catch(NullPointerException e){
			e.printStackTrace(System.err);
		}
		try {
			Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));
			Stage s = Main.getPrimaryStage();
			s.setTitle("Stealth - game");
			s.setScene(new Scene(root, 1024, 768));
			Main.mediaPlayer.stop();
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}

	}
	@FXML public void multiClick() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("multiplayer.fxml"));
			Stage s = Main.getPrimaryStage();
			s.setTitle("Stealth - game");
			s.setScene(new Scene(root, 1024, 768));
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}
	@FXML public void startClick() {
		login.setVisible(true);
		mainmenu.setVisible(false);
		Stage s = Main.getPrimaryStage();
		s.setTitle("Stealth - Enter Your Name");
	}
	@FXML public void Pressed(KeyEvent e)
	{
		String key = e.getCode().toString();
		Stage s = Main.getPrimaryStage();
		if ( key == "ESCAPE" ) {
			s.close();
		}
			
	}
	
	
	


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//System.out.println("View is now loaded!");
	}
}
