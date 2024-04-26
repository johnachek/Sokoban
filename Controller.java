import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

class Controller implements KeyListener, ActionListener{
    Model model;
    Controller (Viewer viewer){
	model = new Model(viewer);
    
    }
    public void actionPerformed (ActionEvent event) {
        	String command = event.getActionCommand();	
        if (command.equals("Undo")) {
	    System.out.println("Undo");
	    model.undo();
	}
        if (command.equals("Exit")) {
	    System.exit(1);
	}
        if (command.equals("About")) {
	    AboutFrame();
	}
        if (command.equals("Restart")) {
	    model.nextLevel();
	}
        if (command.equals("Next")) {
	    model.skipLevel();
	}
        if (command.equals("Prev")) {
	    model.backLevel();
	}

    }
    
    public void keyTyped(KeyEvent event) {

    }
    public void keyPressed(KeyEvent event) {
	int keyCode = event.getKeyCode();
	String direction = "";
	switch(keyCode){
	    case 39:
		direction = "Right";
	    break;
	    case 37:
		direction = "Left";
	    break;
	    case 38:
		direction = "Down";
	    break;
	    case 40:
		direction = "Up";
	    break;
	}
	model.move(direction);
    }
	
    public void keyReleased(KeyEvent event) {

    }

    public void AboutFrame(){
	JFrame aboutFrame = new JFrame();
	JOptionPane.showMessageDialog(aboutFrame, 
	"This game was created by Joomart Acheekeev!\n Please enjoy!", "About Game!", 
	JOptionPane.INFORMATION_MESSAGE);
    }

}