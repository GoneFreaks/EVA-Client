package client;

import java.net.InetSocketAddress;
import java.net.Socket;

import client.listener.GetThread;
import client.listener.MessageListener;
import client.util.Filter;
import client.util.MessageManager;

public class Main {
	
	public static final boolean FILTER_OUTPUT = false;
	private static final String[] SERVER_ADDRESS = {"127.0.0.1", "10.0.3.36"}; 
	
	public static void main(String[] args) {
		System.out.println("CLIENT\n");
		
		Filter.filterOutputStreams();
		
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
			MessageManager.setStreams(server.getInputStream(), server.getOutputStream());
			ViewManager.initialize();
			
			// Listener for incoming messages
			Thread messageListener = new Thread(new MessageListener(server.getInputStream()));
			messageListener.setDaemon(true);
			messageListener.start();
			
			// Retrieve new data from the server
			Thread getThread = new Thread(new GetThread());
			getThread.setDaemon(true);
			getThread.start();
			
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					messageListener.interrupt();
					getThread.interrupt();
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
