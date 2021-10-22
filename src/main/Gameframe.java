package main;

import gameObjects.Board;

public class Gameframe {
	private Board board;
	private int player;
	private int playerTurn;
	
	 public Gameframe() {
		 board=new Board();
		 playerTurn=1;
	 }
	 
	 public void update() {
		 
	 }
	 
	 public Board getBoard() {
		 return board;
	 }
}
