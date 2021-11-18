package main;

import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("CLIENT");
		
		try{
			Socket server;
			if((server = connect()) != null) {
				server.setKeepAlive(true);
				MessageManager.startUp(server.getOutputStream(), server.getInputStream());
				Thread listener = new Thread(new Listener());
				listener.setDaemon(true);
				listener.start();
				
				Scanner sc = new Scanner(System.in);
				String cmd;
				
				while(true) {
					cmd = sc.next();
					if(cmd.toLowerCase().equals("exit")) break;
					MessageManager.sendMessage(cmd);
				}
				sc.close();
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
