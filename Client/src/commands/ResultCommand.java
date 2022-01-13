package commands;

import commands.types.ServerCommand;
import listener.GetThread;
import main.ViewManager;
import util.MessageManager;

public class ResultCommand implements ServerCommand {

	@Override
	public void performCommand(String data) throws Exception {
		if(ViewManager.INSTANCE.isGame()) {
			ViewManager.INSTANCE.showData("res" + data);
			ViewManager.INSTANCE.setWaitingView();
			MessageManager.INSTANCE.sendMessage("#new");
			
			GetThread.INSTANCE.changeState();
		}
	}
}