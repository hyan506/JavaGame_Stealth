package application;

public class Fireball extends Movable{
	public Fireball() {
		    this.setImage("FB_RIGHT.png");
		    this.setpos(1100,800);
			this.setHspeed(0);
			this.setVspeed(0);
	}
	public void CastV(double x, double y, boolean isDown) {
		this.setpos(x,y);
		this.setHspeed(0);
		this.setVspeed(0);
		if(isDown) {
			this.setImage("FB_DOWN.png");
			this.setVspeed(300);
		}
		else {
			this.setImage("FB_UP.png");
			this.setVspeed(-300);
		}
	}
	public void CastH(double x, double y, boolean isRight) {
		this.setpos(x,y);
		this.setHspeed(0);
		this.setVspeed(0);
		if(isRight) {
			this.setImage("FB_RIGHT.png");
			this.setHspeed(300);
		}
		else {
			this.setImage("FB_LEFT.png");
			this.setHspeed(-300);
		}
	}
	public void updatepos(double time)
    {

		Xpos += Xspeed * time;
		Ypos += Yspeed * time;   
		if(this.isTouchDown()||this.isTouchUp()||this.isTouchRight()||this.isTouchLeft()) {
			this.setpos(1100,800);
			this.setHspeed(0);
			this.setVspeed(0);
		}
    }
}
