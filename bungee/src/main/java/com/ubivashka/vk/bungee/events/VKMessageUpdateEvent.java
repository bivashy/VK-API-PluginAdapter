package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.messages.Message;

import net.md_5.bungee.api.plugin.Cancellable;
import net.md_5.bungee.api.plugin.Event;

public abstract class VKMessageUpdateEvent extends Event implements Cancellable {
	private boolean cancel = false;

	private Message message;

	public VKMessageUpdateEvent(Message message) {
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
