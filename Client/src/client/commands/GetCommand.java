package client.commands;

import client.ViewManager;
import client.commands.types.ClientCommand;

public class GetCommand implements ClientCommand {

	@Override
	public void performCommand(String data) throws Exception {
		if(!ViewManager.isGame()) ViewManager.showData("get" + data);
	}
}
