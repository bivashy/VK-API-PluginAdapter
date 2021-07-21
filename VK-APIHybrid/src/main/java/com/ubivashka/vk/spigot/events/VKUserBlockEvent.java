package com.ubivashka.vk.spigot.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.ubivashka.vk.VKEvent;
import com.vk.api.sdk.objects.callback.UserBlock;

public class VKUserBlockEvent extends Event implements VKEvent {
	private static final HandlerList handlers = new HandlerList();
	private UserBlock block;

	public VKUserBlockEvent(UserBlock block) {
		super(true);
		setBlock(block);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}

	public UserBlock getBlock() {
		return block;
	}

	public void setBlock(UserBlock block) {
		this.block = block;
	}

}
