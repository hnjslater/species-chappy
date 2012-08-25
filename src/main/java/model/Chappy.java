package model;

import java.awt.Color;
import java.awt.Graphics;

public class Chappy extends Character {
    double sun_adaption;
    double sun_burn;
    public Chappy() {
	super();
	sun_adaption = 0;
	sun_burn = 0;
    }
    public Chappy(Chappy ma, Chappy pa, Landmass l) {
	sun_burn = 0;
	sun_adaption = (ma.sun_adaption + pa.sun_adaption) / 2;
	sun_adaption += (l.getSunnyness()-sun_adaption) * 0.1;
	this.setX((ma.getX() + pa.getX()) / 2);
	this.setY((ma.getY() + pa.getY()) / 2);
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
	return (c instanceof Chappy);
    }
    @Override
    public void tick() {
        // TODO Auto-generated method stub
        super.tick();
        if (this.isAlive()) {
                    
            Landmass current = level.whereAmI(X,Y,W,H);
            
            if (current.getSunnyness() > sun_adaption) {
        	sun_burn += (current.getSunnyness() - sun_adaption) * 0.1;
        	if (sun_burn >= 1) {
        	    this.kill();
        	    return;
        	}
            }
            
            if (Math.random() * 100 > 96 && current.getPopulationDensity() < 2) {
        	breed(current);
            }
        }
    }
    @Override
    public void paint(Graphics g) {
	if (isAlive()) {
	    g.setColor(Color.GRAY);
	    g.fillOval(X, Y, 10, 10);
	    g.setColor(new Color(255-(int)(255*sun_adaption), 255-(int)(255*sun_adaption), 255-(int)(255 * sun_adaption)));
	    g.fillOval(X-3,Y-4,8,8);
	    g.fillOval(X+5,Y-4,8,8);
	    g.setColor(Color.BLACK);
	    g.drawOval(X-3,Y-4,8,8);
	    g.drawOval(X+5,Y-4,8,8);
	    g.drawLine(X+1, Y, X+1, Y);
	    g.drawLine(X+9, Y, X+9, Y);
	}
    }

}
