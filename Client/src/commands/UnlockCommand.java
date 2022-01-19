package commands;

import commands.types.ClientCommand;
import main.ViewManager;

public class UnlockCommand implements ClientCommand {

	@Override
	public void performCommand(String data) throws Exception {
		ViewManager.enableButtons(true);
	}
}
