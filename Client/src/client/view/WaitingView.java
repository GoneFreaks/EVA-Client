package client.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import client.util.MessageManager;
import client.util.dto.QuestionDTO;

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
				if(value == null || !value.toString().startsWith("@")) enableButtons(true);
				else MessageManager.sendMessage("#req" + value);
				break;
			}
			case "ACCEPT": {
				Object value = selection.get(1).getSelectedItem();
				if(value == null || !value.toString().startsWith("@")) enableButtons(true);
				else MessageManager.sendMessage("#acc" + value);
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
		
		String[] both = input.split(",,");
		for (int i = 0; i < both.length; i++) {
			
			JComboBox<String> box = selection.get(i);
			List<String> current = new ArrayList<>();
			for (int j = 0; j < box.getItemCount(); j++) {
				current.add(box.getItemAt(j));
			}
			
			List<String> new_input = new ArrayList<>();
			String[] args = both[i].split(",");
			for(int j = 0; j < args.length; j++) {
				if(args[j].contains("@")) new_input.add(args[j]);
			}
			modifyComboBox(box, current, new_input);
		}
	}
	
	@Override
	public void showData(QuestionDTO question) {}
	
	// only add new items and remove items not in new_input
	private void modifyComboBox(JComboBox<String> box, List<String> current, List<String> new_input) {
		
		for (int i = 0; i < box.getItemCount(); i++) {
			String item = box.getItemAt(i);
			if(!new_input.contains(item)) box.removeItem(item);
		}
		new_input.forEach((k) -> {
			if(!current.contains(k) && !k.equals(id.getText())) box.addItem(k);
		});
	}
	
}
