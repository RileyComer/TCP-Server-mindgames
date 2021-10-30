package gameObjects;

public class Board {
	private int width=13, height=12;
	private String[][] board;
	private String wBoss, bBoss;
	
	private String[] pieces= {"Payload", "Watcher", "Spy", "Spy", "Assasin", "Temp"};
	
	public Board() {
		board=new String[width][height];
		
		//Team 1
		board[0][0]="RPayload";
		board[3][0]="RSpy1";
		board[6][0]="RHacker";
		board[6][7]="RAssasin";
		board[9][0]="RSpy2";
		board[12][0]="RDetective";
		
		//Team 2
		board[12][11]="BPayload";
		board[9][11]="BSpy1";
		board[6][11]="BHacker";
		board[6][4]="BAssasin";
		board[3][11]="BSpy2";
		board[0][11]="BDetective";
		
		
	}
	
	public boolean[][] getVision(int team) {
		boolean[][] out=new boolean[width][height];
		if(team==1) {
			for(int x=0; x<width; x++) {
				for(int y=0; y<height; y++) {
					if(board[x][y]!=null && board[x][y].contains("R")) {
						out=addVision(getVision(x, y), out);
					}
				}
			}
		}else {
			for(int x=0; x<width; x++) {
				for(int y=0; y<height; y++) {
					if(board[x][y]!=null && board[x][y].contains("B")) {
						out=addVision(getVision(x, y), out);
					}
				}
			}
		}
		return out;
	}
	
	public boolean[][] addVision(boolean[][] in1, boolean[][] in2){
		boolean[][] out=new boolean[width][height];
		for(int x=0; x<in1.length; x++) {
			for(int y=0; y<in1[0].length;y++) {
				if(in1[x][y]||in2[x][y]) {
					out[x][y]=true;
				}
			}
		}
		return out;
	}
	
	public boolean[][] getMoves(int px, int py){
		String piece=board[px][py];
		
		boolean[][] out=new boolean[width][height];
		int ox=0;
		int oy=0;
		
		if(piece.contains("Payload")) {
			ox=0;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=1;
			oy=1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=1;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=1;
			oy=-1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=0;
			oy=-1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-1;
			oy=-1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-1;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-1;
			oy=1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=0;
			oy=1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
		}else if(piece.contains("Spy")) {
			ox=0;
			oy=1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=1;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=0;
			oy=-1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-1;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
		}else if(piece.contains("Hacker")) {
			ox=0;
			oy=1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=1;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=0;
			oy=-1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-1;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
		}else if(piece.contains("Assasin")) {
			ox=1;
			oy=2;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-1;
			oy=2;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=2;
			oy=1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=2;
			oy=-1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=1;
			oy=-2;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-1;
			oy=-2;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-2;
			oy=-1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-2;
			oy=1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
		}else if(piece.contains("Detective")) {
			ox=0;
			oy=1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=1;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=0;
			oy=-1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-1;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
		}
		
		return out;
	}
	
	public boolean[][] getVision(int px, int py){
		String piece=board[px][py];
		
		boolean[][] out=new boolean[width][height];
		//offsets
		int ox=0;
		int oy=0;
		//Vision per piece
		if(piece.contains("Payload")) {
			ox=0;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=1;
			oy=1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=1;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=1;
			oy=-1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=0;
			oy=-1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-1;
			oy=-1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-1;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-1;
			oy=1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=0;
			oy=1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			
		}else if(piece.contains("Spy")) {
			ox=0;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=0;
			oy=1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=0;
			oy=2;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=0;
			oy=3;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=1;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=2;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=3;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=0;
			oy=-1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=0;
			oy=-2;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=0;
			oy=-3;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-1;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-2;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-3;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			
		}else if(piece.contains("Hacker")) {
			ox=0;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=0;
			oy=1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=1;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=0;
			oy=-1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-1;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=1;
			oy=1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=2;
			oy=2;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=3;
			oy=3;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=1;
			oy=-1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=2;
			oy=-2;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=3;
			oy=-3;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-1;
			oy=-1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-2;
			oy=-2;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-3;
			oy=-3;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-1;
			oy=1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-2;
			oy=2;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-3;
			oy=3;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			
			
		}else if(piece.contains("Assasin")) {
			ox=0;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=0;
			oy=1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=0;
			oy=2;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=1;
			oy=2;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-1;
			oy=2;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=1;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=2;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=2;
			oy=1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=2;
			oy=-1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=0;
			oy=-1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=0;
			oy=-2;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=1;
			oy=-2;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-1;
			oy=-2;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-1;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-2;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-2;
			oy=1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-2;
			oy=-1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			
		}else if(piece.contains("Detective")) {
			ox=0;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=1;
			oy=1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=1;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=1;
			oy=-1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=0;
			oy=-1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-1;
			oy=-1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-1;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-1;
			oy=1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=0;
			oy=1;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=2;
			oy=2;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=2;
			oy=-2;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-2;
			oy=-2;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-2;
			oy=2;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=0;
			oy=2;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=2;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=0;
			oy=-2;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
			ox=-2;
			oy=0;
			if(checkInbounds(px, py, ox, oy)) {
				out[px+ox][oy+py]=true;
			}
		}
		
		return out;
		
	}
	
	public boolean checkInbounds(int x, int y, int ox, int oy) {
		boolean out=true;
		
		if(x+ox>=width) {
			out=false;
		}
		if(x+ox<0) {
			out=false;
		}
		if(y+oy>=height) {
			out=false;
		}
		if(y+oy<0) {
			out=false;
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
