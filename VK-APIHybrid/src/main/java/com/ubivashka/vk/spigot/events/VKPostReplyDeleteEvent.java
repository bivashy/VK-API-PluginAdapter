package com.ubivashka.vk.spigot.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.ubivashka.vk.VKEvent;
import com.vk.api.sdk.objects.callback.WallCommentDelete;

public class VKPostReplyDeleteEvent extends Event implements VKEvent {
	private static final HandlerList handlers = new HandlerList();
	private WallCommentDelete postCommentDelete;

	public VKPostReplyDeleteEvent(WallCommentDelete postCommentDelete) {
		super(true);
		setPostCommentDelete(postCommentDelete);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}

	public WallCommentDelete getPostCommentDelete() {
		return postCommentDelete;
	}

	public void setPostCommentDelete(WallCommentDelete postCommentDelete) {
		this.postCommentDelete = postCommentDelete;
	}
}
