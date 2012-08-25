package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Bridge extends Landmass {
    public Bridge(Polygon p) {
	super(p);
	this.isOpen = true;
    }
    private boolean isOpen;
    public void paint(Graphics g) {
	g.setColor(Color.YELLOW);
	if (isOpen())
	g.fillPolygon(p);
	else
	    g.drawPolygon(p);
	
    }
    public int getType() {
	return Landmass.BRIDGE;
    }
    public boolean isOpen() {
	return isOpen;
    }
    public void setOpen(boolean b) {
	isOpen = b;
    }

}
