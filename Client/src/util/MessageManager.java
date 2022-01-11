package util;

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
			System.err.println("Server not reachable\nIf this problem reoccurs contact the server hoster");
			System.exit(1);
		}
	}
	
}
