package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.GroupJoin;

import net.md_5.bungee.api.plugin.Event;

public class VKUserGroupJoinEvent extends Event {
	private GroupJoin join;

	public VKUserGroupJoinEvent(GroupJoin join) {
		setJoin(join);
	}

	public GroupJoin getJoin() {
		return this.join;
	}

	public void setJoin(GroupJoin join) {
		this.join = join;
	}
}
