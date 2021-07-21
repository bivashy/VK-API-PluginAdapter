package com.ubivashka.vk.bungee.events;

import com.ubivashka.vk.VKEvent;
import com.vk.api.sdk.objects.messages.Message;

import net.md_5.bungee.api.plugin.Cancellable;
import net.md_5.bungee.api.plugin.Event;

public class VKMessageEvent extends Event implements VKEvent, Cancellable {
	private boolean cancel = false;

	private Message message;

	public VKMessageEvent(Message message) {
		setMessage(message);
	}

	public final void setCancelled(boolean cancel) {
		this.cancel = cancel;
	}

	public final boolean isCancelled() {
		return this.cancel;
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
