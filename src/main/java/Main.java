import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import model.*;


public class Main extends JPanel implements MouseListener, Runnable {
    private static final long serialVersionUID = 1L;
    List<Level> levels = new ArrayList<Level>();
    int level = 0;
    /**
     * @param args
     */
        
    public Main() {
	levels.add(new LevelZero());
	levels.add(new LevelOne());
	levels.add(new LevelTwo());
	levels.add(new Finale());
	addMouseListener(this);
    }
    public static void main(String[] args) {
	final Main jp = new Main();
	
	JFrame jf = new JFrame();

	
	
	

	jf.setSize(500, 500);
	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jf.add(jp);
	jf.setResizable(false);
	jf.setTitle("Species:Chappy");
	
	    	
	

	jf.setVisible(true);
	Thread t = new Thread(jp);
	t.run();
	
	
	

    }
    public void paint(java.awt.Graphics g) {
	levels.get(level).tick();
	levels.get(level).paint(g);
	
	if (levels.get(level).getComplete()) {
	    level++;
	}
    }
    public void mouseClicked(MouseEvent e) {
        levels.get(level).click(e.getX(), e.getY());
	
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
    @Override
    public void run() {
	while (true) {
	this.repaint();
	try {
	    Thread.sleep(100);
	} catch (InterruptedException e) {
	}
	}
	
    }
}
