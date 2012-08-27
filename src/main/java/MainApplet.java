import java.applet.Applet;
import java.awt.BorderLayout;

import javax.swing.JPanel;


public class MainApplet extends Applet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    Thread t;
    Main main = null;
    boolean running = true;
    @Override
    public void init() {
	main  = new Main();
	this.setLayout(null);
	this.add(main);
	main.setBounds(0, 0, 500, 500);
    }
    
    @Override
    public void start() {
	t = new Thread(main);
	t.start();
    }
    
    @Override
    public void stop() {
	t=null;
    }
    
    @Override
    public void destroy() {
    }

}
