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
				 int i;
				 System.out.println("waiting");
				 i = onlineHandler.getInt();
				 System.out.println(i);
				 turn++;
			 }
		 }else {
			 if(turn%2==0) {
				 //turn stuff
			 }else {
				 int i;
				 System.out.println("waiting");
				 i = onlineHandler.getInt();
				 System.out.println(i);
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

	public void test() {
		turn++;
		onlineHandler.writeInt(turn);
	}
}
