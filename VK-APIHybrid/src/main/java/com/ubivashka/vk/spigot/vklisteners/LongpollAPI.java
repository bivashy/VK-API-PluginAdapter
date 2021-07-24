package com.ubivashka.vk.spigot.vklisteners;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.google.gson.JsonObject;
import com.ubivashka.vk.spigot.VKAPI;
import com.ubivashka.vk.spigot.events.VKJsonEvent;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.exceptions.LongPollServerKeyExpiredException;
import com.vk.api.sdk.objects.callback.longpoll.responses.GetLongPollEventsResponse;
import com.vk.api.sdk.objects.groups.responses.GetLongPollServerResponse;

public class LongpollAPI {
	private VKAPI plugin;
	private BukkitTask currentTask = null;
	private GetLongPollServerResponse longserver;
	private volatile String ts;
	private volatile Integer delay = 2;
	private volatile boolean reconnecting = false;

	public LongpollAPI(VKAPI plugin) {
		this.plugin = plugin;
		checkDelay();
		updateKey();
		startEventListener();
		plugin.debug("LongPool event listener enabled");
	}

	private void startEventListener() {
		if (currentTask != null && !currentTask.isCancelled())
			currentTask.cancel();
		currentTask = new BukkitRunnable() {
			volatile boolean isNewTaskStarted = false;

			@Override
			public synchronized void run() {
				if (!haveInternet()) {
					cancel();
					if (!isNewTaskStarted) {
						isNewTaskStarted = true;
						reconnecting = true;
						plugin.warn(
								String.format("Подключение прервано, попытка переподключения через %d секунды", delay));

						Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> startEventListener(),
								delay * 20);
						delay *= 2;
					}
					return;
				}
				try {
					handleEvents();
				} catch (LongPollServerKeyExpiredException e) {
					updateKey();
				} catch (ApiException | ClientException e) {
					cancel();
				}
			}

			private void handleEvents() throws ApiException, ClientException {
				GetLongPollEventsResponse events = plugin.getVK().longPoll()
						.getEvents(longserver.getServer(), longserver.getKey(), ts).waitTime(20).execute();

				ts = events.getTs();
				List<JsonObject> eventResponseObjects = events.getUpdates();
				for (JsonObject json : eventResponseObjects)
					callEvent(json);

				delay = 2;
				if (reconnecting) {
					plugin.debug("Подключение восстановлено!");
					reconnecting = false;
				}
			}
		}.runTaskTimerAsynchronously(plugin, 0, plugin.getConfig().getInt("settings.delay"));

	}

	private synchronized void callEvent(JsonObject json) {
		VKJsonEvent jsonEvent = new VKJsonEvent(json);
		Bukkit.getPluginManager().callEvent(jsonEvent);
		plugin.getCallbackAPI().parse(json);
	}

	private void updateKey() {
		try {
			this.longserver = plugin.getVK().groupsLongPoll()
					.getLongPollServer(plugin.getActor(), plugin.getActor().getGroupId().intValue()).execute();
			ts = longserver.getTs();
		} catch (ApiException | com.vk.api.sdk.exceptions.ClientException e) {
			e.printStackTrace();
			return;
		}
	}

	private boolean haveInternet() {
		try {
			URL url = new URL("http://www.google.com");
			URLConnection connection = url.openConnection();
			connection.connect();
			return true;
		} catch (MalformedURLException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	private void checkDelay() {
		int delay = plugin.getConfig().getInt("settings.delay");
		if (delay < 10 || delay > 40) {
			plugin.warn(
					"Ваш delay в конфиге слишком низкий, или слишком высокий, при неправильной работе, попробуйте поставить её на нормальное значение (15)");
		}

	}

	public BukkitTask getTask() {
		return currentTask;
	}
}
