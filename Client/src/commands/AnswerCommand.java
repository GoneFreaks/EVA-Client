package commands;

import commands.types.ClientCommand;
import main.ViewManager;

public class AnswerCommand implements ClientCommand {

	@Override
	public void performCommand(String data) throws Exception {
		if(ViewManager.isGame()) ViewManager.showData("ans" + data);
	}
}
