package util;

import java.io.InputStream;
import java.io.OutputStream;

public class MessageManager {

	public static MessageManager INSTANCE;
	
	private InputStream in;
	private OutputStream out;
	
	public MessageManager(InputStream in, OutputStream out) {
		INSTANCE = this;
		this.in = in;
		this.out = out;
	}
	
	public void sendMessage(String message) {
		byte[] output = message.getBytes();
		try {
			out.write(output);
			out.flush();
		} catch (Exception e) {
			System.err.println("Server not reachable\nIf this problem reoccurs contact the server hoster");
			System.exit(1);
		}
	}
	
	public String[] receiveMessage() {
		try {
			byte[] arr = new byte[in.available()];
			in.read(arr, 0, in.available());
			StringBuilder b = new StringBuilder("");
			for (int i = 0; i < arr.length; i++) {
				b.append((char) arr[i]);
			}
			
			return b.toString().substring(1).split("#");
		} catch (Exception e) {
			return null;
		}
		
	}
	
}
