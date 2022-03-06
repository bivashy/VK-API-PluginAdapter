package com.ubivashka.vk.velocity.events;

import com.ubivashka.vk.api.parsers.objects.CallbackButtonEvent;

public class VKCallbackButtonPressEvent extends AbstractVkEvent {
	private CallbackButtonEvent buttonEvent;

	public VKCallbackButtonPressEvent(CallbackButtonEvent buttonEvent, Integer groupId) {
		super(groupId);
		this.buttonEvent = buttonEvent;
	}

	public CallbackButtonEvent getButtonEvent() {
		return this.buttonEvent;
	}

	public void setButtonEvent(CallbackButtonEvent buttonEvent, Integer groupId) {
		this.buttonEvent = buttonEvent;
	}
}
