package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.HandlerList;

import com.ubivashka.vk.api.parsers.objects.MessageTyping;

public class VKMessageTypingEvent extends AbstractVkEvent {
	private static final HandlerList handlers = new HandlerList();
	private MessageTyping messageTyping;

	public VKMessageTypingEvent(MessageTyping message, Integer groupId) {
		super(groupId);
		setMessageTyping(message);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}

	public MessageTyping getMessageTyping() {
		return messageTyping;
	}

	public String getState() {
		return messageTyping.getState();
	}

	public Integer getToId() {
		return messageTyping.getToId();
	}

	public Integer getFromId() {
		return messageTyping.getFromId();
	}

	public void setMessageTyping(MessageTyping messageTyping) {
		this.messageTyping = messageTyping;
	}
}
