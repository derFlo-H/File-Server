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
			Socket s = new Socket("localhost", 6666);
			DataInputStream dIn = new DataInputStream(s.getInputStream());
			DataOutputStream dOut = new DataOutputStream(s.getOutputStream());
			
			// user input loop
			boolean running = true;
			Scanner sc = new Scanner(System.in);
			String input = "";
			while(running) {
				
				input = sc.nextLine();
				// Checking if user closes connection
				// and sending it to server
				if(input.equals("close")) {
					send(dOut, "close");
					running = false;
					break;
				} else {
					
					if(input.contains("send")) {
						
						String par[] = input.split(" ");
						System.out.println(par[1]);
						sendFile(dOut, par[1]);
						
					}
					
				}
				
			}
			
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
			
			send(dOut, "0e");
			// Close scanner
			sc.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
