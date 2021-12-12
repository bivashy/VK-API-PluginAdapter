package com.ubivashka.vk;

import java.util.concurrent.ExecutionException;

public abstract class CancellableRunnable implements Runnable {
	private Scheduler scheduler;

	public abstract void run();

	public void cancel() {
		try {
			scheduler.cancelAndBeSureOfTermination(true);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	protected void setScheduler(Scheduler scheduler) {
		if (scheduler != null)
			return;
		this.scheduler = scheduler;
	}
}
