package listener;

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
					String[] temp = b.toString().substring(1).split("#");
					for (int i = 0; i < temp.length; i++) {
						CommandManager.INSTANCE.performCommand(temp[i].trim());
					}
				}
				TimeUnit.MILLISECONDS.sleep(50);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
