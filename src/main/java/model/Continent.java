package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Continent extends Landmass{
    
    protected Polygon p;
    
    public Continent(Polygon p) {
	this.p = p;
    }
    
    protected Color getFillColor() {
	return Color.green;
    }
    public void paint(Graphics g) {
	
	g.setColor(getFillColor());
	g.fillPolygon(p);
	g.setColor(Color.BLACK);
	g.drawPolygon(p);
    }

    public int getType() {
	return Landmass.CONTINENT;
    }

}
