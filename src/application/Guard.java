package application;

public class Guard extends Person{
	public Guard (double x,double y, boolean direction) {
		this.setImage("guardRight.png");         
		this.setpos(x,y);
		if(direction) {
		this.setHspeed(150);
		}
		else {
		this.setVspeed(150);
		}
	}
	
	public void updatepos(double time)
    {
		if (this.isTouchLeft()){
			this.setImage("guardRight.png");
			Xspeed = Xspeed *(-1);
		}
		if (this.isTouchRight()){
			this.setImage("guardLeft.png");
			Xspeed = Xspeed *(-1);
		}
		if (this.isTouchUp() || this.isTouchDown()){
			Yspeed = Yspeed *(-1);
		}
		Xpos += Xspeed * time;
		Ypos += Yspeed * time;    
    }
    public boolean Touch(Thief T)
    {
        return T.getBoundary().intersects( this.getBoundary() );
    }
    public boolean Touch(Dots D)
    {
        return D.getBoundary().intersects( this.getBoundary() );
    }
}
