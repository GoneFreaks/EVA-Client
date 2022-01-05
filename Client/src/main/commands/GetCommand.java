package main.commands;

import main.ViewManager;
import main.commands.types.ServerCommand;

public class GetCommand implements ServerCommand {

	@Override
	public void performCommand(String data) throws Exception {
		if(!ViewManager.INSTANCE.isGame() && data.length() > 3) ViewManager.INSTANCE.showData(data);
	}
}
