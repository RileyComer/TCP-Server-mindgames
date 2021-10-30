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
		
		if(gameframe.getPlayer()==1) {
			if(gameframe.isTurn()) {
				g.setColor(new Color(255, 0, 0));
			}else {
				g.setColor(new Color(0, 0, 255));
			}
		}else {
			if(gameframe.isTurn()) {
				g.setColor(new Color(0, 0, 255));
			}else {
				g.setColor(new Color(255, 0, 0));
			}
		}
		if(gameframe.getGameState()==1) {
			g.setColor(new Color(0, 255, 0));
		}else if(gameframe.getGameState()==-1) {
			g.setColor(new Color(255, 0, 255));
		}
		g.fillRect(0, 0, size, size);
		
		//board
		resetVision();
		for(int y=0; y< frameH; y++) {
			for(int x=0; x<frameW;x++) {
				if((x+y)%2==0) {
					if(gameframe.getGameState()!=0||vision[x][y]) {
						g.setColor(new Color(210, 210, 200));
					}else {
						g.setColor(new Color(100, 100, 100));
					}
				}else {
					if(gameframe.getGameState()!=0||vision[x][y]) {
						g.setColor(new Color(80, 80, 70));
					}else {
						g.setColor(new Color(40, 40, 40));
					}
				}
				if(sVision[x][y]) {
					g.setColor(new Color(255, 255, 70));
					if(gameframe.getBoard().getArray()[sx][sy].equals(gameframe.getPMasterMind())) {
						g.setColor(new Color(155, 100, 100));
					}
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
					if(gameframe.getGameState()!=0||vision[x][y]) {
						if(gameframe.getPMasterMind().equals(board[x][y])) {
							g.setColor(new Color(0, 80, 0));
							g.fillRect(startX+size*x, startY+size*((frameH-1)-y), size, size);
						}
						drawSprite(g, x, y);
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
	
	private void drawSprite(Graphics g, int x, int y) {
		String[][] board=gameframe.getBoard().getArray();
		int pxSize= (int) (size/16.0);
		int px=0; 
		int py=0;
		Color teamColor=new Color(0, 0, 0);;
		if(board[x][y].contains("R")) {
			teamColor=new Color(255, 0, 0);
		}else {
			teamColor=new Color(0, 0, 255);
		}
		if(board[x][y].contains("Payload")) {
			Color window=new Color(80, 100, 255);
			Color tire=new Color(20, 20, 20);
			Color rim=new Color(200, 200, 200);
			
			//Line 12
			py=12;
			
			g.setColor(teamColor);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=11;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=12;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 11
			py=11;
			
			g.setColor(window);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=11;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=12;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=13;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 10
			py=10;
			
			g.setColor(window);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=11;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=12;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=13;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 9
			py=9;
			
			g.setColor(teamColor);
			
			px=3;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=4;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=11;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=12;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=13;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 8
			py=8;
			
			g.setColor(teamColor);
			
			px=2;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=3;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=4;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=11;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=12;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=13;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 7
			py=7;
			
			g.setColor(teamColor);
			
			px=2;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(tire);
			
			px=3;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=4;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(tire);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=11;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=12;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=13;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 6
			py=6;
			
			g.setColor(teamColor);
			
			px=2;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(tire);
			
			px=3;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(rim);
			
			px=4;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(tire);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(tire);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(rim);
			
			px=11;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(tire);
			
			px=12;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=13;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 5
			py=5;
			
			g.setColor(tire);
			
			px=3;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=4;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=11;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=12;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
		}else if(board[x][y].contains("Spy")) {
			Color suit=new Color(50, 45, 40);
			
			//Line 13
			py=13;
			
			g.setColor(suit);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 12
			py=12;
			
			g.setColor(suit);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(suit);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(suit);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 11
			py=11;
			
			g.setColor(suit);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 10
			py=10;
			
			g.setColor(suit);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 9
			py=9;
			
			g.setColor(suit);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 8
			py=8;
			
			g.setColor(suit);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 7
			py=7;
			
			g.setColor(suit);
			
			px=4;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=11;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 6
			py=6;
			
			g.setColor(teamColor);
			
			px=3;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=4;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(suit);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=11;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=12;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 5
			py=5;
			
			g.setColor(teamColor);
			
			px=4;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(suit);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=11;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 4
			py=4;
			
			g.setColor(suit);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 3
			py=3;
			
			g.setColor(suit);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 2
			py=2;
			
			g.setColor(suit);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 1
			py=1;
			
			g.setColor(suit);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 0
			py=0;
			
			g.setColor(suit);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
		}else if(board[x][y].contains("Assasin")) {
			Color suit=new Color(90, 0, 90);
			Color handle=new Color(20, 20, 20);
			Color blade=new Color(150, 150, 150);
			
			//Line 14
			py=14;
			
			g.setColor(blade);
			
			px=12;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 13
			py=13;
			
			g.setColor(suit);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(blade);
			
			px=12;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 12
			py=12;
			
			g.setColor(suit);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(suit);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(suit);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(blade);
			
			px=12;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 11
			py=11;
			
			g.setColor(suit);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(blade);
			
			px=12;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 10
			py=10;
			
			g.setColor(suit);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(blade);
			
			px=12;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 9
			py=9;
			
			g.setColor(suit);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(blade);
			
			px=12;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 8
			py=8;
			
			g.setColor(suit);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(blade);
			
			px=12;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 7
			py=7;
			
			g.setColor(suit);
			
			px=4;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=11;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(handle);
			
			px=12;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 6
			py=6;
			
			g.setColor(teamColor);
			
			px=3;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=4;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(suit);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=11;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=12;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 5
			py=5;
			
			g.setColor(teamColor);
			
			px=4;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(suit);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=12;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 4
			py=4;
			
			g.setColor(suit);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 3
			py=3;
			
			g.setColor(suit);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 2
			py=2;
			
			g.setColor(suit);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 1
			py=1;
			
			g.setColor(suit);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 0
			py=0;
			
			g.setColor(suit);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
		}else if(board[x][y].contains("Detective")) {
			Color skin=new Color(200, 179, 142);
			Color shirt=new Color(200, 200, 200);
			Color pants=new Color(50, 45, 40);
			
			//Line 13
			py=13;
			
			g.setColor(teamColor);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 12
			py=12;
			
			g.setColor(teamColor);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 11
			py=11;
			
			g.setColor(skin);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(skin);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 10
			py=10;
			
			g.setColor(skin);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 9
			py=9;
			
			g.setColor(skin);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 8
			py=8;
			
			g.setColor(teamColor);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(shirt);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 7
			py=7;
			
			g.setColor(teamColor);
			
			px=4;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(shirt);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=11;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 6
			py=6;
			
			g.setColor(teamColor);
			
			px=4;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(shirt);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=11;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 5
			py=5;
			
			g.setColor(teamColor);
			
			px=4;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(shirt);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=11;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 4
			py=4;
			
			g.setColor(teamColor);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(pants);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 3
			py=3;
			
			g.setColor(teamColor);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 2
			py=2;
			
			g.setColor(teamColor);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 1
			py=1;
			
			g.setColor(pants);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 0
			py=0;
			
			g.setColor(pants);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
		}else if(board[x][y].contains("Hacker")) {
			Color robe=new Color(20, 20, 20);
			Color mask=new Color(40, 40, 40);
			Color computer=new Color(60, 60, 60);
			
			//Line 13
			py=13;
			
			g.setColor(robe);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 12
			py=12;
			
			g.setColor(robe);
			
			px=4;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 11
			py=11;
			
			g.setColor(robe);
			
			px=3;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=4;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(mask);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(robe);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 10
			py=10;
			
			g.setColor(robe);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(mask);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(robe);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 9
			py=9;
			
			g.setColor(robe);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(mask);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(robe);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 8
			py=8;
			
			g.setColor(robe);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			//Line 7
			py=7;
			
			g.setColor(robe);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(computer);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(computer);
			
			px=11;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=12;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(computer);
			
			px=13;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 6
			py=6;
			
			g.setColor(robe);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(computer);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=11;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=12;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(computer);
			
			px=13;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 5
			py=5;
			
			g.setColor(robe);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(computer);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(computer);
			
			px=11;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(teamColor);
			
			px=12;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(computer);
			
			px=13;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 4
			py=4;
			
			g.setColor(robe);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			g.setColor(computer);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=11;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=12;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=13;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 3
			py=3;
			
			g.setColor(robe);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=7;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=8;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
			//Line 2
			py=2;
			
			g.setColor(robe);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			//Line 1
			py=1;
			
			g.setColor(robe);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			//Line 0
			py=0;
			
			g.setColor(robe);
			
			px=5;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=6;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=9;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			px=10;
			g.fillRect(startX+size*x+pxSize*px, startY+size*((frameH-1)-y)+pxSize*(16-py), pxSize, pxSize);
			
			
		}
		
		
	}
}