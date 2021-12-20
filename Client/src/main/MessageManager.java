package main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class MessageManager {

	public static MessageManager INSTANCE;
	
	private PrintWriter out;
	private BufferedReader in;
	
	public MessageManager(OutputStream outStream, InputStream inStream) {
		INSTANCE = this;
		out = new PrintWriter(outStream, true);
		in = new BufferedReader(new InputStreamReader(inStream));
	}
	
	public synchronized void sendMessage(String message) {
		out.println(message);
	}
	
	public String receiveMessage() throws Exception {
		StringBuilder b = new StringBuilder("");
		
		String line;
		while(in.ready() && (line = in.readLine()) != null) b.append(b.length() > 0? "\n" : "" + line);
		
		return b.toString();
	}
	
}
