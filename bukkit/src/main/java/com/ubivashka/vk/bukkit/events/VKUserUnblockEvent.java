package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.callback.UserUnblock;

public class VKUserUnblockEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private UserUnblock unblock;

	public VKUserUnblockEvent(UserUnblock unblock) {
		super(true);
		setUnblock(unblock);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}

	public UserUnblock getUnblock() {
		return unblock;
	}

	public void setUnblock(UserUnblock block) {
		this.unblock = block;
	}
}
