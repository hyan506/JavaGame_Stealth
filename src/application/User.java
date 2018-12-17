package application;

public class User {
	private static User _user = null;
	private String name = "";
	private int highestScore = 0;
	private int score =0;
	private int life;
	
	
	private User() {
		this.name = "Mr.NoName";
		highestScore = 0;
		score = 0;
	}
	 public static User getInstance() {
	      if(_user == null) {
	    	  _user = new User();
	      }
	      return _user;
	   }
	 
	 
	 
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHighestScore() {
		return highestScore;
	}
	public void setHighestScore(int highestScore) {
		this.highestScore = highestScore;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
	
	
	
	
}
