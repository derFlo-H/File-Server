package client;

import java.io.*;
import java.net.*;

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
			
			// Close data streams and socket
			dOut.close();
			dIn.close();
			s.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
