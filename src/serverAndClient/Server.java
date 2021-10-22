package serverAndClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static ServerSocket ss;
	private static Socket socket;
	static DataOutputStream out;
	static DataInputStream in;
	
	public Server(int port) throws IOException {
		ss = new ServerSocket(port);
	}
	
	public void start() throws IOException { 
		socket = ss.accept();
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
		out.close();
		in.close();
		socket.close(); 
		ss.close();
	}
	
	public static void main(String[] args) throws IOException {
		Server server = new Server(3000);
		server.start();
		int i=getInt();
		System.out.println(i);
		i=getInt();
		System.out.println(i);
		i=getInt();
		System.out.println(i);
		close();
	}
}
