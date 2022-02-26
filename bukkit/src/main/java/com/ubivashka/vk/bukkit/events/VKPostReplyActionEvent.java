package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.Event;

import com.vk.api.sdk.objects.wall.WallComment;

public abstract class VKPostReplyActionEvent extends Event {
	private WallComment postComment;

	public VKPostReplyActionEvent(WallComment postComment) {
		setPostComment(postComment);
	}

	public WallComment getPostComment() {
		return this.postComment;
	}

	public void setPostComment(WallComment postComment) {
		this.postComment = postComment;
	}
}
