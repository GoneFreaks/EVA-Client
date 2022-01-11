package listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import main.CommandManager;

public class MessageListener implements Runnable {
	
	private InputStream in;
	
	public MessageListener (InputStream in) {
		this.in = in;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.currentThread().isInterrupted()) {
				if(in.available() > 0) {
					// Read from stream
					byte[] arr = new byte[in.available()];
					in.read(arr, 0, in.available());
					StringBuilder b = new StringBuilder("");
					for (int i = 0; i < arr.length; i++) {
						b.append((char) arr[i]);
					}
					
					// Multiple commands can appear --> split
					String[] temp = b.toString().substring(1).split("#");
					for (int i = 0; i < temp.length; i++) {
						CommandManager.INSTANCE.performCommand(temp[i].trim());
					}
				}
				TimeUnit.MILLISECONDS.sleep(50);
			}
		} catch (InterruptedException e) {
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
