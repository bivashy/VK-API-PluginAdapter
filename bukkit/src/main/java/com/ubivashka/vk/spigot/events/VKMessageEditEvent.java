package com.ubivashka.vk.spigot.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.messages.Message;

@Deprecated
public class VKMessageEditEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private Message message;

	public VKMessageEditEvent(Message message) {
		super(true);
		this.setMessage(message);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}

	public Message getMessage() {
		return message;
	}

	public Integer getUserId() {
		return message.getFromId();
	}

	public Integer getPeer() {
		return message.getPeerId();
	}

	private void setMessage(Message message) {
		this.message = message;
	}

}
