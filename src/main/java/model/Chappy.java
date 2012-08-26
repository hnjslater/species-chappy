package model;

import java.awt.Color;
import java.awt.Graphics;

public class Chappy extends Character {
    double sun_adaption;
    double health;
    public double hat = 0;
    public Chappy() {
	super();
	sun_adaption = 0;
	health = 0;
	hat = 0;
    }
    public Chappy(Chappy ma, Chappy pa, Landmass l) {
	health = 0;
	sun_adaption = (ma.sun_adaption + pa.sun_adaption) / 2;
	sun_adaption += (l.getSunnyness()-sun_adaption) * 0.1;
	hat = (ma.hat + pa.hat) / 2;
	hat += (l.getHattiness() - hat) * 0.1;
	this.setX(ma.getX());
	this.setY(ma.getY());
    }
    private void breed(Landmass current) {
	double distance = Double.MAX_VALUE;
	Character candidate = null;
	for (Character c : level.getCharacters()) {
	    if (c.canBreedWith(this) && level.whereAmI(c.getX(), c.getY(), c.W, c.H) == current) {
		double newDistance = Math.pow(Math.pow(c.getX(), 2) + Math.pow(this.getX(), 2), 0.5);
		if (newDistance < distance) {
		    distance = newDistance;
		    candidate = c;
		}
	    }
	}
	if (candidate != null) {
	    young.add(new Chappy(this, (Chappy)candidate, current));
	}
    }
    
    @Override
    public boolean canBreedWith(Character c) {
	return (c instanceof Chappy && c != this);
    }
    @Override
    public void tick() {
        // TODO Auto-generated method stub
        super.tick();
        if (this.isAlive()) {
                    
            Landmass current = level.whereAmI(X,Y,W,H);
            if (current == null)
        	return;
            
            if (current.getSunnyness() > sun_adaption) {
        	health += (current.getSunnyness() - sun_adaption) * 0.1;
            }
            health += current.getLethalness();
            if (health >= 1) {
        	this.kill();
        	return;
            }
            
            if (Math.random() * 100 > 96 && current.getPopulationDensity() < 2) {
        	breed(current);
            }
        }
    }
    @Override
    public void paint(Graphics g) {
	if (isAlive()) {
	    // body
	    g.setColor(Color.GRAY);
	    g.fillOval(X, Y, 10, 10);
	    
	    // sunglasses/eyes
	    g.setColor(new Color(255-(int)(255*sun_adaption), 255-(int)(255*sun_adaption), 255-(int)(255 * sun_adaption)));
	    g.fillOval(X-3,Y-4,8,8);
	    g.fillOval(X+5,Y-4,8,8);
	    g.setColor(Color.BLACK);
	    g.drawOval(X-3,Y-4,8,8);
	    g.drawOval(X+5,Y-4,8,8);
	    g.drawLine(X+1, Y, X+1, Y);
	    g.drawLine(X+9, Y, X+9, Y);
	    
	    // rhat
	    int hat_radius = (int) (hat * 16);
	    if (hat_radius > 0) {
		g.fillRect(X +1, Y - 4 - hat_radius, 8,  hat_radius);
		g.fillRect(X -2, Y - 4, 14,  2);
	    }
	}
    }

}
