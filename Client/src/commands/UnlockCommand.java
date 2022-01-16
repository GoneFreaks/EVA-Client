package commands;

import commands.types.ServerCommand;
import main.ViewManager;

public class UnlockCommand implements ServerCommand {

	@Override
	public void performCommand(String data) throws Exception {
		ViewManager.enableButtons(true);
	}
}
