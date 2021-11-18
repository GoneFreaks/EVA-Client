package dto;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameView implements View{

	private List<JButton> buttons;
	private JLabel question;
	private JFrame frame;
	
	public GameView () {
		this.buttons = new ArrayList<>();
	}
	
	public void start() {
		frame.setLayout(new GridLayout(4, 2));
		for (int i = 1; i < 8; i++) {
			if(i < 4) {
				if(i == 2) {
					question = new JLabel("FRAGE");
					frame.add(question);
				}
				else frame.add(new JLabel(""));
			}
			else {
				JButton button = new JButton("ANTWORT " + ((i % 4) + 1));
				buttons.add(button);
				frame.add(button);
			}
		}
		
		frame.setSize(600, 400);
		frame.setVisible(true);
	}
	
	public void enableButtons(boolean state) {
		for (JButton i : buttons) i.setEnabled(state);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("EVA");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new GameView().setFrame(frame, null).start();
	}

	@Override
	public String shutdown() {
		return null;
	}

	@Override
	public View setFrame(JFrame frame, String id) {
		this.frame = frame;
		this.frame.add(new JLabel(id));
		return this;
	}

	@Override
	public void showData(String input) {
		
	}
	
}
