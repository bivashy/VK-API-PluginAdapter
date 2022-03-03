package com.ubivashka.example.listeners;

import java.util.Random;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.ubivashka.vk.bukkit.BukkitVkApiPlugin;
import com.ubivashka.vk.bukkit.events.VKMessageEvent;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.utils.DomainResolvedType;
import com.vk.api.sdk.objects.utils.responses.ResolveScreenNameResponse;

public class ScreenNameExample implements Listener {
	private final static VkApiClient CLIENT = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
			.getVkApiClient();
	private final static GroupActor ACTOR = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
			.getActor();
	private final static Random RANDOM = new Random();

	@EventHandler
	public void onMessage(VKMessageEvent e) throws ApiException, ClientException {
		String command = "Какой id: ";
		if (!(e.getMessage().getText().startsWith(command)))
			return;
		if (e.getMessage().getText().length() <= command.length())
			return;
		String screenName = e.getMessage().getText().substring(command.length());
		ResolveScreenNameResponse screenNameResponse = null;
		try {
			screenNameResponse = CLIENT.utils().resolveScreenName(ACTOR, screenName).execute(); // https://vk.com/dev/utils.resolveScreenName
		} catch (ApiException | ClientException ignored) {
			CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message("Произошла ошибка!") // https://vk.com/dev/messages.send
					.execute();
			return;
		}
		Integer id = 1;
		if (screenNameResponse.getType() == DomainResolvedType.GROUP) {
			id = screenNameResponse.getGroupId(); // У групп свой id
		} else { // Если это не группа используем getObjectId
			id = screenNameResponse.getObjectId();
		}
		CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message("ID: " + id).execute(); // https://vk.com/dev/messages.send
	}
}
