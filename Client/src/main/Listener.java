package main;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class Listener implements Runnable {
	
	private ViewManager viewMan;
	private InputStream in;
	
	public Listener (ViewManager viewMan, InputStream in) {
		this.viewMan = viewMan;
		this.in = in;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				if(in.available() > 0) {
					byte[] arr = new byte[in.available()];
					in.read(arr, 0, in.available());
					StringBuilder b = new StringBuilder("");
					for (int i = 0; i < arr.length; i++) {
						b.append((char) arr[i]);
					}
					viewMan.showData(b.toString());
				}
				TimeUnit.MILLISECONDS.sleep(10);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
