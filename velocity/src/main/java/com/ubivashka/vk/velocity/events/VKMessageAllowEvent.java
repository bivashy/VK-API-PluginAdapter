package com.ubivashka.vk.velocity.events;

import com.vk.api.sdk.objects.callback.MessageAllow;

public class VKMessageAllowEvent extends AbstractVkEvent {
	private MessageAllow messageAllow;

	public VKMessageAllowEvent(MessageAllow messageAllow, Integer groupId) {
		super(groupId);
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
