package main.listener;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import main.CommandManager;

public class Listener implements Runnable {
	
	private InputStream in;
	
	public Listener (InputStream in) {
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
					CommandManager.INSTANCE.performCommand(b.toString());
				}
				TimeUnit.MILLISECONDS.sleep(10);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
