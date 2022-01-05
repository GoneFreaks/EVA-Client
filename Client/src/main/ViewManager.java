package main;

import javax.swing.JFrame;

import dto.GameView;
import dto.View;
import dto.WaitingView;
import main.listener.GetThread;
import main.listener.ViewEventListener;
import main.util.MessageManager;

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
		String cmd = input.substring(0, 3);
		view.showData(input);
		if(cmd.equals("acc")) {
			setGameView();
		}
		if(cmd.equals("res")) {
			MessageManager.INSTANCE.sendMessage("new");
			setWaitingView();
		}
		if(cmd.equals("unl")) {
			view.enableButtons(true);
		}
	}
	
	public void setWaitingView() {
		isGame = false;
		frame = initFrame();
		String id = view.shutdown();
		view = new WaitingView();
		view.setFrame(frame, id).start();
		
		Thread getThread = new Thread(new GetThread());
		GetThread.running = true;
		getThread.setDaemon(true);
		getThread.start();
	}
	
	public void setGameView() {
		isGame = true;
		frame = initFrame();
		String id = view.shutdown();
		view = new GameView();
		view.setFrame(frame, id).start();
		GetThread.running = false;
	}
	
	public JFrame initFrame() {
		JFrame init = new JFrame("EVA");
		init.setResizable(false);
		init.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init.addWindowListener(new ViewEventListener());
		return init;
	}
	
}
