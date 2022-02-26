package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.wall.WallComment;

import net.md_5.bungee.api.plugin.Event;

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
