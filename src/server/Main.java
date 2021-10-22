package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Main {

	public static void main(String[] args) {
		
		try {
			
			// Create serversocket and accept connection with client
			ServerSocket ss = new ServerSocket(69);
			Socket s = ss.accept();
			
			// Create Data streams
			DataInputStream dIn = new DataInputStream(s.getInputStream());
			DataOutputStream dOut = new DataOutputStream(s.getOutputStream());
			
			// Close data streams and sockets
			dOut.close();
			dIn.close();
			s.close();
			ss.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	// This method recieves a string from the client
	public static String recieve(DataInputStream dIn) {
		String text = "";
		
		text = dIn.readUTF();
		
		return text;
	}

}
