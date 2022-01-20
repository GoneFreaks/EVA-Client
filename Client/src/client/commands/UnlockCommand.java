package client.commands;

import client.ViewManager;
import client.commands.types.ClientCommand;

public class UnlockCommand implements ClientCommand {

	@Override
	public void performCommand(String data) throws Exception {
		ViewManager.enableButtons(true);
	}
}
