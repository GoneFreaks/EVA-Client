package main;

import java.io.OutputStream;

public class MessageManager {

	public static MessageManager INSTANCE;
	
	private OutputStream out;
	
	public MessageManager(OutputStream out) {
		INSTANCE = this;
		this.out = out;
	}
	
	public synchronized void sendMessage(String message) {
		byte[] output = message.getBytes();
		try {
			out.write(output);
			out.flush();
		} catch (Exception e) {
			System.exit(1);
		}
	}
	
}
