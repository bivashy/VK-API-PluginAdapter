package com.ubivashka.vk.bukkit.events;

import com.ubivashka.vk.api.parsers.objects.MessageTyping;

public class VKMessageTypingEvent extends AbstractVkEvent {
	
	private MessageTyping messageTyping;

	public VKMessageTypingEvent(MessageTyping message, Integer groupId) {
		super(groupId);
		setMessageTyping(message);
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
