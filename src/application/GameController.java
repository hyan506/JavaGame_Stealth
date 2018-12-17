package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.animation.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.event.*;
import javafx.util.Duration;
public class GameController implements Initializable{
	@FXML private AnchorPane game_Pane;
	@FXML private Label Score;
	@FXML private Label KeyLabel;
	@FXML private Label PowerLabel;
	@FXML private Label NameLabel;
	@FXML private Label firstCountLabel,secondCountLabel;
	@FXML private Label LifeLabel;
	@FXML private Button PauseStart;
	ArrayList<String> PowerName = new ArrayList<String>();
	@FXML private Canvas canvas;
	boolean mark = false;
	boolean paused = true;
	boolean countDownCompleted = false;
	boolean start = false;
	private Thief ben = new Thief("Ben");
	public int timeCountDown = 120;
	public int threeSecondCountDown = 3;
	Xmark X = new Xmark();
	ArrayList<Fireball> Fires = new ArrayList<Fireball>();
	private int Fnumber = 0;
	ArrayList<Icewall> Ices = new ArrayList<Icewall>();
	private int Inumber = 0;



	
	@FXML public void PauseAndStart() {
		if(paused) {
			PauseStart.setText("Pause");
			paused = false;
		}
		else {
			PauseStart.setText("Start");
			paused = true;
		}
	}
	ArrayList<String> PressedKeys = new ArrayList<String>();
	public void closeApp() {
		Stage s = Main.getPrimaryStage();
		s.close();
	}

	@FXML public void GamePressed(KeyEvent e)
	{
		String key = e.getCode().toString();
		if ( PressedKeys.contains(key) == false ) {			
			PressedKeys.add(key);
		}
		if(key =="X") {					//Press Escape to exit the game
			if(!mark) {
				X.setpos(ben.getHpos(), ben.getVpos());
				mark = true;
			}
			else {
				ben.setpos(X.getHpos(), X.getVpos());
				ben.setHspeed(0);
				ben.setVspeed(0);
				mark = false;
				X.setpos(1100,800);
			}
		}
		if(key =="P") {					//Press P to pause the game
			PauseAndStart();
		}
		if(key =="Y") {
			timeCountDown = 0;
		} 

		//		System.out.println(key);
	}

	@FXML public void GameReleased(KeyEvent e)
	{
		String key = e.getCode().toString();
		if(key =="ESCAPE") {					//Press Escape to exit the game
			closeApp();
		}
		if(key =="Z") {					//Press Escape to exit the game
			if(PowerLabel.getText() == "FIREBALL") {

				if(Math.abs(ben.getHspeed())>=Math.abs(ben.getVspeed())	) {
					if(ben.getHspeed()>=0) {
						Fires.get(Fnumber).CastH(ben.getHpos() + 50,ben.getVpos() + 10,true);
						Fnumber++;
					}
					else{
						Fires.get(Fnumber).CastH(ben.getHpos() - 50,ben.getVpos() +10,false);
						Fnumber++;
					}
				}
				else {
					if(ben.getVspeed()>=0) {
						Fires.get(Fnumber).CastV(ben.getHpos(),ben.getVpos() + 50,true);
						Fnumber++;
					}
					else{
						Fires.get(Fnumber).CastV(ben.getHpos(),ben.getVpos() - 50,false);
						Fnumber++;
					}
				}
			}
			if(PowerLabel.getText() == "ICEWALL") {
				
				if(Math.abs(ben.getHspeed())>=Math.abs(ben.getVspeed())	) {
					if(ben.getHspeed()>=0) {
						Ices.get(Inumber).setpos(ben.getHpos() + 50,ben.getVpos() + 10);
						Inumber++;
					}
					else{
						Ices.get(Inumber).setpos(ben.getHpos() - 50,ben.getVpos() +10);
						Inumber++;
					}
				}
				else {
					if(ben.getVspeed()>=0) {
						Ices.get(Inumber).setpos(ben.getHpos(),ben.getVpos() + 50);
						Inumber++;
					}
					else{
						Ices.get(Inumber).setpos(ben.getHpos(),ben.getVpos() - 50);
						Inumber++;
					}
				}
			}
			PowerLabel.setText("NONE");
		}


		PressedKeys.remove(key);
	}



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		User user = User.getInstance();
		try {
			NameLabel.setText(user.getName());

		}catch(NullPointerException e){
			System.out.println("Bad");
		}
		user.setLife(5);

		Score.setText("0");
		KeyLabel.setText("0");
		game_Pane.requestFocus();
		for(int i = 0; i<5; i++) {
			Fireball F = new Fireball();
			Fires.add(F);
		}
		for(int i = 0; i<5; i++) {
			Icewall I = new Icewall();
			Ices.add(I);
		}

		///////////            Map             /////////////////
		ArrayList<wall> walls = new ArrayList<wall>();
		//Walls at the TOP
		for (int i = 0; i < 102; i++)
		{
			double x = i*10 ;
			double y = 0 ; 
			wall W = new wall(x,y);
			walls.add(W);
		}
		//Walls at the Bottom
		for (int i = 0; i < 102; i++)
		{
			double x = i*10 ;
			double y = 640 ; 
			wall W = new wall(x,y);
			walls.add(W);
		}
		//Walls at the left
		for (int i = 0; i < 64; i++)
		{
			double x = 0 ;
			double y = i*10 ; 
			wall W = new wall(x,y);
			walls.add(W);
		}
		//Walls at the right
		for (int i = 0; i < 64; i++)
		{
			double x = 1010 ;
			double y = i*10 ; 
			wall W = new wall(x,y);
			walls.add(W);
		}

		for (int i = 20; i < 40; i++)
		{
			double x = i*10 ;
			double y = 450 ; 
			wall W = new wall(x,y);
			walls.add(W);
		}
		for (int i = 20; i < 40; i++)
		{
			double x = i*10 ;
			double y = 160 ; 
			wall W = new wall(x,y);
			walls.add(W);
		}
		for (int i = 60; i < 80; i++)
		{
			double x = i*10 ;
			double y = 450 ; 
			wall W = new wall(x,y);
			walls.add(W);
		}
		for (int i = 60; i < 80; i++)
		{
			double x = i*10 ;
			double y = 160 ; 
			wall W = new wall(x,y);
			walls.add(W);
		}
		for (int i = 25; i < 35; i++)
		{
			double x = 300 ;
			double y = i*10 ; 
			wall W = new wall(x,y);
			walls.add(W);
		}
		for (int i = 25; i < 35; i++)
		{
			double x = 700 ;
			double y = i*10 ; 
			wall W = new wall(x,y);
			walls.add(W);
		}
		for (int i = -5; i < 6; i++)
		{
			for(int j = -5; j<6;j++) {
				double x = 500 + i * 10;
				double y = 300 + j * 10; 
				wall W = new wall(x,y);
				walls.add(W);
			}
		}
		wall W = new wall(500,300);
		walls.add(W);

		//Generate Guards
		ArrayList<Guard> Guards = new ArrayList<Guard>();
		makeHGuards:
			while (Guards.size()<4)
			{
				Guard G = new Guard(980 * Math.random(),550 * Math.random(),true);


				for(int q = 0; q<walls.size();q++) {
					if(G.getBoundary().intersects(walls.get(q).getBoundary())){
						continue makeHGuards;
					}
				}	
				for(int q = 0; q<Guards.size();q++) {
					if(G.getBoundary().intersects(Guards.get(q).getBoundary())){
						continue makeHGuards;
					}
				}
				if(G.Touch(ben)){
					continue makeHGuards;
				}
				Guards.add(G);
			}
		makeVGuards:
			while (Guards.size()<10)
			{
				Guard G = new Guard(980 * Math.random(),550 * Math.random(), false);

				for(int q = 0; q<walls.size();q++) {
					if(G.getBoundary().intersects(walls.get(q).getBoundary())){
						continue makeVGuards;
					}
				}	
				for(int q = 0; q<Guards.size();q++) {
					if(G.getBoundary().intersects(Guards.get(q).getBoundary())){
						continue makeVGuards;
					}
				}
				if(G.Touch(ben)) {
					continue makeVGuards;
				}
				Guards.add(G);
			}
			//Generate Keys
			ArrayList<Key> Keys = new ArrayList<Key>();
			int keyNumber = 0;
			makeKeys:
				while (Keys.size()<=3)
				{
					Key K = new Key(980 * Math.random(),550 * Math.random());
					for(int q = 0; q<walls.size();q++) {
						if(K.getBoundary().intersects(walls.get(q).getBoundary())){
							continue makeKeys;
						}
					}	
					for(int q = 0; q<Keys.size();q++) {
						if(K.getBoundary().intersects(Keys.get(q).getBoundary())){
							continue makeKeys;
						}
					}
					K.setNumber(keyNumber++);
					Keys.add(K);
				}
			//Generate Coins
			ArrayList<Coin> Coins = new ArrayList<Coin>();
			makeCoins:
				while (Coins.size()<=80)
				{
					Coin C = new Coin(980 * Math.random(),550 * Math.random());
					for(int q = 0; q<walls.size();q++) {
						if(C.getBoundary().intersects(walls.get(q).getBoundary())){
							continue makeCoins;
						}
					}
					for(int q = 0; q<Keys.size();q++) {
						if(C.getBoundary().intersects(Keys.get(q).getBoundary())){
							continue makeCoins;
						}
					}
					for(int q = 0; q<Coins.size();q++) {
						if(C.getBoundary().intersects(Coins.get(q).getBoundary())){
							continue makeCoins;
						}
					}
					Coins.add(C);
				}

			//Generate Doors
			ArrayList<Door> Doors = new ArrayList<Door>();
			makeDoors:
				while (Doors.size()<=1)
				{

					Door D = new Door(980 * Math.random(),550 * Math.random());

					for(int q = 0; q<walls.size();q++) {
						if(D.getBoundary().intersects(walls.get(q).getBoundary())){
							continue makeDoors;
						}
					}
					for(int q = 0; q<Keys.size();q++) {
						if(D.getBoundary().intersects(Keys.get(q).getBoundary())){
							continue makeDoors;
						}
					}
					for(int q = 0; q<Coins.size();q++) {
						if(D.getBoundary().intersects(Coins.get(q).getBoundary())){
							continue makeDoors;
						}
					}
					Doors.add(D);
				}
			//Generate power 
			ArrayList<Power> Powers = new ArrayList<Power>();
			makePowers:
				while (Powers.size()<5)
				{
					double x = 980 * Math.random();
					double y = 550 * Math.random(); 
					Power P = new Power();
					P.setImage("P.png");         
					P.setpos(x,y);

					for(int q = 0; q<walls.size();q++) {
						if(P.getBoundary().intersects(walls.get(q).getBoundary())){
							continue makePowers;
						}
					}
					for(int q = 0; q<Keys.size();q++) {
						if(P.getBoundary().intersects(Keys.get(q).getBoundary())){
							continue makePowers;
						}
					}
					for(int q = 0; q<Coins.size();q++) {
						if(P.getBoundary().intersects(Coins.get(q).getBoundary())){
							continue makePowers;
						}
					}
					for(int q = 0; q<Doors.size();q++) {
						if(P.getBoundary().intersects(Doors.get(q).getBoundary())){
							continue makePowers;
						}
					}
					for(int q = 0; q<Powers.size();q++) {
						if(P.getBoundary().intersects(Powers.get(q).getBoundary())){
							continue makePowers;
						}
					}
					Powers.add(P);
				}
			//CountDown Timer->
			Timeline threeSeconds = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					firstCountLabel.setText(Integer.toString(threeSecondCountDown));
					if(threeSecondCountDown > 0)
					{
						threeSecondCountDown--;
						countDownCompleted = false;
					}
					else {
						firstCountLabel.setVisible(false);
						countDownCompleted = true;

					}	
				}
			}));
			threeSeconds.setCycleCount(Timeline.INDEFINITE);
			threeSeconds.playFromStart();
			Timeline SecondsTimer = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					secondCountLabel.setText(Integer.toString(timeCountDown));
					if(timeCountDown > 0 && !paused)
					{

						timeCountDown--;
					}
				}
			}));
			SecondsTimer.setCycleCount(Timeline.INDEFINITE);
			SecondsTimer.play();
			//CountDOwn Timer<-
			PowerLabel.setText("NONE");
			//Initiate the Animation Timer and Graphic Context
			LongV lastNanoTime = new LongV( System.nanoTime() );
			GraphicsContext gc = canvas.getGraphicsContext2D();

			new AnimationTimer()
			{

				public void handle(long currentNanoTime)
				{

					double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
					lastNanoTime.value = currentNanoTime;
					PowerName.addAll(Arrays.asList("FIREBALL", "ICEWALL", "LIGHTING"));
					LifeLabel.setText(Integer.toString(user.getLife()));
					if(countDownCompleted && !start) {
						start = true;
						PauseStart.setVisible(true);
						PauseAndStart();
					}

					if(!paused) {
						if(timeCountDown==0) {
							Stage score = Main.getPrimaryStage();
							Parent layout;
							try {
								layout = FXMLLoader.load(getClass().getResource("Die.fxml"));
								score.setTitle("You Died");
								score.setScene(new Scene(layout, 1024, 768));
								this.stop();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						//Arrow Keys will be handled here
						if (PressedKeys.contains("LEFT")) {

							ben.setImage("Left.gif");
							if(!ben.isTouchLeft()) {
								ben.addHspeed(-10);
							}
						}
						if (PressedKeys.contains("RIGHT")) {
							ben.setImage("Right.gif");
							if(!ben.isTouchRight()) {
								ben.addHspeed(10);
							}
						}
						if (PressedKeys.contains("UP")) {
							if(!ben.isTouchUp()) {
								ben.addVspeed(-10);
							}
						}
						if (PressedKeys.contains("DOWN")) {
							if(!ben.isTouchDown()) {
								ben.addVspeed(10);
							}
						}


						//Thief Collision with the Wall
						ben.setTouchDown(false);
						ben.setTouchUp(false);
						ben.setTouchLeft(false);
						ben.setTouchRight(false);
						Thief ghost = new Thief("ghost");
						ghost.setImage("Right.gif");
						ghost.setpos(ben.getHpos(), ben.getVpos());
						ghost.setHspeed(ben.getHspeed());
						ghost.setVspeed(ben.getVspeed());
						ghost.updatepos(elapsedTime);
						for(int i = 0; i<walls.size(); i++){

							wall W = walls.get(i);
							if ( ghost.Touch(W) )
							{
								if(ghost.getHpos()>=W.getHpos()-ghost.getWidth()&& ghost.getHpos()<W.getHpos()) { 
									ben.setTouchRight(true);
								}
								if(ghost.getHpos()<=W.getHpos()+W.getWidth() && ghost.getHpos()+ghost.getWidth()>W.getHpos()+W.getWidth() ) {
									ben.setTouchLeft(true);
								}
								if(ghost.getVpos()<=W.getVpos()+W.getHeight() && ghost.getVpos()+ghost.height<W.getVpos()+W.getHeight()) {
									ben.setTouchUp(true);
								}
								if(ghost.getVpos()+ghost.getHeight()>=W.getVpos() && ghost.getVpos()>W.getVpos()) {
									ben.setTouchDown(true);
								}
							}
							for(int j = 0; j< Fires.size();j++) {
								if(Fires.get(j).Touch(W)) {
									Fires.get(j).setpos(1100,800);
								}
							}
						}	

						// Guards collision with walls
						for(int j = 0; j<Guards.size(); j++){

							Guard G = Guards.get(j);
							Guard G_Ghost = new Guard(G.getHpos(),G.getVpos(),true);
							G_Ghost.setHspeed(G.getHspeed());
							G_Ghost.setVspeed(G.getVspeed());
							G_Ghost.updatepos(elapsedTime);
							G.setTouchDown(false);
							G.setTouchUp(false);
							G.setTouchLeft(false);
							G.setTouchRight(false);
							for ( int i = 0 ; i<walls.size(); i++ ){
								wall W = walls.get(i);
								if ( G_Ghost.Touch(W) ) {
									if(G_Ghost.getHpos()>=W.getHpos()-G_Ghost.getWidth()&& G_Ghost.getHpos()<W.getHpos()) {
										G.setTouchRight(true);
									}
									if(G_Ghost.getHpos()<=W.getHpos()+W.getWidth() && G_Ghost.getHpos()+G_Ghost.getWidth()>W.getHpos()+W.getWidth() ) {
										G.setTouchLeft(true);
									}
									if(G_Ghost.getVpos()<=W.getVpos()+W.getHeight() && G_Ghost.getVpos()+G_Ghost.height<W.getVpos()+W.getHeight()) { 
										G.setTouchUp(true);
									}
									if(G_Ghost.getVpos()+G_Ghost.getHeight()>=W.getVpos() && G_Ghost.getVpos()>W.getVpos()) {
										G.setTouchDown(true);
									}
								}	
							}

							G.updatepos(elapsedTime);
							if(G.Touch(ben)) {
								
								String StopFile = "stop.mp3";
								Media stopSound = new Media(new File (StopFile).toURI().toString());
								MediaPlayer stopPlayer = new MediaPlayer(stopSound);
								stopPlayer.play();
								if(user.getLife()>1) {
									user.setLife(user.getLife() - 1);
									ben.setpos(50, 50);
									ben.setHspeed(0);
									ben.setVspeed(0);
								}
								else {
									Stage score = Main.getPrimaryStage();
									Parent layout;
									try {
										layout = FXMLLoader.load(getClass().getResource("Die.fxml"));
										score.setTitle("You Died");
										score.setScene(new Scene(layout, 1024, 768));
										this.stop();
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							}
							//fireball and guards
							for(int i = 0; i<Fires.size(); i++){
								if(G.Touch(Fires.get(i))) {
									Guards.remove(j);
									Fires.get(i).setpos(1100,800);
								}
							}
							//Icewall and guards
							for(int i = 0; i<Ices.size(); i++){
								if(G.Touch(Ices.get(i))) {
									G.setHspeed(0);
									G.setVspeed(0);
								}
							}
						}	

						//Thief Collision with Coins
						for(int i = 0; i<Coins.size(); i++) {

							Coin Coin = Coins.get(i);
							if ( ben.Touch(Coin) )
							{
								String coinFile = "PickedCoin.mp3";
								Media coinSound = new Media(new File (coinFile).toURI().toString());
								MediaPlayer coinPlayer = new MediaPlayer(coinSound);
								coinPlayer.play();
								Coins.remove(i);
								user.setScore(user.getScore()+Coin.getPoints());
							}
						}
						Score.setText(Integer.toString(user.getScore()));
						//Thief Collision with Keys
						for(int i = 0; i<Keys.size(); i++){
							Key K = Keys.get(i);
							if ( ben.Touch(K) )
							{
								ben.pickKey(K);
								Keys.remove(i);
								KeyLabel.setText(Integer.toString(Integer.parseInt((KeyLabel.getText()))+1));
							}
						}
						//Thief Collision with Doors
						for(int i = 0; i<Doors.size(); i++){
							Door D = Doors.get(i);
							if ( ben.Touch(D) )
							{
								if(ben.hasKey(D.getDoorNumber()) && PressedKeys.contains("C")){
									try {
										Stage finish = Main.getPrimaryStage();
										Parent layout = FXMLLoader.load(getClass().getResource("finish.fxml"));
										finish.setTitle("!!Finish!!");
										finish.setScene(new Scene(layout, 1024, 768));
										this.stop();
									} catch (IOException e) {
										e.printStackTrace(System.err);
									}
								}

							}
						}
						//Thief Collision with Powers
						for(int i = 0; i<Powers.size(); i++){
							Power P = Powers.get(i);
							if ( ben.Touch(P) )
							{
								Powers.remove(i);
								PowerLabel.setText(PowerName.get((int)(Math.random()*2)));
							}
						}

						ben.updatepos(elapsedTime);
						for(int i = 0; i< Fires.size();i++)
							Fires.get(i).updatepos(elapsedTime);
					}
					//Render -- Show everything up on the canvas
					gc.clearRect(0, 0, 1024,768);
					X.render(gc);	 	 
					for (Coin Coin : Coins )
						Coin.render(gc);
					for (Door Door : Doors )
						Door.render(gc);
					for (Power P : Powers )
						P.render( gc );
					for (Fireball F : Fires )
						F.render( gc );
					for (Icewall I : Ices )
						I.render( gc );
					for (wall wall : walls )
						wall.render(gc);
					for (Key K : Keys )
						K.render( gc );
					for (Guard G : Guards )
						G.render( gc );
					ben.render(gc);


				}
			}.start();



	}
}
