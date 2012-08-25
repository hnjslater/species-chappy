package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

public class Level {
    private ArrayList<Character> characters;
    
    public Level() {
	characters = new ArrayList<Character>();
    }
    
    public void populate(Character c) {
	characters.add(c);
    }
    
    public void tick() {
	
	for (Character c : characters) {
	    c.tick();
	}
    }
    
    public void paint(Graphics g, int unit) {
	    ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
		        RenderingHints.VALUE_ANTIALIAS_ON);
	    g.setColor(Color.blue);
	    g.fillRect(0, 0, 100 * unit, 100 * unit);
	for (Character c : characters) {
	    c.paint(g);
	}
    }
}
