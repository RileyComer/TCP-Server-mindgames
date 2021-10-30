package displays;
import java.awt.Color;
import java.awt.Graphics;

import main.Gameframe;
import main.Gui;

public class Mode1Display{
	
	private Gui gui;
	private Gameframe gameframe;
	private int size,startX,startY,frameW,frameH;
	private int sx, sy;
	private boolean[][] vision;
	private boolean[][] sVision;
	private boolean[][] sMove;
	
	//game stuff
	
	public Mode1Display(Gui gui1, Gameframe gameframe){
		gui=gui1;
		this.gameframe=gameframe;
		frameW=gameframe.getBoard().getWidth();
		frameH=gameframe.getBoard().getHeight();
		resetVision();
		deselect();
	}
	
	public void render(Graphics g) {
		if((gui.getWidth()/frameW)<gui.getHeight()/frameH) {
			size=gui.getWidth()/frameW;
		}else {
			size=gui.getHeight()/frameH;
		}
		startX=(int)((gui.getWidth()/2.0)-((size*frameW)/2.0));
		startY=(int)((gui.getHeight()/2.0)-((size*frameH)/2.0));
		
		//board
		resetVision();
		for(int y=0; y< frameH; y++) {
			for(int x=0; x<frameW;x++) {
				if((x+y)%2==0) {
					if(vision[x][y]) {
						g.setColor(new Color(210, 210, 200));
					}else {
						g.setColor(new Color(100, 100, 100));
					}
				}else {
					if(vision[x][y]) {
						g.setColor(new Color(80, 80, 70));
					}else {
						g.setColor(new Color(40, 40, 40));
					}
				}
				if(sVision[x][y]) {
					g.setColor(new Color(255, 255, 70));
					if(sMove[x][y]) {
						g.setColor(new Color(70, 255, 70));
					}
				}
				g.fillRect(startX+size*x, startY+size*((frameH-1)-y), size, size);
			}
		}
		
		//Draw pieces
		String[][] board=gameframe.getBoard().getArray();
		
		for(int y=0; y< frameH; y++) {
			for(int x=0; x<frameW;x++) {
				if(board[x][y]!=null) {
					if(vision[x][y]) {
						if(board[x][y].contains("R")) {
							g.setColor(new Color(250, 0, 0));
						}else {
							g.setColor(new Color(0, 0, 250));
						}
						if(board[x][y].contains("Spy")) {
							g.fillRect(startX+size*x, startY+size*((frameH-1)-y), size, size);
						}else if(board[x][y].contains("Payload")){
							g.fillRect(startX+size*x, startY+size*((frameH-1)-y), size, size);
						}else if(board[x][y].contains("Hacker")){
							g.fillRect(startX+size*x, startY+size*((frameH-1)-y), size, size);
						}else if(board[x][y].contains("Detective")){
							g.fillRect(startX+size*x, startY+size*((frameH-1)-y), size, size);
						}else if(board[x][y].contains("Assasin")){
							g.fillRect(startX+size*x, startY+size*((frameH-1)-y), size, size);
						}
					}
				}
			}
		}
	}
	
	public void resetVision() {
		vision=gameframe.getBoard().getVision(gameframe.getPlayer());
	}
	
	public void select(int x, int y) {
		sx=x;
		sy=y;
		sVision=gameframe.getBoard().getVision(sx, sy);
		sMove=gameframe.getBoard().getMoves(sx, sy);
	}
	
	public void deselect() {
		sx=-1;
		sy=-1;
		sVision=new boolean[frameW][frameH];
		sMove=new boolean[frameW][frameH];
	}
}