package com.ubivashka.vk.spigot.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.messages.Message;

@Deprecated
public class VKMessageReplyEvent extends VKMessageUpdateEvent {
	private static final HandlerList handlers = new HandlerList();

	public VKMessageReplyEvent(Message message) {
		super(message);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}
}
