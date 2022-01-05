package main.commands;

import main.ViewManager;
import main.commands.types.ServerCommand;
import main.util.MessageManager;

public class ResultCommand implements ServerCommand {

	@Override
	public void performCommand(String data) throws Exception {
		if(ViewManager.INSTANCE.isGame()) {
			ViewManager.INSTANCE.showData("res" + data);
			ViewManager.INSTANCE.setWaitingView();
			MessageManager.INSTANCE.sendMessage("new");
		}
	}
}