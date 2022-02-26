package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.MessageAllow;

import net.md_5.bungee.api.plugin.Event;

public class VKMessageAllowEvent extends Event {
	private MessageAllow messageAllow;

	public VKMessageAllowEvent(MessageAllow messageAllow) {
		setMessageAllow(messageAllow);
	}

	public MessageAllow getMessageAllow() {
		return this.messageAllow;
	}

	public Integer getUser() {
		return this.messageAllow.getUserId();
	}

	private void setMessageAllow(MessageAllow messageAllow) {
		this.messageAllow = messageAllow;
	}
}
