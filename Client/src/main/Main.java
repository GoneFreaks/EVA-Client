package main;

import java.net.InetAddress;
import java.net.Socket;

import main.listener.GetThread;
import main.listener.Listener;
import main.util.MessageManager;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("CLIENT");
		
		try{
			Socket server;
			if((server = new Socket(InetAddress.getByName("127.0.0.1"), 9090)) != null) {
				server.setKeepAlive(true);
				new CommandManager();
				new MessageManager(server.getOutputStream());
				new ViewManager();
				Thread listener = new Thread(new Listener(server.getInputStream()));
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
