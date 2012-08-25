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
	p1.addPoint(60,60);
	p1.addPoint(120,50);
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
	
	Polygon p3 = new Polygon();
	p3.addPoint(150,116);
	p3.addPoint(201,120);
	p3.addPoint(202,149);
	p3.addPoint(150,160);
	Bridge b = new Bridge(p3);
	
	lev.populate(ct1);
	lev.populate(ct2);
	lev.populate(b);
	
	
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
