package client;

import java.util.concurrent.ConcurrentHashMap;

import client.commands.AcceptCommand;
import client.commands.AnswerCommand;
import client.commands.DeleteCommand;
import client.commands.GameCommand;
import client.commands.GetCommand;
import client.commands.NotifyCommand;
import client.commands.ResultCommand;
import client.commands.UnlockCommand;
import client.commands.types.ClientCommand;

public class CommandManager {

	public static CommandManager INSTANCE;
	
	private ConcurrentHashMap<String, ClientCommand> storage;
	
	public CommandManager() {
		INSTANCE = this;
		this.storage = new ConcurrentHashMap<>();
		this.storage.put("unl", new UnlockCommand());
		this.storage.put("acc", new AcceptCommand());
		this.storage.put("ans", new AnswerCommand());
		this.storage.put("gam", new GameCommand());
		this.storage.put("get", new GetCommand());
		this.storage.put("res", new ResultCommand());
		this.storage.put("not", new NotifyCommand());
		this.storage.put("del", new DeleteCommand());
	}
	
	public void performCommand(String input) {
		String cmd = input.substring(0, 3);
		String data = input.substring(3);
		
		if(storage.get(cmd) != null) {
			try {
				storage.get(cmd).performCommand(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else System.out.println("UNBEKANNTER COMMAND: " + cmd);
	}
	
}
