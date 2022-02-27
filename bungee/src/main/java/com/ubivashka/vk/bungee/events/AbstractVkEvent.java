package com.ubivashka.vk.bungee.events;

import com.ubivashka.vk.bungee.BungeeVkApiPlugin;
import com.ubivashka.vk.event.VkGroupEvent;

import net.md_5.bungee.api.plugin.Event;

public abstract class AbstractVkEvent extends Event implements VkGroupEvent {
	private static final BungeeVkApiPlugin PLUGIN = BungeeVkApiPlugin.getInstance();
	private final Integer groupId;

	public AbstractVkEvent(Integer groupId) {
		this.groupId = groupId;
	}

	@Override
	public int groupId() {
		return groupId;
	}

	public void callEvent() {
		PLUGIN.callEvent(this);
	}
}
