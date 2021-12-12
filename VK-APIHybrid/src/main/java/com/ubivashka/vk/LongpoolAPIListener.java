package com.ubivashka.vk;

import java.util.concurrent.TimeUnit;

import com.google.gson.JsonObject;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.exceptions.LongPollServerKeyExpiredException;
import com.vk.api.sdk.objects.callback.longpoll.responses.GetLongPollEventsResponse;
import com.vk.api.sdk.objects.groups.responses.GetLongPollServerResponse;

import net.md_5.bungee.api.ChatColor;

public class LongpoolAPIListener {
	private static final int MILLI_PER_TICK = 1000 / 20;

	private VKAPIPlugin plugin;

	private String ts;

	private int delay = 2;
	private boolean reconnecting = false;

	private GetLongPollServerResponse longserver;

	public LongpoolAPIListener(VKAPIPlugin plugin) {
		this.plugin = plugin;
		updateKey();
		ts = longserver.getTs();
		startEventListener();
	}

	private void startEventListener() {
		System.out.println(ChatColor.GREEN + "LongPool event listener enabled");
		Scheduler.scheduleAtFixedRate(new CancellableRunnable() {

			public void run() {
				try {
					handleEvents();
				} catch (ClientException e) {
					reconnecting = true;
					System.out.println(ChatColor.GRAY + "[" + ChatColor.BLUE + "VKAPI" + ChatColor.GRAY + "] "
							+ ChatColor.YELLOW
							+ String.format("Подключение прервано, попытка переподключения через %d секунд", delay));
					try {
						Thread.sleep(delay * 1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					delay *= 2;
				} catch (LongPollServerKeyExpiredException e) {
					updateKey();
				} catch (NumberFormatException | ApiException e) {
					e.printStackTrace();
					cancel();
				}

			}

			private void handleEvents() throws ApiException, ClientException {
				GetLongPollEventsResponse events = (GetLongPollEventsResponse) plugin.getVK().longPoll()
						.getEvents(longserver.getServer(), longserver.getKey(), ts).waitTime(10).execute();
				for (JsonObject json : events.getUpdates()) {
					plugin.callEvent(json);
				}
				ts = events.getTs();
				delay = 2;
				if (reconnecting) {
					System.out.println(ChatColor.GRAY + "[" + ChatColor.BLUE + "VKAPI" + ChatColor.GRAY + "] "
							+ ChatColor.GREEN + "Подключение восстановлено!");
					reconnecting = false;
				}
			}
		}, 0L, plugin.getDelay() * MILLI_PER_TICK, TimeUnit.MILLISECONDS);
	}

	private void updateKey() {
		try {
			this.longserver = (GetLongPollServerResponse) plugin.getVK().groupsLongPoll()
					.getLongPollServer(plugin.getActor(), plugin.getActor().getGroupId().intValue()).execute();
		} catch (ApiException | com.vk.api.sdk.exceptions.ClientException e) {
			e.printStackTrace();
		}
	}
}
