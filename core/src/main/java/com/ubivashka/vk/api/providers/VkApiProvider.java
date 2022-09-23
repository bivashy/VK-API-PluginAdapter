package com.ubivashka.vk.api.providers;

import java.util.Collections;

import com.ubivashka.vk.api.parsers.LongpoolEventParser;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;

public interface VkApiProvider {
	GroupActor getActor();

	VkApiClient getVkApiClient();

	LongpoolEventParser getLongpoolParser();

	/**
	 * Deprecated because it must be another class, like: VKHelper or VkMethodHelper
	 */
	@Deprecated
	default boolean deleteMessage(Message message){
		try {
			getVkApiClient().messages().delete(getActor()).messageIds(Collections.singletonList(message.getId())).deleteForAll(true)
					.execute();
			return true;
		} catch (ApiException | ClientException e) {
			e.printStackTrace();
		}
		return false;
	}
}
