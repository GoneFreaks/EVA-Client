package client.listener;

import client.util.MessageManager;

public class GetThread implements Runnable {
	
	public static GetThread INSTANCE;
	private boolean waiting;
	
	public GetThread() {
		INSTANCE = this;
		waiting = false;
	}
	
	private synchronized void send() {
		try {
			while(waiting) wait();
			MessageManager.sendMessage("#get");
		} catch (Exception e) {
		}
	}
	
	// "deactivate" thread --> not needed during game
	public synchronized void changeState() {
		waiting = !waiting;
		if(!waiting) notifyAll();
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.currentThread().isInterrupted()) {
				send();
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
