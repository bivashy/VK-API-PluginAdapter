package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.callback.GroupChangeSettings;

public class VKGroupChangeSettingsEvent extends AbstractVkEvent {
	private static final HandlerList handlers = new HandlerList();
	private GroupChangeSettings settings;

	public VKGroupChangeSettingsEvent(GroupChangeSettings settings, Integer groupId) {
		super(groupId);
		setSettings(settings);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}

	public GroupChangeSettings getSettings() {
		return settings;
	}

	public void setSettings(GroupChangeSettings settings) {
		this.settings = settings;
	}
}
