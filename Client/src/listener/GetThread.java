package listener;

import java.util.concurrent.TimeUnit;

import util.MessageManager;

public class GetThread implements Runnable {
	
	public static boolean running = true;
	
	@Override
	public void run() {
		try {
			while(running && !Thread.currentThread().isInterrupted()) {
				TimeUnit.MILLISECONDS.sleep(2000);
				MessageManager.INSTANCE.sendMessage("#get");
			}
		} catch (Exception e) {
		}
	}

}
