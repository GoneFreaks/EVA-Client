package main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class MessageManager {

	private static PrintWriter out;
	private static BufferedReader in;
	
	public static void startUp(OutputStream outStream, InputStream inStream) {
		out = new PrintWriter(outStream, true);
		in = new BufferedReader(new InputStreamReader(inStream));
	}
	
	public static void sendMessage(String message) {
		out.println(message);
	}
	
	public static String receiveMessage() throws Exception {
		StringBuilder b = new StringBuilder("");
		
		String line;
		if((line = in.readLine()) != null) b.append(line + "\n");
		
		return b.toString();
	}
	
}
