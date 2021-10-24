package displays;
import java.awt.Color;
import java.awt.Graphics;

import main.Gameframe;
import main.Gui;

public class Mode1Display{
	
	private Gui gui;
	private Gameframe gameframe;
	private int size,startX,startY,frameW,frameH;
	
	//game stuff
	
	public Mode1Display(Gui gui1, Gameframe gameframe){
		gui=gui1;
		this.gameframe=gameframe;
		frameW=gameframe.getBoard().getWidth();
		frameH=gameframe.getBoard().getHeight();
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
		for(int y=0; y< frameH; y++) {
			for(int x=0; x<frameW;x++) {
				if((x+y)%2==0) {
					g.setColor(new Color(75, 75, 75));
				}else {
					g.setColor(new Color(50, 50, 50));
				}
				g.fillRect(startX+size*x, startY+size*((frameH-1)-y), size, size);
			}
		}
		
		//Draw pieces
		String[][] board=gameframe.getBoard().getArray();
		for(int y=0; y< frameH; y++) {
			for(int x=0; x<frameW;x++) {
				if(board[x][y]!=null) {
					if(board[x][y].contains("Spy")) {
						g.setColor(new Color(250, 250, 250));
						g.fillRect(startX+size*x, startY+size*((frameH-1)-y), size, size);
					}else if(board[x][y].contains("Payload")){
						g.setColor(new Color(250, 250, 250));
						g.fillRect(startX+size*x, startY+size*((frameH-1)-y), size, size);
					}else if(board[x][y].contains("Watcher")){
						g.setColor(new Color(250, 250, 250));
						g.fillRect(startX+size*x, startY+size*((frameH-1)-y), size, size);
					}else if(board[x][y].contains("Temp")){
						g.setColor(new Color(250, 250, 250));
						g.fillRect(startX+size*x, startY+size*((frameH-1)-y), size, size);
					}else if(board[x][y].contains("Assasin")){
						g.setColor(new Color(250, 250, 250));
						g.fillRect(startX+size*x, startY+size*((frameH-1)-y), size, size);
					}
				}
			}
		}
	}
}