package main;

import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import listener.GetThread;
import listener.Listener;
import main.util.MessageManager;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("CLIENT");
		
		Thread timeout = new Thread(() -> {
			try {
				TimeUnit.MILLISECONDS.sleep(3000);
				System.err.println("It looks like no connection can be established");
				System.exit(1);
			} catch (Exception e) {
			}
		});
		timeout.start();
		
		try{

			Socket server = new Socket(InetAddress.getByName("127.0.0.1"), 9090);
			server.setKeepAlive(true);
			server.setSoTimeout(5000);
			new CommandManager();
			new MessageManager(server.getOutputStream());
			new ViewManager();
			
			Thread listener = new Thread(new Listener(server.getInputStream()));
			Thread getThread = new Thread(new GetThread());
			listener.setDaemon(true);
			getThread.setDaemon(true);
			listener.start();
			getThread.start();
			
			timeout.interrupt();
			
		} catch (Exception e) {
			timeout.interrupt();
			e.printStackTrace();
			return;
		}
	}
}
