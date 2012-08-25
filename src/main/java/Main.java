import javax.swing.JFrame;
import javax.swing.JPanel;

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
		lev.paint(g,5);
	    };
	};
	jf.add(jp);
	
	
	
	
	Chappy c1 = new Chappy();
	c1.setX(30);
	c1.setY(30);
	
	Chappy c2 = new Chappy();
	c2.setX(100);
	c2.setY(100);
	c1.setdX(10);
	c2.setdY(10);
	
	lev.populate(c1);
	lev.populate(c2);
	
	Runnable r = new Runnable() {

	    public void run() {
		while (true) {
		lev.tick();
		jp.repaint();
		try {
		    Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		}
		
	    }
	    
	};
	jf.show();
	r.run();
	
	

    }

}
