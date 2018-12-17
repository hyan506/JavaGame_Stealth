package application;

public class Bird extends Movable{
	public Bird () {
	    this.setImage("bird_up.png");
		this.setpos(50, 50);
		this.setHspeed(0);
		this.setVspeed(0);
		}
	public void updatepos(double time)
    {
		if(!(Xpos + Xspeed * time >1014 ||Xpos + Xspeed * time<25)) {
			Xpos += Xspeed * time;
			
		}
		if(!(Ypos + Yspeed * time >600 ||Ypos + Yspeed * time<25)) {
			Ypos += Yspeed * time;
		}
			
		
       
    }
	public boolean Touch(Coin C)
    {
        return C.getBoundary().intersects( this.getBoundary() );
    }
}
