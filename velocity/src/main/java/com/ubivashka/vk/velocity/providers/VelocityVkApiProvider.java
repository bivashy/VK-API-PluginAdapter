package com.ubivashka.vk.velocity.providers;

import com.ubivashka.vk.api.config.PluginConfig;
import com.ubivashka.vk.api.parsers.LongpoolEventParser;
import com.ubivashka.vk.api.providers.VkApiProvider;
import com.ubivashka.vk.velocity.VelocityVkApiPlugin;
import com.ubivashka.vk.velocity.parsers.VelocityLongpoolEventParser;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;

public class VelocityVkApiProvider implements VkApiProvider {
	private final LongpoolEventParser longpoolEventParser = new VelocityLongpoolEventParser(
			VelocityVkApiPlugin.getInstance());
	private final GroupActor groupActor;
	private final VkApiClient vkApiClient;

	public VelocityVkApiProvider(PluginConfig pluginConfig) {
		this.groupActor = new GroupActor(pluginConfig.getGroupId(), pluginConfig.getGroupToken());
		this.vkApiClient = new VkApiClient(HttpTransportClient.getInstance());

		try {
			vkApiClient.messages().getLongPollServer(groupActor).execute();
		} catch (ApiException e) {
			System.out.println("Код ошибки: " + e.getCode());
			System.out.println("В сайте https://vk.com/dev/errors описаны все ошибки связанные с ВК");
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
}
