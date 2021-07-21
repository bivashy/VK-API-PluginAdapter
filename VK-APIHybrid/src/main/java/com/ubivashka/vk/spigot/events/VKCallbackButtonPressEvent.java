package com.ubivashka.vk.spigot.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.ubivashka.vk.VKEvent;
import com.ubivashka.vk.callback.objects.CallbackButtonEvent;

public class VKCallbackButtonPressEvent extends Event implements VKEvent {
	private static final HandlerList handlers = new HandlerList();
	private CallbackButtonEvent buttonEvent;

	public VKCallbackButtonPressEvent(CallbackButtonEvent buttonEvent) {
		super(true);
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