package main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener implements Runnable {
	
	private InputStream in;
	private OutputStream out;
	
	public Listener (InputStream in, OutputStream out) {
		this.in = in;
		this.out = out;
	}
	
	@Override
	public void run() {
		
		try (ServerSocket sock = new ServerSocket(0)) {
			while(true) {
				Socket connection = sock.accept();
				connection.setSoTimeout(0);
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				if((line = reader.readLine()) != null) System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
