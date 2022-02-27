package com.ubivashka.vk.velocity.events;

import com.ubivashka.vk.event.VkGroupEvent;
import com.ubivashka.vk.velocity.VelocityVkApiPlugin;

public abstract class AbstractVkEvent implements VkGroupEvent {
	private static final VelocityVkApiPlugin PLUGIN = VelocityVkApiPlugin.getInstance();
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
