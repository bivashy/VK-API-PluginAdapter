package com.ubivashka.vk.bungee.events;

import com.ubivashka.vk.VKEvent;
import com.vk.api.sdk.objects.messages.Message;

import net.md_5.bungee.api.plugin.Event;

public class VKMessageEditEvent extends Event implements VKEvent {
	private Message message;

	public VKMessageEditEvent(Message message) {
		setMessage(message);
	}

	public Message getMessage() {
		return this.message;
	}

	public Integer getUserId() {
		return this.message.getFromId();
	}

	public Integer getPeer() {
		return this.message.getPeerId();
	}

	private void setMessage(Message message) {
		this.message = message;
	}
}
