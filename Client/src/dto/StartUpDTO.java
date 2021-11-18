package dto;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StartUpDTO {

	private static final String[] STARTUP_LABELS = {"ID: ...", "RELOAD",
			"", "REQUEST",
			"", "ACCEPT"};
	private List<JComboBox<String>> selection;
	private List<JButton> buttons;
	private JLabel id;
	
	
	private final JFrame frame;
	public StartUpDTO (JFrame frame) {
		this.frame = frame;
		selection = new ArrayList<>();
		buttons = new ArrayList<>();
	}
	
	private void handleAction (ActionEvent a) {
		
		enableButtons(false);
		
		JButton pressed = (JButton) a.getSource();
		switch (pressed.getText()) {
			case "RELOAD": {
				System.out.println("RELOAD DATA");
				break;
			}
			case "REQUEST": {
				System.out.println(selection.get(0).getSelectedItem());
				break;
			}
			case "ACCEPT": {
				System.out.println(selection.get(1).getSelectedItem());
				break;
			}
		}
	}
	
	public void startUp() {
		
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
	
	public void shutdown() {
		frame.removeAll();
		frame.setVisible(false);
	}
	
	public void setId (String id) {
		this.id.setText(this.id.getText().substring(0, 4) + id);
	}
	
	public void addPlayer(String playerId) {
		selection.get(0).addItem(playerId);
	}
	
	public void addInvite(String invite) {
		selection.get(1).addItem(invite);
	}
	
	public void enableButtons(boolean state) {
		for (JButton i : buttons) {
			i.setEnabled(state);
		}
	}
	
}
