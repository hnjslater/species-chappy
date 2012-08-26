package model;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Finale extends Level {
    private BufferedImage bg = null;
    
    public Finale() {
	
	try {                
	    java.io.InputStream is = getClass().getResourceAsStream("/finale.png");
	    bg = ImageIO.read(is);
	} catch (Exception ex) {
	    System.out.println("couldn't load background");
	}

	for (int i = 0; i < 20; i++) {
	    Chappy c1 = new Chappy();
	    c1.setX((int)(250));
	    c1.setY((int)(250));
	    populate(c1);
	}
	{
	    Polygon p = new Polygon();
	    p.addPoint(35, 350);
	    p.addPoint(460, 350);
	    p.addPoint(460, 455);
	    p.addPoint(35, 455);
	    Continent c = new Desert(p);
		populate(c);
	}
	{
	Polygon p = new Polygon();
	p.addPoint(0, 0);
	p.addPoint(0, 500);
	p.addPoint(500, 500);
	p.addPoint(500, 0);
	Bridge c = new Bridge(p);
	populate(c);
	}
    }
    
@Override
public void paint(Graphics g) {
	prepaint(g);
	for (Bridge b : bridges) {
	    b.paint(g);
	}
	if (bg != null) {
	    g.drawImage(bg, 0,0,null);
	}
	else {
	    for (Continent c : continents) {
		c.paint(g);
	    }
	}
	for (Character c : characters) {
	    c.paint(g);
	}
}
}
