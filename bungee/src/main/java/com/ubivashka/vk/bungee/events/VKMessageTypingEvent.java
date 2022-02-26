package com.ubivashka.vk.bungee.events;

import com.ubivashka.vk.api.parsers.objects.MessageTyping;

import net.md_5.bungee.api.plugin.Event;

public class VKMessageTypingEvent extends Event {
	private MessageTyping messageTyping;

	public VKMessageTypingEvent(MessageTyping message) {
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
