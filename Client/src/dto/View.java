package dto;

import javax.swing.JFrame;

public interface View {

	public void start();
	public String shutdown();
	public View setFrame(JFrame frame, String id);
	public void showData(String input);
	public void enableButtons(boolean state);
	
}
