package com.ubivashka.vk.api.scheduler;

import java.util.concurrent.ExecutionException;

/**
 * @author vladimir-bukhtoyarov source from
 *         <a href="https://gist.github.com/vladimir-bukhtoyarov/b71fe668ce53e1e81856ccc7e99a3150">...</a>
 */
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
		if (scheduler == null)
			return;
		this.scheduler = scheduler;
	}
}
