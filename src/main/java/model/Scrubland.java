package model;

import java.awt.Color;
import java.awt.Polygon;

public class Scrubland extends Continent {

    public Scrubland(Polygon p) {
	super(p);
	// TODO Auto-generated constructor stub
    }
    
    public double getSunnyness() {
	return 0.5;
    }
    
    @Override
    protected Color getFillColor() {
	return new Color(237, 250, 175);
    }

}
