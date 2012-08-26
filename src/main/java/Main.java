import java.awt.Dimension;
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
	final Level lev = new LevelOne();
	
	final JFrame jf = new JFrame();
	
	final JPanel jp = new JPanel() {
	    private static final long serialVersionUID = 1L;
	    public void paint(java.awt.Graphics g) {
		lev.tick();
		lev.paint(g);
	    };
	};

	jf.setSize(500, 500);
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
