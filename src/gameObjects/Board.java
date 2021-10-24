package gameObjects;

public class Board {
	private int width=9, height=9;
	private String[][] board;
	private String wBoss, bBoss;
	
	private String[] pieces= {"Payload", "Watcher", "Spy", "Spy", "Assasin", "Temp"};
	
	public Board() {
		board=new String[width][height];
		
		//Team 1
		board[0][0]="wPayload";
		board[2][0]="wSpy1";
		board[4][0]="wWatcher";
		board[4][1]="wAssasin";
		board[6][0]="wSpy2";
		board[8][0]="wTemp";
		
		//Team 2
		board[8][8]="bPayload";
		board[6][8]="bSpy1";
		board[4][8]="bWatcher";
		board[4][7]="bAssasin";
		board[2][8]="bSpy2";
		board[0][8]="bTemp";
		
		
	}
	
	public boolean[][] getVision(int team) {
		return null;
	}
	
	public boolean[][] getVision(int px, int py){
		String piece=board[px][py];
		
		boolean[][] out=new boolean[width][height];
		
		//Vision per piece
		if(piece.contains("Payload")) {
			
		}else if(piece.contains("Spy")) {
			
		}else if(piece.contains("Watcher")) {
			
		}else if(piece.contains("Assasin")) {
			
		}else if(piece.contains("Temp")) {
			
		}
		
		return out;
		
	}
	
	public String[][] getArray() {
		return board;
	}
	
	public void setBoss(int team, String piece) {
		if(team==1) {
			wBoss=piece;
		}else {
			bBoss= piece;
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
}
