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
				MessageManager.startUp(server.getOutputStream(), server.getInputStream());
				viewMan = new ViewManager();
				viewMan.start();
				Thread listener = new Thread(new Listener(viewMan));
				listener.setDaemon(true);
				listener.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	private static Socket connect() {
		try {
			return new Socket(InetAddress.getByName("127.0.0.1"), 9090);
		} catch (Exception e) {
			return null;
		}
	}
}
