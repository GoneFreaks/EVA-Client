package client.commands;

import client.ViewManager;
import client.commands.types.ClientCommand;
import client.listener.GetThread;
import client.util.MessageManager;

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