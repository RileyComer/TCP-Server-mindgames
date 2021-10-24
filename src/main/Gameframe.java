package main;

import java.io.IOException;

import gameObjects.Board;
import serverAndClient.Client;
import serverAndClient.OnlineHandler;
import serverAndClient.Server;

public class Gameframe {
	private Board board;
	private OnlineHandler onlineHandler;
	private int player;
	private int turn;
	
	 public Gameframe(int player) {
		 board=new Board();
		 turn=1;
		 this.player=player;
		 
		 //online stuff
		 if(player==1) {
			 onlineHandler=new OnlineHandler("server");
		 }else {
			 onlineHandler=new OnlineHandler("client");
		 }
	 }
	 
	 public void update() {
		 if(player==1) {
			 if(turn%2==1) {
				 //turn stuff
			 }else {
				 int x1;
				 int y1;
				 int x2;
				 int y2;
				 System.out.println("waiting");
				 x1 = onlineHandler.getInt();
				 y1 = onlineHandler.getInt();
				 x2 = onlineHandler.getInt();
				 y2 = onlineHandler.getInt();
				 
				 //online move
				 board.getArray()[x2][y2]=board.getArray()[x1][y1];
				 board.getArray()[x1][y1]=null;
				 System.out.println(x1+" "+y1+" move to "+x2+" "+y2);
				 turn++;
			 }
		 }else {
			 if(turn%2==0) {
				 //turn stuff
			 }else {
				 int x1;
				 int y1;
				 int x2;
				 int y2;
				 System.out.println("waiting");
				 x1 = onlineHandler.getInt();
				 y1 = onlineHandler.getInt();
				 x2 = onlineHandler.getInt();
				 y2 = onlineHandler.getInt();
				 
				 //online move
				 board.getArray()[x2][y2]=board.getArray()[x1][y1];
				 board.getArray()[x1][y1]=null;
				 System.out.println(x1+" "+y1+" move to "+x2+" "+y2);
				 turn++;
			 }
		 }
	 }
	 
	 public Board getBoard() {
		 return board;
	 }

	public void exit() {
		onlineHandler.close();
		
	}
	
	public void move(int x1, int y1, int x2, int y2) {
		System.out.println("Moved");
		board.getArray()[x2][y2]=board.getArray()[x1][y1];
		board.getArray()[x1][y1]=null;
		turn++;
		onlineHandler.writeInt(x1);
		onlineHandler.writeInt(y1);
		onlineHandler.writeInt(x2);
		onlineHandler.writeInt(y2);
	}
}
