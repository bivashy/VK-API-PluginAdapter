package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.wall.WallComment;

public class VKPostReplyEditEvent extends VKPostReplyActionEvent {
	private static final HandlerList handlers = new HandlerList();

	public VKPostReplyEditEvent(WallComment postComment) {
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
