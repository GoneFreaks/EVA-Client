package listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import main.CommandManager;
import util.MessageManager;

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
					String[] temp = MessageManager.INSTANCE.receiveMessage();
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
