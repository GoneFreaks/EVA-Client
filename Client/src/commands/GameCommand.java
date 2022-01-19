package commands;

import commands.types.ClientCommand;
import main.ViewManager;

public class GameCommand implements ClientCommand {

	@Override
	public void performCommand(String data) throws Exception {
		if(ViewManager.isGame()) ViewManager.showData("gam" + data);
	}
}
