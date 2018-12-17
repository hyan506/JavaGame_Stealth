package application;

import java.util.ArrayList;

public class Thief extends Person{
	
	
	public Thief (String Name) {
	this.Name = Name;
    this.setImage("Right.gif");
	this.setpos(50, 50);
	this.setHspeed(0);
	this.setVspeed(0);
	}
	ArrayList<Key> Keys = new ArrayList<Key>();
	public void pickKey(Key K) {
		Keys.add(K);
	}
	public boolean hasKey(int n) {
		for(int i = 0; i< Keys.size();i++) {
			if(Keys.get(i).getNumber() == n){
				return true;
			}
		}
		return false;
	}
    public boolean Touch(Dots D)
    {
        return D.getBoundary().intersects( this.getBoundary() );
    }

	public void updatepos(double time)
    {
		if (this.isTouchLeft() && this.isTouchUp() && !this.isTouchDown()) {
			Xspeed = 0;
			Yspeed = 0;
		} else if (this.isTouchLeft() && !this.isTouchUp() && this.isTouchDown()) {
			Xspeed = 0;
			Yspeed = 0;
		} else if (this.isTouchRight() && this.isTouchUp() && !this.isTouchDown()) {
			Xspeed = 0;
			Yspeed = 0;
		} else if (this.isTouchRight() && this.isTouchDown() && !this.isTouchUp()) {
			Xspeed = 0;
			Yspeed = 0;
		} else if (this.isTouchLeft()) {
			Ypos += Yspeed * time;
			Xspeed = 0;
		} else if (this.isTouchRight()) {
			Ypos += Yspeed * time;
			Xspeed = 0;
		} else if (this.isTouchUp()) {
			Xpos += Xspeed * time;
			Yspeed = 0;
		} else if (this.isTouchDown()) {
			Xpos += Xspeed * time;
			Yspeed = 0;
		} else {
			Xpos += Xspeed * time;
			Ypos += Yspeed * time;
		}
       
    }
}
