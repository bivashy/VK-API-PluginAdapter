package com.ubivashka.vk.velocity.events;

import java.util.Objects;

import com.velocitypowered.api.event.ResultedEvent;
import com.velocitypowered.api.event.ResultedEvent.GenericResult;
import com.vk.api.sdk.objects.messages.Message;

public abstract class VKMessageUpdateEvent extends AbstractVkEvent implements ResultedEvent<GenericResult> {
	private GenericResult result = GenericResult.allowed();

	private Message message;

	public VKMessageUpdateEvent(Message message, Integer groupId) {
		super(groupId);
		setMessage(message);
	}

	@Override
	public GenericResult getResult() {
		return result;
	}

	@Override
	public void setResult(GenericResult result) {
		this.result = Objects.requireNonNull(result);
	}

	public Message getMessage() {
		return this.message;
	}

	public Integer getUserId() {
		return this.message.getFromId();
	}

	public Integer getPeer() {
		return this.message.getPeerId();
	}

	private void setMessage(Message message) {
		this.message = message;
	}
}
