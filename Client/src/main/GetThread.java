package main;

import java.util.concurrent.TimeUnit;

public class GetThread implements Runnable {

	public static boolean run = true;
	
	@Override
	public void run() {
		try {
			while(run) {
				TimeUnit.MILLISECONDS.sleep(1000);
				MessageManager.INSTANCE.sendMessage("get");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
