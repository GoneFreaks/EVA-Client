package main;

import java.net.InetAddress;
import java.net.Socket;

public class Main {

	private static ViewManager viewMan;
	
	public static void main(String[] args) {
		System.out.println("CLIENT");
		
		try{
			Socket server;
			if((server = connect()) != null) {
				server.setKeepAlive(true);
				new MessageManager(server.getOutputStream(), server.getInputStream());
				viewMan = new ViewManager();
				viewMan.start();
				Thread listener = new Thread(new Listener(viewMan));
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
	
	private static Socket connect() {
		try {
			return new Socket(InetAddress.getByName("192.168.178.48"), 9090);
		} catch (Exception e) {
			return null;
		}
	}
}
