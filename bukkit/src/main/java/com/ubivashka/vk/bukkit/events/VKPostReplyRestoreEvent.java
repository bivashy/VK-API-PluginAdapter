package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.wall.WallComment;

public class VKPostReplyRestoreEvent extends VKPostReplyActionEvent {
	private static final HandlerList handlers = new HandlerList();

	public VKPostReplyRestoreEvent(WallComment postComment, Integer groupId) {
		super(postComment, groupId);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}
}
