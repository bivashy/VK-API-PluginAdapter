package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.GroupLeave;

public class VKUserGroupLeaveEvent extends AbstractVkEvent {
	private GroupLeave leave;

	public VKUserGroupLeaveEvent(GroupLeave leave, Integer groupId) {
		super(groupId);
		setLeave(leave);
	}

	public GroupLeave getLeave() {
		return this.leave;
	}

	public void setLeave(GroupLeave leave) {
		this.leave = leave;
	}
}
