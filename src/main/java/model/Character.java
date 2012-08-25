package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Character {
    
    protected int X;
    protected int Y;
    protected int dX;
    protected int H;
    protected int W;
    protected Level level;
    protected int dY;
    private boolean isAlive;
    private int age;
    protected List<Character> young;
    
    public boolean canBreedWith(Character c) {
	return false;
    }
    
    public Character() {
	W = 10;
	H = 10;
	age = 0;
	setdX((int)(Math.random() * 8)-4);
	setdY((int)(Math.random() * 8)-4);
	if (getdX() == 0)
	    setdX(1);
	if (getdY() == 0)
	    setdY(1);
	
	isAlive = true;
	young = new ArrayList<Character>();
    }
 
    public List<Character> getYoung() {
	return young;
    }
    
    public Level getLevel() {
	return level;
    }

    public void setLevel(Level l) {
	this.level = l;
    }

    public int getdX() {
        return dX;
    }

    public void setdX(int dX) {
        this.dX = dX;
    }

    public int getdY() {
        return dY;
    }

    public void setdY(int dY) {
        this.dY = dY;
    }

    
      
    public int getX() {
	return X;
    }
    public void setX(int x) {
	X = x;
    }
    public void paint(Graphics g) {
	if (isAlive()) {
	    g.setColor(Color.GRAY);
	    g.fillOval(X, Y, 10, 10);
	}
    }
    public int getY() {
	return Y;
    }
    public void setY(int y) {
	Y = y;
    }
    public boolean isAlive() {
	return isAlive;
    }
    public void kill() {
	this.isAlive = false;
	this.X = 0;
	this.Y = 0;
	this.dX = 0;
	this.dY = 0;
    }

    public void tick() {
	Landmass current = level.whereAmI(X,Y,W,H);
	if (current == null) {
	    this.kill();
	    return;
	}
	
	move(current);
	
	age++;
	if (age + (100 * Math.random()) > 150) {
	    this.kill();
	}
	

	
    }





    private void move(Landmass current) {
	
	boolean ok = false;
	
	Landmass next = level.whereAmI(X+dX,Y+dY,W,H);
	
	
	ok = (current == next || (next != null && next.getType() == Landmass.BRIDGE && ((Bridge)next).isOpen()) || (next != null && current.getType() == Landmass.BRIDGE));
	if (!ok) {
	    next = level.whereAmI(X-dX,Y+dY,W,H);
	    if ((current == next) || (next != null && next.getType() == Landmass.BRIDGE) && ((Bridge)next).isOpen()) {
		dX = -dX;
		ok = true;
	    }
	}
	if (!ok) {
	    next = level.whereAmI(X+dX,Y-dY,W,H);
	    if ((current == next) || (next != null && next.getType() == Landmass.BRIDGE) && ((Bridge)next).isOpen()) {
		dY = -dY;
		ok = true;
	    }
	}
	if (!ok) {	
	    next = level.whereAmI(X-dX,Y-dY,W,H);
	
	    if (current == next || (next != null && next.getType() == Landmass.BRIDGE) && ((Bridge)next).isOpen()) {
		dY = -dY;
		dX = -dX;
		ok = true;
	    }
	}	
	
	X += dX;
	Y += dY;
    }


}
