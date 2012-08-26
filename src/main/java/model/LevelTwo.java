package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class LevelTwo extends Level {
    Continent goal;

    public LevelTwo() {
	setBackground("/level_two.png");
	{ // TOP
	Polygon p = new Polygon();
	p.addPoint(100, 20);
	p.addPoint(400, 20);
	p.addPoint(400, 80);
	p.addPoint(100, 80);
	populate(new Continent(p));
	}
	{ //LEFT
	Polygon p = new Polygon();
	p.addPoint(20, 100);
	p.addPoint(20, 400);
	p.addPoint(80, 400);
	p.addPoint(80, 100);
	populate(new Continent(p));
	}
	{ // RIGHT
	Polygon p = new Polygon();
	p.addPoint(420, 100);
	p.addPoint(420, 400);
	p.addPoint(480, 400);
	p.addPoint(480, 100);
	populate(new Continent(p));
	}
	{ //MIDDLE
	Polygon p = new Polygon();
	p.addPoint(150, 150);
	p.addPoint(150, 350);
	p.addPoint(350, 350);
	p.addPoint(350, 150);
	populate(new Poisonland(p));
	}	
	{ //TOP LEFT
	    Polygon p = new Polygon();
		p.addPoint(100, 20);
		p.addPoint(100, 80);
		p.addPoint(80, 100);
		p.addPoint(20, 100);
		Bridge b = new Bridge(p);
		b.setOpen(false);
		populate(b);
	}
	{ //TOP RIGHT
	    Polygon p = new Polygon();
	    
		p.addPoint(400, 20);
		p.addPoint(390, 20);
		p.addPoint(390, 80);
		p.addPoint(400, 80);
		p.addPoint(420, 100);
		p.addPoint(480, 100);
		Bridge b = new Bridge(p);
		b.setOpen(false);
		populate(b);
	}
	{ //TOP-MIDDLE bridge
	    Polygon p  = new Polygon();
		p.addPoint(200, 160);
		p.addPoint(200, 70);
		p.addPoint(300, 70);
		p.addPoint(300, 160);
		Bridge b = new Bridge(p);
		b.setOpen(false);
		populate(b);
	}
	{ //LEFT-MIDDLE bridge
	    Polygon p  = new Polygon();
		p.addPoint(70, 200);
		p.addPoint(70, 300);
		p.addPoint(160, 300);
		p.addPoint(160, 200);
		Bridge b = new Bridge(p);
		b.setOpen(false);
		populate(b);
	}	
	{ //LEFT-MIDDLE bridge
	    Polygon p  = new Polygon();
		p.addPoint(430, 200);
		p.addPoint(430, 300);
		p.addPoint(240, 300);
		p.addPoint(240, 200);
		Bridge b = new Bridge(p);
		b.setOpen(false);
		populate(b);
	}
	{ //bottom bridge
	    Polygon p  = new Polygon();
		p.addPoint(350, 340);
		p.addPoint(350, 350);
		p.addPoint(300, 400);
		p.addPoint(200, 400);
		p.addPoint(150, 350);
		p.addPoint(150, 340);
		Bridge b = new Bridge(p);
		b.setOpen(false);
		populate(b);
	}	
	{ //Goal
	    Polygon p  = new Polygon();
		p.addPoint(200, 400);
		p.addPoint(300, 400);
		p.addPoint(300, 450);
		p.addPoint(200, 450);
		goal = new Continent(p);
		populate(goal);
	}
	for (int i = 0; i < 20; i++) {
	    Chappy c1 = new Chappy();
	    c1.setX(250);
	    c1.setY(60);
	    populate(c1);
	}
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        

	g.setColor(Color.black);
	g.drawString("Starland Population: " + goal.getPopulation()+ " Aim: 5", 0, 450);
    }
    @Override
    public boolean getComplete() {
	return goal.getPopulation() > 5;
    }

}
