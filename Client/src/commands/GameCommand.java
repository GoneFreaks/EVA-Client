package commands;

import commands.types.ServerCommand;
import main.ViewManager;

public class GameCommand implements ServerCommand {

	@Override
	public void performCommand(String data) throws Exception {
		if(ViewManager.isGame()) ViewManager.showData("gam" + data);
	}
}
