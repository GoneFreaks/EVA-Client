package listener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import util.MessageManager;

public class WindowEventListener implements WindowListener{

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		MessageManager.INSTANCE.sendMessage("#del");
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

}
