package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Movable extends Sprite{    
	protected double Xspeed;
	protected double Yspeed;
    protected boolean touchRight;
    protected boolean touchLeft;
    protected boolean touchUp;
    protected boolean touchDown;


    public boolean isTouchRight() {
		return touchRight;
	}

	public void setTouchRight(boolean touchRight) {
		this.touchRight = touchRight;
	}

	public boolean isTouchLeft() {
		return touchLeft;
	}

	public void setTouchLeft(boolean touchLeft) {
		this.touchLeft = touchLeft;
	}

	public boolean isTouchUp() {
		return touchUp;
	}

	public void setTouchUp(boolean touchUp) {
		this.touchUp = touchUp;
	}

	public boolean isTouchDown() {
		return touchDown;
	}

	public void setTouchDown(boolean touchDown) {
		this.touchDown = touchDown;
	}


	public double getHpos() {
		return Xpos;
	}
	public double getVpos() {
		return Ypos;
	}
	public double getWidth() {
		return width;
	}
	public double getHeight() {
		return height;
	}


	public void setpos(double hpos,double vpos) {
		Xpos = hpos;
		Ypos = vpos;
	}


	public double getHspeed() {
		return Xspeed;
	}
	public void setHspeed(double hspeed) {
		Xspeed = hspeed;
	}
	public void addHspeed(double hspeed) {
		if(Xspeed+hspeed<=150 && Xspeed+hspeed>-150)
		Xspeed += hspeed;
	}
	public double getVspeed() {
		return Yspeed;
	}
	public void setVspeed(double vspeed) {
		Yspeed = vspeed;
	}
	public void addVspeed(double vspeed) {
		if(Yspeed+vspeed<=150 && Yspeed+vspeed>-150)
		Yspeed += vspeed;
	}
    public boolean Touch(wall W)
    {
        return W.getBoundary().intersects( this.getBoundary() );
    }

}
