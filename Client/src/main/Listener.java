package main;

import java.util.concurrent.TimeUnit;

public class Listener implements Runnable {
	
	@Override
	public void run() {
		try {
			while(true) {
				String out = MessageManager.receiveMessage();
				System.out.print(out);
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
