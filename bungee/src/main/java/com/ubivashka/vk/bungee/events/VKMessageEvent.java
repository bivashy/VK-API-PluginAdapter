package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.messages.Message;

public class VKMessageEvent extends VKMessageUpdateEvent {

	public VKMessageEvent(Message message) {
		super(message);
	}

}