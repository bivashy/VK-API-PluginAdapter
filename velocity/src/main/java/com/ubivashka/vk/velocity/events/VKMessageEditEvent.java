package com.ubivashka.vk.velocity.events;

import com.vk.api.sdk.objects.messages.Message;

public class VKMessageEditEvent extends VKMessageUpdateEvent {

	public VKMessageEditEvent(Message message, Integer groupId) {
		super(message, groupId);
	}

}
