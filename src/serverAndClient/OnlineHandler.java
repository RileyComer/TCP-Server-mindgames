package serverAndClient;

import java.io.IOException;

public class OnlineHandler {
	private Server server;
	private Client client;
	private String address;
	private String type;
	
	public OnlineHandler(String type) {
		this.type=type;
		//online stuff
		 address="142.231.43.161";//public IP
		 try {
			 if(type.equals("server")) {
				 server=new Server(3000);
				 server.start();
				 System.out.println("Server started");
			 }else {
				 client=new Client();
				 client.start(address, 3000);
				 System.out.println("client connected");
			 }
		 } catch (IOException e) {
			 System.out.println("Error Can not connect");
			 e.printStackTrace();
		 }
	}

	public int getInt() {
		int out=0;
		try {
			if(type.equals("server")) {
				out=server.getInt();
			}else {
				out=client.getInt();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}

	public void close() {
		try {
			if(type.equals("server")) {
				server.close();
			}else {
				client.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void writeInt(int i) {
		try {
			if(type.equals("server")) {
				server.writeInt(i);
			}else {
				client.writeInt(i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
