package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.callback.GroupJoin;

public class VKUserGroupJoinEvent extends AbstractVkEvent {
	private static final HandlerList handlers = new HandlerList();
	private GroupJoin join;

	public VKUserGroupJoinEvent(GroupJoin join, Integer groupId) {
		super(groupId);
		setJoin(join);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}

	public GroupJoin getJoin() {
		return join;
	}

	public void setJoin(GroupJoin join) {
		this.join = join;
	}

}
