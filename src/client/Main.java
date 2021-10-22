package client;

import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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
	
	// this method sends a string to the server
	public static void send(DataOutputStream dOut, String content) {
		
		try {
			
			dOut.writeUTF(content);
			dOut.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// this method sends a file to the Server
	public static void sendFile(DataOutputStream dOut, String fileName) {
		try {
			
			// Create Scanner
			File file = new File(fileName);
			Scanner sc = new Scanner(file);
			
			// Read file
			while(sc.hasNextLine()) {
				send(dOut, sc.nextLine());
			}
			
			// Close scanner
			sc.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
