package main;

import java.util.concurrent.ConcurrentHashMap;

import commands.AcceptCommand;
import commands.AnswerCommand;
import commands.GameCommand;
import commands.GetCommand;
import commands.ResultCommand;
import commands.UnlockCommand;
import commands.types.ServerCommand;

public class CommandManager {

	public static CommandManager INSTANCE;
	
	private ConcurrentHashMap<String, ServerCommand> storage;
	
	public CommandManager() {
		INSTANCE = this;
		this.storage = new ConcurrentHashMap<>();
		this.storage.put("unl", new UnlockCommand());
		this.storage.put("acc", new AcceptCommand());
		this.storage.put("ans", new AnswerCommand());
		this.storage.put("gam", new GameCommand());
		this.storage.put("get", new GetCommand());
		this.storage.put("res", new ResultCommand());
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
