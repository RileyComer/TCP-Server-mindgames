package main;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import gameObjects.Board;
import serverAndClient.Client;
import serverAndClient.OnlineHandler;
import serverAndClient.Server;

public class Gameframe {
	private Board board;
	private OnlineHandler onlineHandler;
	private int player;
	private int turn;
	private String pMasterMind;
	private String eMasterMind;
	private int gameState;
	
	private static File turnEnd=new File ("src/resources/turnEnd.WAV");
	private static File kill=new File ("src/resources/kill.WAV");
	
	 public Gameframe(int player) {
		 board=new Board();
		 turn=0;
		 this.player=player;
		 pMasterMind="";
		 eMasterMind="";
		 gameState=0;
		 
		 //online stuff
		 if(player==1) {
			 onlineHandler=new OnlineHandler("server");
		 }else {
			 onlineHandler=new OnlineHandler("client");
		 }
	 }
	 
	 public void update() {
		 if(isTurn()) {
			//turn stuff
		 }else {
			 int x1;
			 int y1;
			 int x2;
			 int y2;
			 x1 = onlineHandler.getInt();
			 y1 = onlineHandler.getInt();
			 x2 = onlineHandler.getInt();
			 y2 = onlineHandler.getInt();
			 
			 if(x1==-1 && y1==-1) {
				 eMasterMind=board.getArray()[x2][y2];
			 }else if(x1==-2 && y1==-2){
				 if(board.getArray()[x2][y2].equals(pMasterMind)) {
					 lose();
				 }
				 board.getArray()[x2][y2]=null;
				 turn++;
				 playSound(kill);
			 }else {
				//online move
				 board.getArray()[x2][y2]=board.getArray()[x1][y1];
				 board.getArray()[x1][y1]=null;
				 if(board.getArray()[x2][y2].equals("RPayload")) {
					 if(y2==board.getArray()[0].length-1) {
						 lose();
					 }
				 }else if(board.getArray()[x2][y2].equals("BPayload")) {
					 if(y2==0) {
						 lose();
					 }
				 }
				 turn++;
				 playSound(turnEnd);
			 }
		 }
	 }
	 
	 public Board getBoard() {
		 return board;
	 }
	 
	 public boolean isTurn() {
		 boolean out=false;
		 if(player==1) {
			 if(turn==0||turn%2==1) {
				 out=true;
			 }
		 }else {
			 if(turn%2==0) {
				 out=true;
			 }
		 }
		 return out;
	 }

	public void exit() {
		onlineHandler.close();
		
	}
	
	public int getPlayer() {
		return player;
	}
	
	public void move(int x1, int y1, int x2, int y2) {
		board.getArray()[x2][y2]=board.getArray()[x1][y1];
		board.getArray()[x1][y1]=null;
		turn++;
		playSound(turnEnd);
		onlineHandler.writeInt(x1);
		onlineHandler.writeInt(y1);
		onlineHandler.writeInt(x2);
		onlineHandler.writeInt(y2);
		
		if(board.getArray()[x2][y2].equals("RPayload")) {
			if(y2==board.getArray()[0].length-1) {
				win();
			}
		}else if(board.getArray()[x2][y2].equals("BPayload")) {
			if(y2==0) {
				win();
			}
		}
	}
	
	public void sendMasterMind(int x2, int y2) {
		pMasterMind=board.getArray()[x2][y2];
		turn=1;
		onlineHandler.writeInt(-1);
		onlineHandler.writeInt(-1);
		onlineHandler.writeInt(x2);
		onlineHandler.writeInt(y2);
	}

	public int getTurn() {
		return turn;
	}

	public String getPMasterMind() {
		return pMasterMind;
	}

	public String getEMasterMind() {
		return eMasterMind;
	}
	
	public void kill(int x, int y) {
		if(board.getArray()[x][y].equals(eMasterMind)) {
			win();
		}
		board.getArray()[x][y]=null;
		turn++;
		playSound(kill);
		onlineHandler.writeInt(-2);
		onlineHandler.writeInt(-2);
		onlineHandler.writeInt(x);
		onlineHandler.writeInt(y);
		
	}
	
	public void win() {
		gameState=1;
	}
	
	public int getGameState() {
		return gameState;
	}
	
	public void lose() {
		gameState=-1;
	}
	
	private static void playSound(File Sound) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();
		}catch(Exception e) {
			
		}
	}
	
}
