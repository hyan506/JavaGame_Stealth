package application;

public class Key extends Dots{
	private int number;
	public Key(double x, double y) {
		this.setImage("Key.png");         
		this.setpos(x,y);

	}
	public Key(int n) {
		this.number = n;
	}

	public int getNumber() {
		return number;
	}
	public void setNumber(int n) {
		this.number = n;
	}

}
