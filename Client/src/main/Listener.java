package main;

import java.util.concurrent.TimeUnit;

public class Listener implements Runnable {
	
	private ViewManager viewMan;
	
	public Listener (ViewManager viewMan) {
		this.viewMan = viewMan;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				String out = MessageManager.receiveMessage();
				if(out.length() > 0) viewMan.showData(out);
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
