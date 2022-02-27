package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.GroupJoin;

public class VKUserGroupJoinEvent extends AbstractVkEvent {
	private GroupJoin join;

	public VKUserGroupJoinEvent(GroupJoin join, Integer groupId) {
		super(groupId);
		setJoin(join);
	}

	public GroupJoin getJoin() {
		return this.join;
	}

	public void setJoin(GroupJoin join) {
		this.join = join;
	}
}
