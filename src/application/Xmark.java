package application;

public class Xmark extends Dots{
	boolean set = false;
	public Xmark() {
		this.setImage("X.jpg");
		this.setpos(1100,800);
		set = false;
	}
	public boolean isSet() {
		return set;
	}
	public void setSet(boolean set) {
		this.set = set;
	}
	
}
