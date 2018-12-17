package application;

public class Coin extends Dots{
	private int points = 10;
	public Coin(double x, double y) {
		this.setImage("coin.PNG");         
		this.setpos(x,y);
	}
	public Coin(int points) {
		this.points = points;
	}
	

	public int getPoints() {
		return points;
	}
	
	
}
