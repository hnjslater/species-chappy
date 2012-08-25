package model;

import java.awt.Polygon;

public abstract class  Landmass {
    protected Polygon p;
    protected double area;
    
    public Landmass(Polygon p) {
	this.p = p;
	this.area = polyArea(p) / 1000;
	this.population = 0;
    }
    
    private double polyArea(Polygon p) {
	// Copied from http://www.daniweb.com/software-development/java/threads/178927/area-of-a-polygon
	double area = 0;
	for(int i = 0; i <= p.npoints-1; i++)
	{
	    if(i == p.npoints-1)
	    {
		area += (p.xpoints[i]*p.ypoints[0])-(p.xpoints[0]*p.ypoints[i]);
	    }
	    else
	    {
		area += (p.xpoints[i]*p.ypoints[i+1])-(p.xpoints[i+1]*p.ypoints[i]);
	    }
	}
	area /= 2;
	//if they enter points counterclockwise the area will be negative but correct.
	if(area < 0)
	    area *= -1;
	return area;

    }
    
    public static int CONTINENT = 1;
    public static int BRIDGE = 2;
    
    private int population;
    
    abstract int getType();
    public double getSunnyness() {
	return 0;
    }
    public static double habitable(Landmass l, Character c) {
	if (l == null) {
	    return 0;
	}
	else if (l.getType() == Landmass.BRIDGE) {
	    return ((Bridge)l).isOpen() ? 1 : 0;
	}
	return 1;
    }
    
    public void zeroPopulation() {
	this.population = 0;
    }
    public void incPopulation() {
	this.population++;
    }
    public int getPopulation() {
	return this.population;
    }
    public double getPopulationDensity() {
	if (this.population == 0)
	    return 0;
	else
	    return this.population / this.area;
    }
}
