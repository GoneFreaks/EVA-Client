package main.commands;

import main.ViewManager;
import main.commands.types.ServerCommand;

public class AcceptCommand implements ServerCommand {

	@Override
	public void performCommand(String data) throws Exception {
		ViewManager.INSTANCE.setGameView();
	}
}
