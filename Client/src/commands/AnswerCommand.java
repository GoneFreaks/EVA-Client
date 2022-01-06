package commands;

import commands.types.ServerCommand;
import main.ViewManager;

public class AnswerCommand implements ServerCommand {

	@Override
	public void performCommand(String data) throws Exception {
		if(ViewManager.INSTANCE.isGame()) ViewManager.INSTANCE.showData("ans" + data);
	}
}
