package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;

public class Level {
    protected List<Character> characters;
    protected List<Continent> continents;
    protected List<Bridge> bridges;
    private BufferedImage bg = null;
    
    public Level() {
	characters = Collections.synchronizedList(new CopyOnWriteArrayList<Character>());
	continents = Collections.synchronizedList(new CopyOnWriteArrayList<Continent>());
	bridges = Collections.synchronizedList(new CopyOnWriteArrayList<Bridge>());
    }
    
    public void populate(Character c) {
	c.setLevel(this);
	characters.add(c);
    }
    public void populate(Continent c) {
	continents.add(c);
    }
    public void populate(Bridge b) {
	bridges.add(b);
    }
    
    public void tick() {
	for (Continent c : continents) {
	    c.zeroPopulation();
	}
	for (Bridge b : bridges) {
	    b.zeroPopulation();
	}
	for (Character c : characters) {
	    Landmass m = whereAmI(c.getX(), c.getY(), c.W, c.H);
	    if (m!=null) 
		m.incPopulation();
	    c.continent = m;
	    
	}
	for (Character c : characters) {
	    c.tick();
	}
	Iterator<Character> itr = characters.iterator();
	ArrayList<Character> dead = new ArrayList<Character>();
	while (itr.hasNext()) {
	    Character element = itr.next();
	    if (!element.isAlive())
		dead.add(element);
	}
	characters.removeAll(dead);
	ArrayList<Character> newBirths = new ArrayList<Character>();
	for (Character c : characters) {
	    newBirths.addAll(c.getYoung());
	    c.getYoung().clear();
	}
	for (Character c : newBirths) {
	    this.populate(c);
	}
    }
    
    public void paint(Graphics g) {
	((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
		RenderingHints.VALUE_ANTIALIAS_ON);
	g.setColor(Color.blue);
	g.fillRect(0, 0, 500, 500);

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

    public Landmass whereAmI(int x, int y, int w, int h) {
	for (Continent c : continents) {
	    if (c.p.contains(x, y,w,h))
		return c;
	}
	for (Bridge b : bridges) {
	    if (b.p.contains(x, y) && b.isOpen())
		return b;
	}	
	return null;
    }

    public void click(int x, int y) {	
	for (Bridge b : bridges) {
	    if (b.p.contains(x, y))
		b.setOpen(!b.isOpen());
	}	
    
	
    }

    public List<Character> getCharacters() {
	return Collections.unmodifiableList(characters);
    }

    public double getPopulation() {
	return characters.size();
    }
    public boolean getComplete() {
	return false;
    }

    protected void setBackground(String resource) {
        try {                
            java.io.InputStream is = getClass().getResourceAsStream(resource);
            bg = ImageIO.read(is);
        } catch (Exception ex) {
            System.out.println("couldn't load background");
        }
    }
    
}
