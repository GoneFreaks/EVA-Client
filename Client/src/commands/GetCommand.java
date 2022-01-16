package commands;

import commands.types.ServerCommand;
import main.ViewManager;

public class GetCommand implements ServerCommand {

	@Override
	public void performCommand(String data) throws Exception {
		if(!ViewManager.isGame()) ViewManager.showData("get" + data);
	}
}
