package com.ubivashka.vk.bungee.providers;

import java.util.Arrays;

import com.ubivashka.vk.api.config.PluginConfig;
import com.ubivashka.vk.api.parsers.LongpoolEventParser;
import com.ubivashka.vk.api.providers.VkApiProvider;
import com.ubivashka.vk.bungee.BungeeVkApiPlugin;
import com.ubivashka.vk.bungee.parsers.BungeeLongpoolEventParser;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.Message;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.config.Configuration;

public class BungeeVkApiProvider implements VkApiProvider {

	private final LongpoolEventParser longpoolEventParser = new BungeeLongpoolEventParser(
			BungeeVkApiPlugin.getInstance());
	private final GroupActor groupActor;
	private final VkApiClient vkApiClient;

	public BungeeVkApiProvider(PluginConfig<Configuration> pluginConfig) {
		this.groupActor = new GroupActor(pluginConfig.getGroupId(), pluginConfig.getGroupToken());
		this.vkApiClient = new VkApiClient(HttpTransportClient.getInstance());

		try {
			vkApiClient.messages().getLongPollServer(groupActor).execute();
		} catch (ApiException e) {
			System.out.println(ChatColor.RED + "Код ошибки: " + e.getCode());
			System.out.println(ChatColor.RED + "В сайте https://vk.com/dev/errors описаны все ошибки связанные с ВК");
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

	@Override
	public GroupActor getActor() {
		return groupActor;
	}

	@Override
	public VkApiClient getVkApiClient() {
		return vkApiClient;
	}

	@Override
	public LongpoolEventParser getLongpoolParser() {
		return longpoolEventParser;
	}

	@Override
	public boolean deleteMessage(Message message) {
		try {
			vkApiClient.messages().delete(groupActor).messageIds(Arrays.asList(message.getId())).deleteForAll(true)
					.execute();
			return true;
		} catch (ApiException | ClientException e) {
			e.printStackTrace();
		}
		return false;
	}

}
