package main;

import javax.swing.JFrame;

import dto.GameView;
import dto.View;
import dto.WaitingView;

public class ViewManager {

	public static ViewManager INSTANCE;
	
	private static JFrame frame;
	private View view;
	private boolean isGame = false;
	
	public ViewManager () {
		INSTANCE = this;
		frame = initFrame();
		view = new WaitingView();
		view.setFrame(frame, null).start();
	}
	
	public boolean isGame() {
		return isGame;
	}
	
	public void enableButtons(boolean state) {
		view.enableButtons(state);
	}
	
	public void showData(String input) {
		view.showData(input);
	}
	
	public void setWaitingView() {
		isGame = false;
		frame = initFrame();
		String id = view.shutdown();
		view = new WaitingView();
		view.setFrame(frame, id).start();
	}
	
	public void setGameView() {
		isGame = true;
		frame = initFrame();
		String id = view.shutdown();
		view = new GameView();
		view.setFrame(frame, id).start();
	}
	
	public JFrame initFrame() {
		JFrame init = new JFrame("EVA");
		init.setResizable(false);
		init.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return init;
	}
	
}
