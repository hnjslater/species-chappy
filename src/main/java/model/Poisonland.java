package model;

import java.awt.Color;
import java.awt.Polygon;

public class Poisonland extends Continent {

    public Poisonland(Polygon p) {
	super(p);
	// TODO Auto-generated constructor stub
    }
    
    @Override
    public double getLethalness() {
        // TODO Auto-generated method stub
        return 0.08;
    }
    
    @Override
    protected Color getFillColor() {
        // TODO Auto-generated method stub
        return Color.pink;
    }

}
