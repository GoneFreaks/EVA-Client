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
		frame = initFrame();
		String id = view.shutdown();
		view = new GameView();
		view.setFrame(frame, id).start();
	}
	
	public JFrame initFrame() {
		JFrame init = new JFrame("EVA");
		init.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init.addWindowListener(new ViewEventListener());
		return init;
	}
	
}
