package application;

public class Person extends Movable{
	protected String Name;
	public boolean Touch(Fireball F)
	    {
	        return F.getBoundary().intersects( this.getBoundary() );
	    }
}
