package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.messages.Message;

public class VKMessageReplyEvent extends VKMessageUpdateEvent {

	public VKMessageReplyEvent(Message message, Integer groupId) {
		super(message, groupId);
	}

}
