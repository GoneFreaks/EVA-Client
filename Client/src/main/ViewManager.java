package main;

import javax.swing.JFrame;

import dto.GameView;
import dto.View;
import dto.WaitingView;

public class ViewManager {

	private JFrame frame;
	private View view;
	
	public ViewManager () {
		frame = initFrame();
	}
	
	public void start () {
		view = new WaitingView();
		view.setFrame(frame, null).start();
	}
	
	public void showData(String input) {
		System.out.println(input);
		String cmd = input.substring(0, 3);
		if(cmd.equals("acc")) {
			setGameView();
		}	
		view.showData(input);
	}
	
	public void setWaitingView() {
		frame = initFrame();
		String id = view.shutdown();
		view = new WaitingView();
		view.setFrame(frame, id).start();
	}
	
	public void setGameView() {
		System.out.println("GAME");
		frame = initFrame();
		String id = view.shutdown();
		view = new GameView();
		view.setFrame(frame, id).start();
		GetThread.run = false;
	}
	
	public JFrame initFrame() {
		JFrame init = new JFrame("EVA");
		init.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init.addWindowListener(new ViewEventListener());
		return init;
	}
	
}
