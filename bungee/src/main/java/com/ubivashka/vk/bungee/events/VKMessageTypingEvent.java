package com.ubivashka.vk.bungee.events;

import com.ubivashka.vk.api.parsers.objects.MessageTyping;

public class VKMessageTypingEvent extends AbstractVkEvent {
	private MessageTyping messageTyping;

	public VKMessageTypingEvent(MessageTyping message, Integer groupId) {
		super(groupId);
		setMessageTyping(message);
	}

	public MessageTyping getMessageTyping() {
		return this.messageTyping;
	}

	public String getState() {
		return this.messageTyping.getState();
	}

	public Integer getToId() {
		return this.messageTyping.getToId();
	}

	public Integer getFromId() {
		return this.messageTyping.getFromId();
	}

	public void setMessageTyping(MessageTyping messageTyping) {
		this.messageTyping = messageTyping;
	}
}
