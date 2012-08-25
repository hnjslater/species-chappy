package model;

import java.awt.Color;
import java.awt.Polygon;

public class Desert extends Continent {
    public Desert(Polygon p) {
	super(p);
	// TODO Auto-generated constructor stub
    }

    public double getSunnyness() {
	return 1;
    }
    
    @Override
    protected Color getFillColor() {
	return new Color(237, 201, 175);
    }

}
