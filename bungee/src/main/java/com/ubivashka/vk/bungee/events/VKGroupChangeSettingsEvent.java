package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.GroupChangeSettings;

public class VKGroupChangeSettingsEvent extends AbstractVkEvent {
	private GroupChangeSettings settings;

	public VKGroupChangeSettingsEvent(GroupChangeSettings settings, Integer groupId) {
		super(groupId);
		setSettings(settings);
	}

	public GroupChangeSettings getSettings() {
		return this.settings;
	}

	public void setSettings(GroupChangeSettings settings) {
		this.settings = settings;
	}
}
