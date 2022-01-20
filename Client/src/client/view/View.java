package client.view;

import javax.swing.JFrame;

import client.util.dto.QuestionDTO;

public interface View {

	public void start();
	public String shutdown();
	public View setFrame(JFrame frame, String id);
	public void showData(String input);
	public void showData(QuestionDTO question);
	public void enableButtons(boolean state);
	
}
