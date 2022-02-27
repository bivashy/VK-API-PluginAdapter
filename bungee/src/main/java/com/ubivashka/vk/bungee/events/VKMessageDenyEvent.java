package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.MessageDeny;

public class VKMessageDenyEvent extends AbstractVkEvent {
	private MessageDeny messageDeny;

	public VKMessageDenyEvent(MessageDeny messageAllow, Integer groupId) {
		super(groupId);
		setMessageDeny(messageAllow);
	}

	public MessageDeny getMessageDeny() {
		return this.messageDeny;
	}

	public Integer getUser() {
		return this.messageDeny.getUserId();
	}

	private void setMessageDeny(MessageDeny messageAllow) {
		this.messageDeny = messageAllow;
	}
}
