package commands;

import commands.types.ClientCommand;
import listener.GetThread;
import main.ViewManager;
import util.MessageManager;

public class ResultCommand implements ClientCommand {

	@Override
	public void performCommand(String data) throws Exception {
		if(ViewManager.isGame()) {
			ViewManager.showData("res" + data);
			ViewManager.setWaitingView();
			MessageManager.sendMessage("#new");
			
			GetThread.INSTANCE.changeState();
		}
	}
}