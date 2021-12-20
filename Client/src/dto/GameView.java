package dto;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import main.MessageManager;

public class GameView implements View{

	private List<JButton> buttons;
	private JLabel question;
	private JFrame frame;
	private QuestionDTO current;
	private String id;
	
	private JLabel result_label;
	
	public GameView () {
		this.buttons = new ArrayList<>();
		this.current = new QuestionDTO();
	}
	
	public void start() {
		frame.setLayout(new GridLayout(4, 2));
		for (int i = 1; i < 8; i++) {
			if(i < 4) {
				if(i == 2) {
					question = new JLabel("FRAGE");
					frame.add(question);
				}
				else if(i == 1) {
					result_label = new JLabel("");
					frame.add(result_label);
				}
				else frame.add(new JLabel(""));
			}
			else {
				JButton button = new JButton("ANTWORT " + ((i % 4) + 1));
				button.addActionListener((a) -> handleAction(a));
				buttons.add(button);
				frame.add(button);
			}
		}
		
		frame.setSize(600, 400);
		frame.setVisible(true);
		MessageManager.INSTANCE.sendMessage("gam");
	}
	
	private void handleAction(ActionEvent a) {
		JButton pressed = (JButton) a.getSource();
		String answer = pressed.getText();
		pressed.setBackground(current.isCorrect(answer)? Color.GREEN : Color.RED);
		setCorrectAnswer();
		enableButtons(false);
		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(5);
				MessageManager.INSTANCE.sendMessage("ans" + (current.isCorrect(answer)? 1:0));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	private void setCorrectAnswer() {
		for (JButton i : buttons) {
			if(current.isCorrect(i.getText())) i.setBackground(Color.GREEN);
		}
	}

	public void enableButtons(boolean state) {
		for (JButton i : buttons) i.setEnabled(state);
	}
	
	public void resetButtonColor() {
		for (JButton i : buttons) i.setBackground(Color.WHITE);
	}

	@Override
	public String shutdown() {
		frame.setVisible(false);
		frame.removeAll();
		return id;
	}

	@Override
	public View setFrame(JFrame frame, String id) {
		this.frame = frame;
		this.frame.add(new JLabel(id));
		this.id = id;
		return this;
	}

	@Override
	public void showData(String input) {
		if(input.startsWith("get")) return;
		else {
			if(input.startsWith("gam") || input.startsWith("ans")) {
				input = input.substring(3);
				String[] args = input.split(",");
				enableButtons(true);
				resetButtonColor();
				List<Integer> random_order = new ArrayList<>();
				random_order.add(0);
				random_order.add(1);
				random_order.add(2);
				random_order.add(3);
				Collections.shuffle(random_order);
				current.StringToDTO(args);
				for (int i = 0; i < args.length; i++) {
					if(i == 0) question.setText(args[i]);
					else buttons.get(random_order.remove(0)).setText(args[i]);
				}	
			}
			if(input.startsWith("res")) {
				input = input.substring(3);
				String[] args = input.split(",,");
				StringBuilder b = new StringBuilder("");
				for (int i = 0; i < args.length; i++) {
					String[] temp = args[i].split(",");
					if(temp[0].equals(id)) b.append("Spieler: " + temp[1] + (b.length() > 0? "":"    "));
					else b.append("Gegner: " + temp[1] + (b.length() > 0? "":"    "));
				}
				result_label.setText(b.toString());
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
