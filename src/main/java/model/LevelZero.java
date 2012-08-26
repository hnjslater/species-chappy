package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class LevelZero extends Level {
    private BufferedImage bg = null;
    
    int stage = 0;
    Bridge b1;
    Bridge b2;
    Continent capital;
    
    public LevelZero() {
	

	try {                
	    java.io.InputStream is = getClass().getResourceAsStream("/level_zero.png");
	    bg = ImageIO.read(is);
	} catch (Exception ex) {
	    System.out.println("couldn't load background");
	}
	
	for (int i = 0; i < 5; i++) {
	    Chappy c = new Chappy();
	    c.setX(350);
	    c.setY(60);
	    populate(c);
	}
	{
	    Polygon p = new Polygon();
	    p.addPoint(300, 10);
	    p.addPoint(300, 110);
	    p.addPoint(400, 110);
	    p.addPoint(400, 10);
	    Continent c = new Continent(p);
	    populate(c);	    
	}
	{
	    Polygon p = new Polygon();
	    p.addPoint(250, 160);
	    p.addPoint(250, 260);
	    p.addPoint(450, 260);
	    p.addPoint(450, 160);
	    Continent c = new Continent(p);
	    populate(c);	    
	}
	{
	    Polygon p = new Polygon();
	    p.addPoint(300, 310);
	    p.addPoint(300, 410);
	    p.addPoint(400, 410);
	    p.addPoint(400, 310);
	    capital = new Hatland(p);
	    populate(capital);	    
	}
	{
	    Polygon p = new Polygon();
	    p.addPoint(320, 100);
	    p.addPoint(380, 100);
	    p.addPoint(380, 160);
	    p.addPoint(320, 160);
	    b1 = new Bridge(p);
	    b1.setOpen(false);
	    populate(b1);
	}
	{
	    Polygon p = new Polygon();
	    p.addPoint(280, 250);
	    p.addPoint(420, 250);
	    p.addPoint(380, 320);
	    p.addPoint(320, 320);
	    b2 = new Bridge(p);
	    b2.setOpen(false);
	    populate(b2);
	}
    }
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
	g.setFont(g.getFont().deriveFont(16));
	g.drawString("These are chappies. They explore",10,30);
	g.drawString("breed and then die. The yellow lines", 10, 50);
	g.drawString("are bridges, click the gap between the", 10, 70);
	g.drawString("higher two.", 10, 90);
	if (stage == 0 && b1.isOpen()) {
	    stage++;
	}
	if (stage > 0) {
	    g.drawString("Well done, now the chappies can",10,120);
	    g.drawString("explore. A larger continent can", 10, 140);
	    g.drawString("support a higher population.",10,160);
	}
	if (stage == 1 && b2.isOpen()) {
	    stage++;
	}
	if (stage > 1) {
	    g.drawString("The young of chappies are always",10,300);
	    g.drawString("better adapted to their current ",10,320);
	    g.drawString("continent. In this case: hats. Fill",10,340);
	    g.drawString("the capital with the tallest hats to win.",10,360);
	}
	if (stage == 3) {
	    g.drawString("You win!",10,380);
	}
	
	
    }
    @Override
    public void tick() {
	super.tick();
	double total_hats = 0;
	for (Character c : characters) {
	    if (c instanceof Chappy && c.continent == capital) {
		total_hats += ((Chappy)c).hat;
	    }
	}
	double ave_hat = total_hats / capital.getPopulation();
	if (ave_hat > 0.6) {
	    stage = 3;
	}
    }
    @Override
    public boolean getComplete() {
	return stage == 3;
    }
}
