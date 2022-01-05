package main.commands;

import main.ViewManager;
import main.commands.types.ServerCommand;
import main.listener.GetThread;
import main.util.MessageManager;

public class ResultCommand implements ServerCommand {

	@Override
	public void performCommand(String data) throws Exception {
		if(ViewManager.INSTANCE.isGame()) {
			ViewManager.INSTANCE.showData("res" + data);
			ViewManager.INSTANCE.setWaitingView();
			MessageManager.INSTANCE.sendMessage("new");
			
			Thread getThread = new Thread(new GetThread());
			GetThread.running = true;
			getThread.setDaemon(true);
			getThread.start();
		}
	}
}