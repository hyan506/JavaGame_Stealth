package application;

public class Door extends Dots{
	private int doorNumber;
	boolean open = false;
	public Door(double x,double y) {
		this.setImage("Door.png");         
		this.setpos(x,y);
		doorNumber = (int) (Math.random() * 4);
	}


	public int getDoorNumber() {
		return doorNumber;
	}


	public void setDoorNumber(int doorNumber) {
		this.doorNumber = doorNumber;
	}


	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}

}
