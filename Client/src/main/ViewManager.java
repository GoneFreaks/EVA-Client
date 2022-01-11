package main;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;

import listener.WindowEventListener;
import main.view.GameView;
import main.view.View;
import main.view.WaitingView;
import util.dto.QuestionDTO;

public class ViewManager {

	public static ViewManager INSTANCE;
	
	private static JFrame frame;
	private View view;
	private boolean isGame = false;
	private String id;
	
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
		String data = input.substring(3);
		
		switch (cmd) {
		case "gam": {
		}
		case "ans": {
			String[] args = data.split(",");
			view.showData(new QuestionDTO().ArrayToDTO(args));
			break;
		}
		case "res": {
			List<String> list = Arrays.asList(data.split(",,"));
			if(!list.get(0).startsWith(id)) Collections.reverse(list);
			
			StringBuilder b = new StringBuilder("");
			list.forEach((k) -> {
				String[] temp = k.split(",");
				if(temp[0].equals(id)) b.append("Spieler: " + temp[1] + (b.length() > 0? "":"    "));
				else b.append("Gegner: " + temp[1] + (b.length() > 0? "":"    "));
			});
			view.showData(b.toString());
			break;
		}

		default:
			if(data.startsWith("@") && id == null) id = data;
			view.showData(data);
		}
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
		init.addWindowListener(new WindowEventListener());
		return init;
	}
	
}
