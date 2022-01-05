package main.commands;

import main.ViewManager;
import main.commands.types.ServerCommand;
import main.listener.GetThread;

public class AcceptCommand implements ServerCommand {

	@Override
	public void performCommand(String data) throws Exception {
		ViewManager.INSTANCE.setGameView();
		GetThread.running = false;
	}
}
