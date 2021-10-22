package serverAndClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
	static DataInputStream in;
	static DataOutputStream out;
	static Socket socket;
	
	public void start(String address, int port) throws IOException { 
		socket = new Socket(address,  port); 
		in = new DataInputStream(socket.getInputStream()); 
		out = new DataOutputStream(socket.getOutputStream()); 
	}
	
	public static int getInt() throws IOException {
		return in.readInt(); 
	}
	
	public static void writeInt(int i) throws IOException {
		out.writeInt(i);
	}
	
	public static void close() throws IOException {
		in.close();
		out.close();
		socket.close();
	}
	
	public static void main(String[] args) throws IOException { 
		Client client = new Client();
		//public ip address
		client.start("142.231.45.161", 3000); 
		writeInt(2);
		writeInt(4);
		writeInt(3);
		close();
	}
}
