import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

class Model {
    Viewer viewer;
    
    int[][] desktop;
    int[][] targetFour;
    int n;
    int indexI;
    int indexJ;
    int a;
    boolean flag;
//    String playerString;
    
    Model(Viewer viewer) {
	this.viewer = viewer;
	n = 1;
        nextLevel();
    }

    public void move(String direction){
	if(direction.equals("Right")){
	    viewer.playerTurn(39);
	    moveRight();
	} else if(direction.equals("Left")){
	    viewer.playerTurn(37);
	    moveLeft();
	} else if(direction.equals("Down")){
	    viewer.playerTurn(38);
	    moveDown();
	} else if(direction.equals("Up")){
	    viewer.playerTurn(40);
	    moveUp();
	}
        check();
	viewer.updateCanvas();
    }
    private void moveRight() {
	if(desktop[indexI][indexJ + 1] == 3 
	& desktop[indexI][indexJ + 2] == 0) { 	
	    desktop[indexI][indexJ + 1] = 0;
	    desktop[indexI][indexJ + 2] = 3;
	}
	if(desktop[indexI][indexJ + 1] == 3 
	& desktop[indexI][indexJ + 2] == 4) { 	
	    desktop[indexI][indexJ + 1] = 0;
	    desktop[indexI][indexJ + 2] = 3;
	}
	if (desktop[indexI][indexJ + 1] == 0 || desktop[indexI][indexJ + 1] == 4) {
	    desktop[indexI][indexJ] = 0;
	    indexJ = indexJ + 1;
	    desktop[indexI][indexJ] = 1;
	}
    }
    private void moveLeft() {
	if(desktop[indexI][indexJ - 1] == 3 
	& desktop[indexI][indexJ - 2] == 0) { 	
	    desktop[indexI][indexJ - 1] = 0;
	    desktop[indexI][indexJ - 2] = 3;
	} 
	if(desktop[indexI][indexJ - 1] == 3 
	& desktop[indexI][indexJ - 2] == 4) { 	
	    desktop[indexI][indexJ - 1] = 0;
	    desktop[indexI][indexJ - 2] = 3;
	} 
	if (desktop[indexI][indexJ - 1] == 0 || desktop[indexI][indexJ - 1] == 4){
	    desktop[indexI][indexJ] = 0;
	    indexJ = indexJ - 1;
	    desktop[indexI][indexJ] = 1;
	}
    }
    private void moveDown() {
	if(desktop[indexI - 1][indexJ] == 3
	& desktop[indexI - 2][indexJ] == 0) { 	
	    desktop[indexI - 1][indexJ] = 0;
	    desktop[indexI - 2][indexJ] = 3;
	} 
	if(desktop[indexI - 1][indexJ] == 3
	& desktop[indexI - 2][indexJ] == 4) { 	
	    desktop[indexI - 1][indexJ] = 0;
	    desktop[indexI - 2][indexJ] = 3;
	} 
	if (desktop[indexI - 1][indexJ] == 0 || desktop[indexI - 1][indexJ] == 4){
	    desktop[indexI][indexJ] = 0;
	    indexI = indexI - 1;
	    desktop[indexI][indexJ] = 1;
	}
    }
    private void moveUp() {
	if(desktop[indexI + 1][indexJ] == 3 
	& desktop[indexI + 2][indexJ] == 0) { 	
	    desktop[indexI + 1][indexJ] = 0;
	    desktop[indexI + 2][indexJ] = 3;
	} 
	if(desktop[indexI + 1][indexJ] == 3 
	& desktop[indexI + 2][indexJ] == 4) { 	
	    desktop[indexI + 1][indexJ] = 0;
	    desktop[indexI + 2][indexJ] = 3;
	} 
	if (desktop[indexI + 1][indexJ] == 0 
	|| desktop[indexI + 1][indexJ] == 4){
	    desktop[indexI][indexJ] = 0;
	    indexI = indexI + 1;
	    desktop[indexI][indexJ] = 1;
	}
    }
    public void check(){
	for(int j = 0; j < targetFour[0].length; j++) {
		int x = targetFour[0][j];
		int y = targetFour[1][j];
	    if(desktop[x][y] == 0){
		desktop[x][y] = 4;
	    }
	}
	int goal = 0;
	for(int j = 0; j < targetFour[0].length; j++) {
		int x = targetFour[0][j];
		int y = targetFour[1][j];
	    if(desktop[x][y] == 3){
		goal++;
	    }
	    if (goal == a) {
		viewer.updateCanvas();
		viewer.showDialog();
		n++;
		nextLevel();
		viewer.updateCanvas();
	    }
	}
    }
    public void skipLevel() {
        n++;
	nextLevel();
	viewer.updateCanvas();
    }
    public void backLevel() {
        n--;
	nextLevel();
	viewer.updateCanvas();
    }
    public void nextLevel() {
	if (n == 1) {
	    desktop = new int[][]   {
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 3, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 0, 0, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 0, 0, 3, 0, 3, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 0, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 0, 0, 0, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 0, 0, 4, 4, 2},
				{2, 0, 3, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 2},
				{2, 2, 2, 2, 2, 0, 2, 2, 2, 0, 2, 1, 2, 2, 0, 0, 4, 4, 2},
				{2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
				};
	} else if (n == 2) {
	    desktop = new int[][]   {
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0},
				{2, 4, 4, 0, 0, 2, 0, 0, 0, 0, 0, 2, 2, 2},
				{2, 4, 4, 0, 0, 2, 0, 3, 0, 0, 3, 0, 0, 2},
				{2, 4, 4, 0, 0, 2, 3, 2, 2, 2, 2, 0, 0, 2},
				{2, 4, 4, 0, 0, 0, 0, 1, 0, 2, 2, 0, 0, 2},
				{2, 4, 4, 0, 0, 2, 0, 2, 0, 0, 3, 0, 2, 2},
				{2, 2, 2, 2, 2, 2, 0, 2, 2, 3, 0, 3, 0, 2},
				{0, 0, 2, 0, 3, 0, 0, 3, 0, 3, 0, 3, 0, 2},
				{0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2},
				{0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
				};
		viewer.updateCanvas();
	} else if (n == 3) {
	    desktop = new int[][]   {
				{0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 1, 2, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 3, 2, 3, 0, 2, 2, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 3, 0, 0, 3, 2, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 3, 0, 3, 0, 2, 0, 0},
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 3, 0, 2, 0, 2, 2, 2},
				{2, 4, 4, 4, 4, 0, 0, 2, 2, 0, 3, 0, 0, 3, 0, 0, 2},
				{2, 2, 4, 4, 4, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 2},
				{2, 4, 4, 4, 4, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0}
				};
		viewer.updateCanvas();
	} else if (n == 4) {
	    desktop = new int[][]   {
				{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0},
				{2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 2, 2},
				{2, 0, 0, 0, 0, 2, 0, 3, 0, 0, 3, 0, 0, 2},
				{2, 0, 0, 0, 0, 2, 3, 2, 2, 2, 2, 0, 0, 2},
				{2, 0, 0, 0, 0, 0, 0, 1, 0, 2, 2, 0, 0, 2},
				{2, 0, 0, 0, 0, 2, 0, 2, 0, 0, 3, 0, 2, 2},
				{2, 2, 2, 2, 2, 2, 0, 2, 2, 3, 0, 3, 0, 2},
				{0, 0, 2, 0, 3, 0, 0, 3, 0, 3, 0, 3, 0, 2},
				{0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2},
				{0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
				};
		viewer.updateCanvas();
	}
	initialisation();
    }    
    public void undo(){
    }
    public void initialisation(){
	int countOne = 0;
	int countThree = 0;
	int countFour = 0;
        for (int i = 0; i < desktop.length; i++) {
	    for (int j = 0;  j < desktop[i].length; j++){
		if (desktop[i][j] == 1) {
		    indexI = i;
		    indexJ = j;
		    countOne++;
		} 
		if (desktop[i][j] == 3) {
		    countThree++;
		} 
		if (desktop[i][j] == 4) {
		    countFour++;
		}
	    }
	}
	targetFour = new int[2][countFour];
	a = 0;
        for (int i = 0; i < desktop.length; i++) {
	    for (int j = 0;  j < desktop[i].length; j++){
		if(desktop[i][j] == 4) {
			targetFour[0][a] = i;
			targetFour[1][a] = j;
			a++;
		}
	    }
	}
	if ((countThree != countFour) & (countThree == 0 & countFour == 0)) {
	    flag = true;
	}
	if (countOne != 1) {
	    flag = true;
	}
	}
}