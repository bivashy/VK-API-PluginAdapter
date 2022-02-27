package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.callback.UserUnblock;

public class VKUserUnblockEvent extends AbstractVkEvent {
	private static final HandlerList handlers = new HandlerList();
	private UserUnblock unblock;

	public VKUserUnblockEvent(UserUnblock unblock, Integer groupId) {
		super(groupId);
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
