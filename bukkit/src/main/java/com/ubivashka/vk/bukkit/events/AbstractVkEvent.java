package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.ubivashka.vk.bukkit.BukkitVkApiPlugin;
import com.ubivashka.vk.event.VkGroupEvent;

public abstract class AbstractVkEvent extends Event implements VkGroupEvent {
	private static final HandlerList HANDLER_LIST = new HandlerList();
	private static final BukkitVkApiPlugin PLUGIN = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class);
	private final Integer groupId;

	public AbstractVkEvent(Integer groupId) {
		super(true);
		this.groupId = groupId;
	}

	@Override
	public int groupId() {
		return groupId;
	}

	public void callEvent() {
		PLUGIN.callEvent(this);
	}

	public static HandlerList getHandlerList() {
		return HANDLER_LIST;
	}

	@Override
	public final HandlerList getHandlers() {
		return HANDLER_LIST;
	}
}
