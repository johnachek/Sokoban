import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

import java.io.File;
import java.io.IOException;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Font;

class Canvas extends JPanel{

    Model model;
    Image imageGamer;
    Image brick;
    Image tile;
    Image box;
    Image target;
    Font font;
    Image playerStandFront;
    Image playerStandBack;
    Image playerStandLeft;
    Image playerStandRight;
    Image playerPushFront;
    Image playerPushBack;
    Image playerPushLeft;
    Image playerPushRight;

    Canvas (Model model) {

	this.model = model;
	setBackground(Color.black);
	font = new Font("Impact", Font.BOLD, 35);
	File fileNameGamer = new File("images/playerStandFront.jpg");
	File playerNameStandFront = new File("images/playerStandFront.jpg");
	File playerNameStandBack = new File("images/playerStandBack.jpg");
	File playerNameStandLeft = new File("images/playerStandLeft.jpg");
	File playerNameStandRight = new File("images/playerStandRight.jpg");
	File brickName = new File("images/brick.jpg");
	File tileName = new File("images/tile.jpg");
	File boxName = new File("images/steelBox.jpg");
	File targetName = new File("images/target.jpg");
	try {
	    imageGamer = ImageIO.read(fileNameGamer);
	    brick = ImageIO.read(brickName);
	    tile = ImageIO.read(tileName);
	    box = ImageIO.read(boxName);
	    target = ImageIO.read(targetName);
	    playerStandFront = ImageIO.read(playerNameStandFront);
	    playerStandBack = ImageIO.read(playerNameStandBack);
	    playerStandLeft = ImageIO.read(playerNameStandLeft);
	    playerStandRight = ImageIO.read(playerNameStandRight);
	} catch(IOException e) {
	    System.out.println(e);
	}

    }
    public void paint(Graphics g) {
	super.paint(g);
	if(model.flag){
	    g.setFont(font);
	    g.setColor(Color.red);
	    g.drawString("Initialisation Error", 300, 100);

	} else {
	drawDesktop(g);
	}
    }
    public void turnPlayer(int t) {
	switch(t){
	    case 39:
		imageGamer = playerStandRight;
	    break;
	    case 37:
		imageGamer = playerStandLeft;
	    break;
	    case 38:
		imageGamer = playerStandBack;
	    break;
	    case 40:
		imageGamer = playerStandFront;
	    break;
       }
    }
    public void drawDesktop (Graphics g) { 
	int startX = 50;
	int startY = 50;
	int x = startX;
	int y = startY;
	int width = 50;
	int height = 50;
	int offset = 0;

	for(int i = 0; i < model.desktop.length; i++){
	    for(int j = 0; j < model.desktop[i].length; j++) {
		if(model.desktop[i][j] == 1) {
			g.drawImage(imageGamer, x, y, width, height, null);
		} else if(model.desktop[i][j] == 2) {
			g.drawImage(brick, x, y, width, height, null);
		} else if(model.desktop[i][j] == 0) {
			g.drawImage(tile, x, y, width, height, null);
		} else if(model.desktop[i][j] == 3) {
			g.drawImage(box, x, y, width, height, null);
		} else if(model.desktop[i][j] == 4) {
			g.drawImage(target, x, y, width, height, null);
		}
		x = x + width + offset;
	}
	    x = startX;
	    y = y + height + offset;
	}
    }	
}