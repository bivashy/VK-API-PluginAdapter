package com.ubivashka.vk.bukkit.providers;

import com.ubivashka.vk.api.config.PluginConfig;
import com.ubivashka.vk.api.parsers.LongpoolEventParser;
import com.ubivashka.vk.api.providers.VkApiProvider;
import com.ubivashka.vk.bukkit.BukkitVkApiPlugin;
import com.ubivashka.vk.bukkit.parsers.BukkitLongpoolEventParser;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;

import net.md_5.bungee.api.ChatColor;

public class BukkitVkApiProvider implements VkApiProvider {

	private final LongpoolEventParser longpoolEventParser = new BukkitLongpoolEventParser(
			BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class));
	private final GroupActor groupActor;
	private final VkApiClient vkApiClient;

	public BukkitVkApiProvider(PluginConfig pluginConfig) {
		this.groupActor = new GroupActor(pluginConfig.getGroupId(), pluginConfig.getGroupToken());
		this.vkApiClient = new VkApiClient(HttpTransportClient.getInstance());

		try {
			vkApiClient.messages().getLongPollServer(groupActor).execute();
		} catch(ApiException e) {
			System.out.println(ChatColor.RED + "Код ошибки: " + e.getCode());
			System.out.println(ChatColor.RED + "В сайте https://vk.com/dev/errors описаны все ошибки связанные с ВК");
			e.printStackTrace();
		} catch(ClientException e) {
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
}