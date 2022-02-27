package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.callback.GroupOfficersEdit;

public class VKGroupOfficersEditEvent extends AbstractVkEvent {
	private static final HandlerList handlers = new HandlerList();
	private GroupOfficersEdit officersEdit;

	public VKGroupOfficersEditEvent(GroupOfficersEdit officersEdit, Integer groupId) {
		super(groupId);
		setOfficersEdit(officersEdit);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}

	public GroupOfficersEdit getOfficersEdit() {
		return officersEdit;
	}

	public void setOfficersEdit(GroupOfficersEdit officersEdit) {
		this.officersEdit = officersEdit;
	}

}
