package com.ubivashka.vk.api;

import com.ubivashka.vk.api.config.PluginConfig;
import com.ubivashka.vk.api.parsers.LongpoolEventParser;
import com.ubivashka.vk.api.providers.VkApiProvider;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;

public interface VkApiPlugin<E, T> {
	@Deprecated
	/**
	 * @return Actor of group Deprecated because this method moved to
	 *         {@link VkApiPlugin#getVkApiProvider()}
	 */
	GroupActor getActor();

	@Deprecated
	/**
	 * @return client of vk Deprecated because this method moved to
	 *         {@link VkApiPlugin#getVkApiProvider()}
	 */
	VkApiClient getVK();

	@Deprecated
	/**
	 * @return client of vk Deprecated because this method moved to
	 *         {@link VkApiPlugin#getVkApiProvider()}
	 */
	LongpoolEventParser getLongpoolParser();

	VkApiProvider getVkApiProvider();

	PluginConfig<T> getPluginConfig();

	void callEvent(E event);
}
