package com.ubivashka.vk.bukkit.events;

import com.vk.api.sdk.objects.callback.MessageAllow;

public class VKMessageAllowEvent extends AbstractVkEvent {
	
	private MessageAllow messageAllow;

	public VKMessageAllowEvent(MessageAllow messageAllow, Integer groupId) {
		super(groupId);
		this.setMessageAllow(messageAllow);
	}

	public MessageAllow getMessageAllow() {
		return messageAllow;
	}

	public Integer getUser() {
		return messageAllow.getUserId();
	}

	private void setMessageAllow(MessageAllow messageAllow) {
		this.messageAllow = messageAllow;
	}
}
