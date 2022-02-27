package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.HandlerList;

import com.ubivashka.vk.api.parsers.objects.CallbackButtonEvent;

public class VKCallbackButtonPressEvent extends AbstractVkEvent {
	private static final HandlerList handlers = new HandlerList();
	private CallbackButtonEvent buttonEvent;

	public VKCallbackButtonPressEvent(CallbackButtonEvent buttonEvent, Integer groupId) {
		super(groupId);
		this.buttonEvent = buttonEvent;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}

	public CallbackButtonEvent getButtonEvent() {
		return buttonEvent;
	}

	public void setButtonEvent(CallbackButtonEvent buttonEvent) {
		this.buttonEvent = buttonEvent;
	}

}