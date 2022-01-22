package client.commands;

import client.commands.types.ClientCommand;

public class DeleteCommand implements ClientCommand {

	@Override
	public void performCommand(String data) throws Exception {
		System.exit(0);
	}

}
