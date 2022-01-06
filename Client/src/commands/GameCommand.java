package commands;

import commands.types.ServerCommand;
import main.ViewManager;

public class GameCommand implements ServerCommand {

	@Override
	public void performCommand(String data) throws Exception {
		if(ViewManager.INSTANCE.isGame()) ViewManager.INSTANCE.showData("gam" + data);
	}
}
