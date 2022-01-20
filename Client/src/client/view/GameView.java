package client.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import client.util.MessageManager;
import client.util.dto.QuestionDTO;

public class GameView implements View{

	private List<JButton> buttons;
	private JTextArea question;
	private JFrame frame;
	private QuestionDTO current;
	private String id;
	
	private JLabel result_label;
	
	public GameView () {
		this.buttons = new ArrayList<>();
		this.wrong = 0;
		this.correct = 0;
	}
	
	public void start() {
		frame.setLayout(new GridLayout(4, 2));
		for (int i = 1; i < 8; i++) {
			if(i < 4) {
				if(i == 2) {
					question = new JTextArea("FRAGE");
					question.setEditable(false);
					question.setLineWrap(true);
					question.setFont(question.getFont().deriveFont(20f));
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
		
		frame.setSize(900, 600);
		frame.setVisible(true);
		MessageManager.sendMessage("#gam");
	}
	
	private int correct;
	private int wrong;
	private void handleAction(ActionEvent a) {
		JButton pressed = (JButton) a.getSource();
		String answer = pressed.getText();
		pressed.setBackground(current.isCorrect(answer)? Color.GREEN : Color.RED);
		if(current.isCorrect(answer)) correct++;
		else wrong++;
		result_label.setText("Richtig: " + correct + " " + "Falsch:" + wrong);
		setCorrectAnswer();
		enableButtons(false);
		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(5);
				MessageManager.sendMessage("#ans" + (current.isCorrect(answer)? 1:0));
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
		result_label.setText(input);
		try {
			TimeUnit.SECONDS.sleep(7);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void showData(QuestionDTO questionDTO) {
		
		enableButtons(true);
		resetButtonColor();
		current = questionDTO;
		
		question.setText(questionDTO.getQuestion());
		List<String> answers = current.getRandomOrder();
		for (int i = 0; i < 4; i++) {
			JButton temp = buttons.get(i);
			temp.setFont(temp.getFont().deriveFont(16f));
			temp.setText(answers.get(i));
		}
	}
	
}
