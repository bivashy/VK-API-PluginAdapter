package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.GroupLeave;

import net.md_5.bungee.api.plugin.Event;

public class VKUserGroupLeaveEvent extends Event {
	private GroupLeave leave;

	public VKUserGroupLeaveEvent(GroupLeave leave) {
		setLeave(leave);
	}

	public GroupLeave getLeave() {
		return this.leave;
	}

	public void setLeave(GroupLeave leave) {
		this.leave = leave;
	}
}
