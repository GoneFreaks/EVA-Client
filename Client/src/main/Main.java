package main;

import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		System.out.println("CLIENT");
		
		try{
			Socket server;
			if((server = connect()) != null) {
				server.setKeepAlive(true);
				MessageManager.startUp(server.getOutputStream(), server.getInputStream());
				System.out.println(MessageManager.receiveMessage());
				MessageManager.sendMessage("get");
				TimeUnit.SECONDS.sleep(1);
				System.out.println(MessageManager.receiveMessage());
				server.close();
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
