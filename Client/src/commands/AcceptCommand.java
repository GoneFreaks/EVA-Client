package commands;

import commands.types.ServerCommand;
import listener.GetThread;
import main.ViewManager;

public class AcceptCommand implements ServerCommand {

	@Override
	public void performCommand(String data) throws Exception {
		ViewManager.setGameView();
		GetThread.INSTANCE.changeState();
	}
}
