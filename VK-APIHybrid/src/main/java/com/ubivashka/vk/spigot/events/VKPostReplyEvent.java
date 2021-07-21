package com.ubivashka.vk.spigot.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.ubivashka.vk.VKEvent;
import com.vk.api.sdk.objects.wall.WallComment;

public class VKPostReplyEvent extends Event implements VKEvent {
	private static final HandlerList handlers = new HandlerList();
	private WallComment postComment;

	public VKPostReplyEvent(WallComment postComment) {
		super(true);
		setPostComment(postComment);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}

	public WallComment getPostComment() {
		return postComment;
	}

	public void setPostComment(WallComment postComment) {
		this.postComment = postComment;
	}
}
