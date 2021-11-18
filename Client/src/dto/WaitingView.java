package dto;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import main.MessageManager;

public class WaitingView implements View {

	private static final String[] STARTUP_LABELS = {"ID: ...", "RELOAD",
			"", "REQUEST",
			"", "ACCEPT"};
	private List<JComboBox<String>> selection;
	private List<JButton> buttons;
	private JLabel id;
	private JFrame frame;
	
	public WaitingView () {
		selection = new ArrayList<>();
		buttons = new ArrayList<>();
	}
	
	public View setFrame(JFrame frame, String id) {
		this.frame = frame;
		return this;
	}
	
	private void handleAction (ActionEvent a) {
		
		enableButtons(false);
		
		JButton pressed = (JButton) a.getSource();
		switch (pressed.getText()) {
			case "RELOAD": {
				new Thread(() -> {
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (Exception e) {
					}
					enableButtons(true);
				}).start();
				MessageManager.sendMessage("get");
				break;
			}
			case "REQUEST": {
				Object value = selection.get(0).getSelectedItem();
				if(value == null) enableButtons(true);
				else MessageManager.sendMessage("req" + value);
				break;
			}
			case "ACCEPT": {
				Object value = selection.get(1).getSelectedItem();
				if(value == null) enableButtons(true);
				else MessageManager.sendMessage("acc" + value);
				break;
			}
		}
	}
	
	public void start() {
		
		frame.setLayout(new GridLayout(3, 2));
		
		for (int i = 0; i < 6; i++) {
			String label = STARTUP_LABELS[i];
			if(i % 2 == 0) {
				if(i == 0) {
					id = new JLabel(label);
					frame.add(id);
				}
				else {
					JComboBox<String> temp = new JComboBox<String>();
					selection.add(temp);
					frame.add(temp);
				}
			}
			else {
				JButton temp = new JButton(label);
				temp.addActionListener((a) -> handleAction(a));
				buttons.add(temp);
				frame.add(temp);
			}
		}
		
		frame.setSize(600, 400);
		frame.setVisible(true);
	}
	
	public String shutdown() {
		frame.setVisible(false);
		frame.removeAll();
		return id.getText();
	}
	
	private void enableButtons(boolean state) {
		for (JButton i : buttons) i.setEnabled(state);
	}
	
	public void showData(String input) {
		if(input.startsWith("#")) id.setText(input.trim());
		else {
			String cmd = input.substring(0, 3);
			String data = input.substring(3);
			
			switch (cmd) {
				case "get": {
					String[] both = data.split(",,");
					for(int i = 0; i < both.length; i++) {
						String[] args = both[i].split(",");
						for (int j = 0; j < args.length; j++) {
							String temp = args[j].trim();
							if(temp.equals(id.getText())) continue;
							selection.get(i).removeItem(temp);
							if(i == 1) selection.get(0).removeItem(temp);
							selection.get(i).addItem(temp);
						}
					}
					break;
				}
			}
		}
	}
	
}
