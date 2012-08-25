package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Character {
    
    private int X;
    private int Y;
    private int dX;
    private int H;
    private int W;
    private Level level;
    private int dY;
    private boolean isAlive;
    private int age;
    private List<Character> young;
    
    public Character() {
	W = 10;
	H = 10;
	age = 0;
	    setdX((int)(Math.random() * 4)+1);
	    setdY((int)(Math.random() * 4)+1);
	
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
	    g.setColor(Color.WHITE);
	    g.fillOval(X-3,Y-4,8,8);
	    g.fillOval(X+5,Y-4,8,8);
	    g.setColor(Color.BLACK);
	    g.drawOval(X-3,Y-4,8,8);
	    g.drawOval(X+5,Y-4,8,8);
	    g.drawLine(X+1, Y, X+1, Y);
	    g.drawLine(X+9, Y, X+9, Y);
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
	if (age + (1000 * Math.random()) > 1500) {
	    this.kill();
	}
	
	if (Math.random() * 1000 > 999) {
	    breed(current);
	}
	
    }


    private void breed(Landmass current) {
	double distance = Double.MAX_VALUE;
	Character candidate = null;
	for (Character c : level.getCharacters()) {
	    if (level.whereAmI(c.getX(), c.getY(), c.W, c.H) == current) {
		double newDistance = Math.pow(Math.pow(c.getX(), 2) + Math.pow(this.getX(), 2), 0.5);
		if (newDistance < distance) {
		    distance = newDistance;
		    candidate = c;
		}
	    }
	}
	
	if (candidate != null) {
	 	young.add(new Chappy(this, candidate));
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
