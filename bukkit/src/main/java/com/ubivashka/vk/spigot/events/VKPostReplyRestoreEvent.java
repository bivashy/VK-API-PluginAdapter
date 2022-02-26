package com.ubivashka.vk.spigot.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.wall.WallComment;

@Deprecated
public class VKPostReplyRestoreEvent extends VKPostReplyActionEvent {
	private static final HandlerList handlers = new HandlerList();

	public VKPostReplyRestoreEvent(WallComment postComment) {
		super(postComment);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}
}
