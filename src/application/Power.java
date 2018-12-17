package application;

public class Power extends Dots{
	private int PowerNumber;

	public Power() {
		PowerNumber = (int) ((Math.random() * 3) + 1);
	}

	public int getPowerNumber() {
		return PowerNumber;
	}
	public void setPowerNumber(int powerNumber) {
		PowerNumber = powerNumber;
	}
}
