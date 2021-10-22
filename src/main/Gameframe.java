package main;

import java.io.IOException;

import gameObjects.Board;
import serverAndClient.Client;
import serverAndClient.Server;

public class Gameframe {
	private Board board;
	private Server server;
	private Client client;
	private String address;
	private int player;
	private int turn;
	
	 public Gameframe(int player) {
		 board=new Board();
		 turn=1;
		 this.player=player;
		 
		 //online stuff
		 address="142.231.45.161";//public IP
		 try {
			 server=new Server(3000);
			 client=new Client();
			 
			 if(player==1) {
				 server.start();
			 }else {
				 client.start(address, 3000);
			 }
		 } catch (IOException e) {
			 System.out.println("Error Can not connect");
			 e.printStackTrace();
		 }
	 }
	 
	 public void update() {
		 if(player==1) {
			 if(turn%2==1) {
				 //turn stuff
			 }else {
				 int i;
				 try {
					 i = server.getInt();
					 System.out.println(i);
				 } catch (IOException e) {
					 System.out.println("Connection error");
					 e.printStackTrace();
				 }
			 }
		 }else {
			 if(turn%2==0) {
				 //turn stuff
			 }else {
				 int i;
				 try {
					 i = client.getInt();
					 System.out.println(i);
				 } catch (IOException e) {
					 System.out.println("Connection error");
					 e.printStackTrace();
				 }
			 }
		 }
	 }
	 
	 public Board getBoard() {
		 return board;
	 }

	public void exit() throws IOException {
		if(player==1) {
			server.close();
		}else {
			client.close();
		}
		
	}

	public void test() {
		turn++;
	}
}
