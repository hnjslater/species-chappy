package model;

public abstract class  Landmass {
    public static int CONTINENT = 1;
    public static int BRIDGE = 2;
    abstract int getType();
    public static double habitable(Landmass l, Character c) {
	if (l == null) {
	    return 0;
	}
	else if (l.getType() == Landmass.BRIDGE) {
	    return ((Bridge)l).isOpen() ? 1 : 0;
	}
	return 1;
    }
}
