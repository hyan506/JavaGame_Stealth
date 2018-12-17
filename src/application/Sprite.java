package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
	protected Image image;
	protected double width;
	protected double height;
	protected double Xpos;
	protected double Ypos;  
    public void setImage(String filename)
    {
        Image i = new Image(filename);
        image = i;
        width = i.getWidth();
        height = i.getHeight();
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
    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(Xpos,Ypos,width,height);
    }
    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, Xpos, Ypos );
    }
}
