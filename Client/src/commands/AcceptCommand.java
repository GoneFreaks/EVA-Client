package commands;

import commands.types.ClientCommand;
import listener.GetThread;
import main.ViewManager;

public class AcceptCommand implements ClientCommand {

	@Override
	public void performCommand(String data) throws Exception {
		ViewManager.setGameView();
		GetThread.INSTANCE.changeState();
	}
}
