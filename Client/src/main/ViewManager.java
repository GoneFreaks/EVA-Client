package main;

import javax.swing.JFrame;

import dto.StartUpDTO;

public class ViewManager {

	private JFrame frame;
	private StartUpDTO startUp;
	
	public ViewManager () {
		frame = new JFrame("EVA");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new ViewEventListener());
	}
	
	public void start () {
		startUp = new StartUpDTO(frame);
		startUp.startUp();
	}
	
	public void showData(String input) {
		startUp.showData(input);
	}
	
}
