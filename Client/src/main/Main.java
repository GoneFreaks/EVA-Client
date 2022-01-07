package main;

import java.net.InetSocketAddress;
import java.net.Socket;

import listener.GetThread;
import listener.Listener;
import main.util.MessageManager;

public class Main {
	
	private static final String[] SERVER_ADDRESS = {"127.0.0.1", "10.0.3.36"}; 
	
	public static void main(String[] args) {
		System.out.println("CLIENT\n");
		
		for (int i = 0; i < SERVER_ADDRESS.length; i++) {
			String address = SERVER_ADDRESS[i];
			System.out.println("Trying to connect to " + address);
			if(establishConnection(address)) break;
			System.out.println("Unable to connect to " + address + "\n");
		}
	}
	
	private static boolean establishConnection(String server_address){
		try{
			Socket server = new Socket();
			server.connect(new InetSocketAddress(server_address, 9090), 1000);
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
			
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					MessageManager.INSTANCE.sendMessage("#del");
					listener.interrupt();
					try {
						server.close();
					} catch (Exception e) {
					}
				}
			});
			
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}
}
