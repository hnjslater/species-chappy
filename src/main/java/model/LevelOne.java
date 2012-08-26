package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class LevelOne extends Level {
    private BufferedImage bg = null;
    
    public LevelOne() {
	
	try {                
	    java.io.InputStream is = getClass().getResourceAsStream("/level_one.png");
	    bg = ImageIO.read(is);
	} catch (Exception ex) {
	    System.out.println("couldn't load background");
	}

	for (int i = 0; i < 20; i++) {
	    Chappy c1 = new Chappy();
	    c1.setX((int)(Math.random() * 100)+53);
	    c1.setY((int)(Math.random() * 100)+53);
	    populate(c1);
	}

	
	Polygon p1 = new Polygon();
	p1.addPoint(20,20);
	p1.addPoint(120,10);
	p1.addPoint(180,20);
	p1.addPoint(160,160);
	p1.addPoint(60, 180);
	Continent ct1 = new Continent(p1);
	
	Polygon p2 = new Polygon();
	p2.addPoint(200,100);
	p2.addPoint(200,200);	
	p2.addPoint(300,400);
	p2.addPoint(500,300);
	p2.addPoint(300,100);
	Continent ct2 = new Desert(p2);
	
	Polygon p4 = new Polygon();
	p4.addPoint(60,260);
	p4.addPoint(120,250);
	p4.addPoint(180,220);
	p4.addPoint(160,360);
	p4.addPoint(60, 380);
	Continent ct3 = new Scrubland(p4);
	
	Polygon p3 = new Polygon();
	p3.addPoint(150,100);
	p3.addPoint(220,120);
	p3.addPoint(220,169);
	p3.addPoint(150,160);
	Bridge b = new Bridge(p3);
	
	Polygon p5 = new Polygon();
	p5.addPoint(62,260);
	p5.addPoint(62, 150);
	p5.addPoint(160,150);
	p5.addPoint(120,250);	
	Bridge b2 = new Bridge(p5);
	b2.setOpen(false);

	Polygon p6 = new Polygon();
	p6.addPoint(172,226);
	p6.addPoint(214,204);
	p6.addPoint(295,307);
	p6.addPoint(148,348);
	Bridge b3 = new Bridge(p6);
	b3.setOpen(false);
	
	populate(ct1);
	populate(ct2);
	populate(ct3);
	populate(b);
	populate(b2);
	populate(b3);
	
    }
    
    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
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
	g.setColor(Color.black);
	g.drawString("Current Population: " + this.characters.size() + " Aim: 200", 0, 450);
	if (this.characters.size() > 200) {
	    g.setColor(Color.PINK);
	    g.setFont(g.getFont().deriveFont((float) (g.getFont().getSize() * 4)));
	    g.drawString("YOU WIN!", 200, 450);
	}
    }
@Override
public boolean getComplete() {
    return this.characters.size() > 200;
}
}
