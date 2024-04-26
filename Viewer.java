import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class Viewer {

    Canvas canvas;
    Image image;
    JFrame window;

    Viewer() {
	Controller controller = new Controller(this);

        canvas = new Canvas(controller.model);
	
	loadImage();

	JFrame frame = new JFrame("Isken Baike - Batman");
	frame.setLocation(170, 80);
	frame.setSize(1600, 900);
	frame.add(canvas);
	
	JMenuBar menuBar = new JMenuBar();
	JMenu fileMenu = new JMenu("File");
	JMenu gameMenu = new JMenu("Game");
	JMenu aboutMenu = new JMenu("About");

	JMenuItem undoMenuItem = new JMenuItem("Undo");
	undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
	undoMenuItem.addActionListener(controller);
	undoMenuItem.setActionCommand("Undo");

	JMenuItem settingsMenuItem = new JMenuItem("Settings");
	settingsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
	settingsMenuItem.addActionListener(controller);
	settingsMenuItem.setActionCommand("Settings");

	JMenuItem exitMenuItem = new JMenuItem("Exit");
	exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
	exitMenuItem.addActionListener(controller);
	exitMenuItem.setActionCommand("Exit");

	JMenuItem restartMenuItem = new JMenuItem("Restart Level");
	restartMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
	restartMenuItem.addActionListener(controller);
	restartMenuItem.setActionCommand("Restart");

	JMenuItem nextLevelMenuItem = new JMenuItem("Next Level");
	nextLevelMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
	nextLevelMenuItem.addActionListener(controller);
	nextLevelMenuItem.setActionCommand("Next");

	JMenuItem prevLevelMenuItem = new JMenuItem("Previous Level");
	prevLevelMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
	prevLevelMenuItem.addActionListener(controller);
	prevLevelMenuItem.setActionCommand("Prev");

	JMenuItem aboutMenuItem = new JMenuItem("About Game");
	aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
	aboutMenuItem.addActionListener(controller);
	aboutMenuItem.setActionCommand("About");

	fileMenu.add(settingsMenuItem);
	fileMenu.add(undoMenuItem);
	fileMenu.add(exitMenuItem);
	gameMenu.add(restartMenuItem);
	gameMenu.add(nextLevelMenuItem);
	gameMenu.add(prevLevelMenuItem);
	aboutMenu.add(aboutMenuItem);

	menuBar.add(fileMenu);
	menuBar.add(gameMenu);
	menuBar.add(aboutMenu);
	frame.add("North", menuBar);

	frame.setIconImage(image);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	frame.setResizable(false);
	frame.addKeyListener(controller);	


    }
    public void loadImage(){
	try {
	    image = ImageIO.read(new File("images/new/playerStandFront.jpg"));
	} catch (IOException e) {
	}
    }
    public void updateCanvas() {
	canvas.repaint();
	return;
    }
    
    public void playerTurn(int turn){
	canvas.turnPlayer(turn);
    }

    public void showDialog(){
	
	window = new JFrame();
	JOptionPane.showMessageDialog(window, 
	"Level Complete!", 
	"Level Complete!", 
	JOptionPane.INFORMATION_MESSAGE);
    }
}