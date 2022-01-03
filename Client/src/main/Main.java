package main;

import java.net.InetAddress;
import java.net.Socket;

public class Main {

	private static ViewManager viewMan;
	
	public static void main(String[] args) {
		System.out.println("CLIENT");
		
		try{
			Socket server;
			if((server = new Socket(InetAddress.getByName("127.0.0.1"), 9090)) != null) {
				server.setKeepAlive(true);
				new MessageManager(server.getOutputStream());
				viewMan = new ViewManager();
				viewMan.start();
				Thread listener = new Thread(new Listener(viewMan, server.getInputStream()));
				Thread getThread = new Thread(new GetThread());
				listener.setDaemon(true);
				getThread.setDaemon(true);
				listener.start();
				getThread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
}
