package com.ubivashka.vk.bungee.events;

import com.ubivashka.vk.api.parsers.objects.CallbackButtonEvent;

import net.md_5.bungee.api.plugin.Event;

public class VKCallbackButtonPressEvent extends Event {
	private CallbackButtonEvent buttonEvent;

	public VKCallbackButtonPressEvent(CallbackButtonEvent buttonEvent) {
		this.buttonEvent = buttonEvent;
	}

	public CallbackButtonEvent getButtonEvent() {
		return this.buttonEvent;
	}

	public void setButtonEvent(CallbackButtonEvent buttonEvent) {
		this.buttonEvent = buttonEvent;
	}
}
