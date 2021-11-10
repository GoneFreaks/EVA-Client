package main;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		System.out.println("CLIENT");
		for (int i = 0; i < 5; i++) {
			try {
				TimeUnit.SECONDS.sleep(5);
				
				int port = 9090;
				InetAddress address = InetAddress.getByName("127.0.0.1");
				Socket socket = new Socket(address, port);
				OutputStream out = socket.getOutputStream();
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
				
				String output = "ICH BIN NUMMER " + (i+1);
				writer.write(output);
				writer.flush();
				
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
