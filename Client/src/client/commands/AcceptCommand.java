package client.commands;

import client.ViewManager;
import client.commands.types.ClientCommand;
import client.listener.GetThread;

public class AcceptCommand implements ClientCommand {

	@Override
	public void performCommand(String data) throws Exception {
		ViewManager.setGameView();
		GetThread.INSTANCE.changeState();
	}
}
