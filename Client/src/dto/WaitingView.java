package dto;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import main.util.MessageManager;

public class WaitingView implements View {

	private static final String[] STARTUP_LABELS = {"ID: ...", "",
			"", "REQUEST",
			"", "ACCEPT"};
	private List<JComboBox<String>> selection;
	private List<JButton> buttons;
	private JLabel id;
	private JFrame frame;
	private String id_text;
	
	public WaitingView () {
		selection = new ArrayList<>();
		buttons = new ArrayList<>();
	}
	
	public View setFrame(JFrame frame, String id) {
		this.frame = frame;
		this.id_text = id;
		return this;
	}
	
	private void handleAction (ActionEvent a) {
		
		enableButtons(false);
		
		JButton pressed = (JButton) a.getSource();
		switch (pressed.getText()) {
			case "REQUEST": {
				Object value = selection.get(0).getSelectedItem();
				if(value == null) enableButtons(true);
				else MessageManager.INSTANCE.sendMessage("#req" + value);
				break;
			}
			case "ACCEPT": {
				Object value = selection.get(1).getSelectedItem();
				if(value == null) enableButtons(true);
				else MessageManager.INSTANCE.sendMessage("#acc" + value);
				break;
			}
		}
	}
	
	public void start() {
		
		frame.setLayout(new GridLayout(3, 2));
		
		for (int i = 0; i < 6; i++) {
			String label = STARTUP_LABELS[i];
			if(i % 2 == 0 || i <  2) {
				if(i == 0) {
					id = new JLabel(id_text != null? id_text : label);
					frame.add(id);
				}
				else {
					if(i == 1) frame.add(new JLabel(label));
					else {
						JComboBox<String> temp = new JComboBox<String>();
						selection.add(temp);
						frame.add(temp);
					}
				}
			}
			else {
				JButton temp = new JButton(label);
				temp.addActionListener((a) -> handleAction(a));
				buttons.add(temp);
				frame.add(temp);
			}
		}
		
		frame.setSize(900, 600);
		frame.setVisible(true);
	}
	
	public String shutdown() {
		frame.setVisible(false);
		frame.removeAll();
		return id.getText();
	}
	
	public void enableButtons(boolean state) {
		for (JButton i : buttons) i.setEnabled(state);
	}
	
	public void showData(String input) {
		if(input.startsWith("@") && !id.getText().startsWith("@")) id.setText(input.trim());
		
		selection.forEach((k) -> {
			k.removeAllItems();
		});
					
		String[] both = input.split(",,");
		for(int i = 0; i < both.length; i++) {
			String[] args = both[i].split(",");
			for (int j = 0; j < args.length; j++) {
				String temp = args[j].trim();
				if(temp.equals(id.getText())) continue;
				selection.get(i).addItem(temp);
			}
		}
	}
	
}
