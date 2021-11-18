package main;

import javax.swing.JFrame;

import dto.StartUpDTO;

public class ViewManager {

	private static JFrame frame = new JFrame("Client");
	private static StartUpDTO startUp;
	
	public static void main(String[] args) throws Exception {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new ViewEventListener());
		
		startUp = new StartUpDTO(frame);
		startUp.startUp();
		Thread.sleep(5000);
		startUp.setId("KEVIN");
		for (int i = 0; i < 8; i++) {
			startUp.addInvite("INVITE " + i);
			startUp.addPlayer("PLAYER " + i);
		}
		startUp.enableButtons(true);
	}
}
