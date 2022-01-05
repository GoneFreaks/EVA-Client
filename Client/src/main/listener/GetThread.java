package main.listener;

import java.util.concurrent.TimeUnit;

import main.util.MessageManager;

public class GetThread implements Runnable {
	
	public static boolean running = true;
	
	@Override
	public void run() {
		try {
			while(running) {
				TimeUnit.MILLISECONDS.sleep(2000);
				MessageManager.INSTANCE.sendMessage("get");
			}
		} catch (Exception e) {
		}
	}

}
