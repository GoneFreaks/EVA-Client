package main.commands;

import main.ViewManager;
import main.commands.types.ServerCommand;

public class AnswerCommand implements ServerCommand {

	@Override
	public void performCommand(String data) throws Exception {
		if(ViewManager.INSTANCE.isGame()) ViewManager.INSTANCE.showData("ans" + data);
	}
}
