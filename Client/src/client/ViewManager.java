package client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;

import client.listener.WindowEventListener;
import client.util.dto.QuestionDTO;
import client.view.GameView;
import client.view.View;
import client.view.WaitingView;

public class ViewManager {
	
	private static JFrame frame;
	private static View view;
	private static boolean isGame = false;
	private static String id;
	
	public static void initialize () {
		frame = initFrame();
		view = new WaitingView();
		view.setFrame(frame, null).start();
	}
	
	public static boolean isGame() {
		return isGame;
	}
	
	public static void enableButtons(boolean state) {
		view.enableButtons(state);
	}
	
	public static void showData(String input) {
		
		String cmd = input.substring(0, 3);
		String data = input.substring(3);
		
		switch (cmd) {
		case "gam": {
		}
		case "ans": {
			String[] args = data.split(",");
			view.showData(new QuestionDTO(args));
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
	
	public static void setWaitingView() {
		isGame = false;
		frame = initFrame();
		String id = view.shutdown();
		view = new WaitingView();
		view.setFrame(frame, id).start();
	}
	
	public static void setGameView() {
		isGame = true;
		frame = initFrame();
		String id = view.shutdown();
		view = new GameView();
		view.setFrame(frame, id).start();
	}
	
	private static JFrame initFrame() {
		JFrame init = new JFrame("EVA");
		init.setResizable(false);
		init.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init.addWindowListener(new WindowEventListener());
		return init;
	}
	
}
