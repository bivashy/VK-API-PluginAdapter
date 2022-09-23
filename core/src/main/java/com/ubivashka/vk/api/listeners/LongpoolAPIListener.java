package com.ubivashka.vk.api.listeners;

import java.util.concurrent.TimeUnit;

import com.google.gson.JsonObject;
import com.ubivashka.vk.api.VkApiPlugin;
import com.ubivashka.vk.api.scheduler.CancellableRunnable;
import com.ubivashka.vk.api.scheduler.Scheduler;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.exceptions.LongPollServerKeyExpiredException;
import com.vk.api.sdk.objects.callback.longpoll.responses.GetLongPollEventsResponse;
import com.vk.api.sdk.objects.groups.responses.GetLongPollServerResponse;

public class LongpoolAPIListener {
	private static final int MILLI_PER_TICK = 1000 / 20;

	private VkApiPlugin plugin;

	private GetLongPollServerResponse longserver;
	private String lastTimeStamp;

	private long reconnectMillisDelay = 2;
	private boolean reconnecting = false;

	public LongpoolAPIListener(VkApiPlugin plugin) {
		this.plugin = plugin;
		this.updateLongpoolServer();
		lastTimeStamp = longserver.getTs();
		startEventListener();
	}

	private void startEventListener() {
		Scheduler.scheduleAtFixedRate(new CancellableRunnable() {

			public void run() {
				try {
					handleEvents();
				} catch (ClientException e) {
					System.out.printf("[VK-API] Подключение прервано, попытка переподключения через %d секунд%n",
							reconnectMillisDelay);

					reconnecting = true;
					try {
						Thread.sleep(reconnectMillisDelay * 1000);
					} catch (InterruptedException ignored) {
					}
					reconnectMillisDelay *= 2;
				} catch (LongPollServerKeyExpiredException e) {
					updateLongpoolServer();
				} catch (ApiException e) {
					e.printStackTrace();
					cancel();
				}
			}

			private void handleEvents() throws ApiException, ClientException {
				VkApiClient client = plugin.getVkApiProvider().getVkApiClient();
				GetLongPollEventsResponse events = client.longPoll()
						.getEvents(longserver.getServer(), longserver.getKey(), lastTimeStamp).waitTime(10).execute();
				for (JsonObject json : events.getUpdates())
					plugin.getVkApiProvider().getLongpoolParser().parse(json);

				lastTimeStamp = events.getTs();
				if (reconnecting) {
					System.out.println("[VK-API] Подключение восстановлено!");

					reconnectMillisDelay = 2;
					reconnecting = false;
				}
			}

		}, 0L, plugin.getPluginConfig().getLongpoolSchedulerDelay() * MILLI_PER_TICK, TimeUnit.MILLISECONDS);
	}

	private void updateLongpoolServer() {
		try {
			GroupActor actor = plugin.getVkApiProvider().getActor();
			VkApiClient client = plugin.getVkApiProvider().getVkApiClient();
			this.longserver = client.groupsLongPoll().getLongPollServer(actor, actor.getGroupId()).execute();
		} catch (ApiException | com.vk.api.sdk.exceptions.ClientException e) {
			e.printStackTrace();
		}
	}
}
