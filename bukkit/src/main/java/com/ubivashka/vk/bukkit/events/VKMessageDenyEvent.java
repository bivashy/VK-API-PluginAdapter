package com.ubivashka.vk.bukkit.events;

import com.vk.api.sdk.objects.callback.MessageDeny;

public class VKMessageDenyEvent extends AbstractVkEvent {
	
	private MessageDeny messageDeny;

	public VKMessageDenyEvent(MessageDeny messageAllow, Integer groupId) {
		super(groupId);
		this.setMessageDeny(messageAllow);
	}

	public MessageDeny getMessageDeny() {
		return messageDeny;
	}

	public Integer getUser() {
		return messageDeny.getUserId();
	}

	private void setMessageDeny(MessageDeny messageAllow) {
		this.messageDeny = messageAllow;
	}
}
