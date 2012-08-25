package model;

public class Chappy extends Character {
    public Chappy() {
	super();
    }
    public Chappy(Character ma, Character pa) {
	this.setX((ma.getX() + pa.getX()) / 2);
	this.setY((ma.getY() + pa.getY()) / 2);
    }

}
