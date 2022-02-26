package com.ubivashka.vk.spigot.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.callback.GroupOfficersEdit;

@Deprecated
public class VKGroupOfficersEditEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private GroupOfficersEdit officersEdit;

	public VKGroupOfficersEditEvent(GroupOfficersEdit officersEdit) {
		super(true);
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
