package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.GroupOfficersEdit;

import net.md_5.bungee.api.plugin.Event;

public class VKGroupOfficersEditEvent extends Event {
	private GroupOfficersEdit officersEdit;

	public VKGroupOfficersEditEvent(GroupOfficersEdit officersEdit) {
		setOfficersEdit(officersEdit);
	}

	public GroupOfficersEdit getOfficersEdit() {
		return this.officersEdit;
	}

	public void setOfficersEdit(GroupOfficersEdit officersEdit) {
		this.officersEdit = officersEdit;
	}
}
