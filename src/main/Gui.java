package main;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Gui extends JFrame{ 
	
	private Display display;
	private Window window;
	private String[] mode;
	private Gameframe gameframe;
	private int selectedX, selectedY;
	
	public Gui(Window window, String[] mode, Gameframe gameframe, String name) {
		super(name);
		this.window=window;
		this.mode=mode;
		this.gameframe=gameframe;
		
		selectedX=-1;
		selectedY=-1;
		
		/*
		File file=new File("C:\\Users\\Riley\\Desktop\\Maps\\Map.png");
		if(file!=null) {
			BufferedImage img=null;
			Color c;
			try {
				img=ImageIO.read(file);
				for(int y=0; y<17; y++) {
					for(int x=0; x<9; x++) {
						c=new Color(img.getRGB(x, y));
						if(!(c.getRed()==255&&c.getBlue()==255&&c.getGreen()==0)) {
							//(save pixel) sheet[x][y]=c;
						}
					}
				}
			}catch(IOException e){
			}
		}
		load();
		*/
		
		display =new Display(this, mode, gameframe);
		add(display,BorderLayout.CENTER);
		
		//Mouse Handler
		HandlerClass handler=new HandlerClass();
		display.addMouseListener(handler);
		display.addMouseMotionListener(handler);
		
		//Exit key
		addKeyListener(
				new KeyAdapter() {
					public void keyPressed(KeyEvent ke) {  // handler
						if(ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
							if(mode[0].equals("Menu")) {
								gameframe.exit();
								System.exit(0);
							}else {
								window.setMode("Menu");
							}
							
						}else if(ke.getKeyCode() == KeyEvent.VK_F) {
							
						}else if(ke.getKeyCode() == KeyEvent.VK_C) {
							
							
						}else if(ke.getKeyCode() == KeyEvent.VK_RIGHT) {
							
							
						}else if(ke.getKeyCode() == KeyEvent.VK_LEFT) {
							
							
						}
					}
				}
		);
	}
	
	public void redraw() {
		display.redraw();
	}
	
	private class HandlerClass implements MouseListener,MouseMotionListener{

		public void mouseClicked(MouseEvent e) {
			
		}

		public void mouseEntered(MouseEvent e) {
			
		}

		public void mouseExited(MouseEvent e) {
			
		}

		public void mousePressed(MouseEvent e) {
			
		}

		public void mouseReleased(MouseEvent e) {
			double x=e.getX();
			double y=e.getY(); 
			int size;
			int startX, startY;
			if(mode[0].equals("Menu")) {
				if((getWidth()/(3))<getHeight()/5) {
					size=getWidth()/(3);
				}else {
					size=getHeight()/(5);
				}
				startX=(int)((getWidth()/2.0)-((size*3)/2.0));
				startY=(int)((getHeight()/2.0)-((size*5)/2.0));
				
				if((x>startX&&x<(startX+(size*3)))&&(y>startY+(size*2)&&y<(startY+(size*5)))) {
					if(y<startY+(size*3)) {
						window.setMode("Mode1");
					}else if(y<startY+(size*4)) {
						window.setMode("Mode2");
					}else if(y<startY+(size*5)) {
						System.exit(0);
					}
				}
			}else if(mode[0].equals("Mode1")) {
				int frameW=gameframe.getBoard().getWidth();
				int frameH=gameframe.getBoard().getHeight();
				if((getWidth()/frameW)<getHeight()/frameH) {
					size=getWidth()/frameW;
				}else {
					size=getHeight()/frameH;
				}
				startX=(int)((getWidth()/2.0)-((size*frameW)/2.0));
				startY=(int)((getHeight()/2.0)-((size*frameH)/2.0));
				if(gameframe.getGameState()==0) {
					if(x>startX && x<startX+size*frameW) {
						if(y>startY && y<startY+size*frameH) {
							int px=(int) (x-startX)/size;
							int py=(int) ((frameH)-((y-startY)/size));
							if(gameframe.getBoard().getArray()[px][py]==null){
								if(selectedX!=-1) {
									if(gameframe.isTurn()) {
										if(gameframe.getBoard().getMoves(selectedX, selectedY)[px][py]) {
											gameframe.move(selectedX, selectedY, px, py);
											selectedX=-1;
											selectedY=-1;
											display.getDisplay1().deselect();
										}
									}
								}
							}else {
								if(gameframe.isTurn()) {
									if(gameframe.getPlayer()==1) {
										if(gameframe.getBoard().getArray()[px][py].contains("R")) {
											if(gameframe.getTurn()==0) {
												gameframe.sendMasterMind(px, py);
											}else {
												selectedX=px;
												selectedY=py;
												display.getDisplay1().select(selectedX, selectedY);
											}
										}else {
											if(selectedX!=-1) {
												if(gameframe.getBoard().getArray()[selectedX][selectedY].equals(gameframe.getPMasterMind())) {
													if(gameframe.getBoard().getVision(selectedX, selectedY)[px][py]) {
														gameframe.kill(px, py);
														selectedX=-1;
														selectedY=-1;
														display.getDisplay1().deselect();
													}
												}
											}
										}
									}else {
										if(gameframe.getBoard().getArray()[px][py].contains("B")) {
											if(gameframe.getTurn()==0) {
												gameframe.sendMasterMind(px, py);
											}else {
												selectedX=px;
												selectedY=py;
												display.getDisplay1().select(selectedX, selectedY);
											}
										}else {
											if(selectedX!=-1) {
												if(gameframe.getBoard().getArray()[selectedX][selectedY].equals(gameframe.getPMasterMind())) {
													if(gameframe.getBoard().getVision(selectedX, selectedY)[px][py]) {
														gameframe.kill(px, py);
														selectedX=-1;
														selectedY=-1;
														display.getDisplay1().deselect();
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		public void mouseMoved(MouseEvent arg0) {
			
		}
		
		public void mouseDragged(MouseEvent e) {
			
		}
	}
	
	/*
	public void save() {
		for(int y=0;y<17;y++) {
			for(int x=0;x<9;x++) {
				if(pixles[x][y]!=null) {
					if(pixles[x][y].equals("B")) {
						sheet[x][y]=new Color(0,0,150);
					}else if(pixles[x][y].equals("R")) {
						sheet[x][y]=new Color(150,0,0);
					}else if(pixles[x][y].equals("G")) {
						sheet[x][y]=new Color(0,150,0);
					}else if(pixles[x][y].equals("M")) {
						sheet[x][y]=new Color(200,200,200);
					}else if(pixles[x][y].equals("F")) {
						sheet[x][y]=new Color(0,50,0);
					}else if(pixles[x][y].equals("W")) {
						sheet[x][y]=new Color(0,200,200);
					}else if(pixles[x][y].equals("Bridge")) {
						sheet[x][y]=new Color(100,80,0);
					}
				}
			}
		}
	}
	
	public void load() {
		for(int y=0;y<17;y++) {
			for(int x=0;x<9;x++) {
				if(sheet[x][y].getRed()==0&&sheet[x][y].getGreen()==0&&sheet[x][y].getBlue()==150) {
					pixles[x][y]="B";
				}else if(sheet[x][y].getRed()==150&&sheet[x][y].getGreen()==0&&sheet[x][y].getBlue()==0) {
					pixles[x][y]="R";
				}else if(sheet[x][y].getRed()==0&&sheet[x][y].getGreen()==150&&sheet[x][y].getBlue()==0) {
					pixles[x][y]="G";
				}else if(sheet[x][y].getRed()==200&&sheet[x][y].getGreen()==200&&sheet[x][y].getBlue()==200) {
					pixles[x][y]="M";
				}else if(sheet[x][y].getRed()==0&&sheet[x][y].getGreen()==50&&sheet[x][y].getBlue()==0) {
					pixles[x][y]="F";
				}else if(sheet[x][y].getRed()==0&&sheet[x][y].getGreen()==200&&sheet[x][y].getBlue()==200) {
					pixles[x][y]="W";
				}else if(sheet[x][y].getRed()==100&&sheet[x][y].getGreen()==80&&sheet[x][y].getBlue()==0) {
					pixles[x][y]="Bridge";
				}
			}
		}
	}
	*/
}
