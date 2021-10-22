package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		
		try {
			
			// Create serversocket and accept connection with client
			ServerSocket ss = new ServerSocket(6666);
			Socket s = ss.accept();
			
			// Create Data streams
			DataInputStream dIn = new DataInputStream(s.getInputStream());
			DataOutputStream dOut = new DataOutputStream(s.getOutputStream());
			
			recieveFile(dIn, "b.txt");
			
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
		
		try {
			text = dIn.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return text;
	}
	
	// This method recieves strings and puts them together into a file
	public static void recieveFile(DataInputStream dIn, String fileName) {
		try {
			
			File file = new File(fileName);
			
			if(file.createNewFile()) {
				System.out.println("created new file");
			}
			FileWriter fw = new FileWriter(file);
			
			String text = "";
			boolean run = true;
			while(run) {
				
				text = recieve(dIn);
				if(text.equals("0e")) {run = false; break;}
				fw.write(text);
				
			}
			
			fw.flush();
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
