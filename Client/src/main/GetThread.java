package main;

import java.util.concurrent.TimeUnit;

public class GetThread implements Runnable {

	public static boolean running = true;
	
	@Override
	public void run() {
		try {
			while(running) {
				TimeUnit.MILLISECONDS.sleep(1000);
				MessageManager.INSTANCE.sendMessage("get");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
