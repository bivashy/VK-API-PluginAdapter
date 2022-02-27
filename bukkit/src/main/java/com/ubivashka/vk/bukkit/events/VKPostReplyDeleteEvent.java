package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.callback.WallCommentDelete;

public class VKPostReplyDeleteEvent extends AbstractVkEvent {
	private static final HandlerList handlers = new HandlerList();
	private WallCommentDelete postCommentDelete;

	public VKPostReplyDeleteEvent(WallCommentDelete postCommentDelete, Integer groupId) {
		super(groupId);
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
