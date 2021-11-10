package main;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Main {

	private static String[] test = {"del_irgendwas", "get_irgendwas", "new_irgendwas", "ans_irgendwas", "unbekannt"};
	
	public static void main(String[] args) {
		System.out.println("CLIENT");
		for (int i = 0; i < test.length; i++) {
			try {
				
				int port = 9090;
				InetAddress address = InetAddress.getByName("127.0.0.1");
				Socket socket = new Socket(address, port);
				OutputStream out = socket.getOutputStream();
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
				
				writer.write(test[i]);
				writer.flush();
				TimeUnit.SECONDS.sleep(1);
					
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
