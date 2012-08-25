package model;

import java.awt.Color;
import java.awt.Graphics;

public class Character {
    
    private int X;
    private int Y;
    private int dX;
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

    private int dY;
    private boolean isAlive;
    
    public Character() {
	isAlive = true;
    }
    
    public int getX() {
	return X;
    }
    public void setX(int x) {
	X = x;
    }
    public void paint(Graphics g) {
	System.out.println(X);
	g.setColor(Color.GRAY);
	g.fillOval(X, Y, 10, 10);
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
    }

    public void tick() {
	X += dX;
	Y += dY;
	
    }


}
