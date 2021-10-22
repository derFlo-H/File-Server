package client;

import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

/*
 * Client for file server
 */

public class Main {

	public static void main(String[] args) {
		
		try {
			
			// Create Socket and Data streams
			Socket s = new Socket("localhost", 69);
			DataInputStream dIn = new DataInputStream(s.getInputStream());
			DataOutputStream dOut = new DataOutputStream(s.getOutputStream());
			
			// Send string to server
			dOut.writeUTF("hi server");
			dOut.flush();
			
			// Close data streams and socket
			dOut.close();
			dIn.close();
			s.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
