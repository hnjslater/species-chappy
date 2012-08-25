import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import model.*;


public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
	final Level lev = new Level();
	
	final JFrame jf = new JFrame();
	jf.setSize(500, 500);
	
	final JPanel jp = new JPanel() {
	    private static final long serialVersionUID = 1L;
	    public void paint(java.awt.Graphics g) {
		lev.tick();
		lev.paint(g);
	    };
	};
	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jf.add(jp);
	jf.setResizable(false);
	jp.addMouseListener(new MouseListener() {
	    public void mouseClicked(MouseEvent e) {
	        lev.click(e.getX(), e.getY());
	        
	    }

	    public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	    }

	    public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	    }

	    public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	    }

	    public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	    }
	});
	
	
	for (int i = 0; i < 20; i++) {
	    Chappy c1 = new Chappy();
	    c1.setX((int)(Math.random() * 100)+53);
	    c1.setY((int)(Math.random() * 100)+53);
	    lev.populate(c1);
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
	p3.addPoint(201,100);
	p3.addPoint(202,149);
	p3.addPoint(150,160);
	Bridge b = new Bridge(p3);
	
	Polygon p5 = new Polygon();
	p5.addPoint(50,260);
	p5.addPoint(50, 150);
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
	
	lev.populate(ct1);
	lev.populate(ct2);
	lev.populate(ct3);
	lev.populate(b);
	lev.populate(b2);
	lev.populate(b3);
	
	
	Runnable r = new Runnable() {

	    public void run() {
		while (true) {
		jp.repaint();
		try {
		    Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		}
		
	    }
	    
	};
	jf.setVisible(true);
	r.run();
	
	

    }

}
