package com.ubivashka.vk.api.providers;

import com.ubivashka.vk.api.parsers.LongpoolEventParser;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.objects.messages.Message;

public interface VkApiProvider {
	GroupActor getActor();

	VkApiClient getVkApiClient();

	LongpoolEventParser getLongpoolParser();

	boolean deleteMessage(Message message);
}
