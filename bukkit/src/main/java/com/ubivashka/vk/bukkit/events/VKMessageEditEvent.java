package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.messages.Message;

public class VKMessageEditEvent extends VKMessageUpdateEvent {
	private static final HandlerList handlers = new HandlerList();

	public VKMessageEditEvent(Message message, Integer groupId) {
		super(message,groupId);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}
}
