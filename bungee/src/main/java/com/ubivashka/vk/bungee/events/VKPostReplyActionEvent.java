package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.wall.WallComment;

public abstract class VKPostReplyActionEvent extends AbstractVkEvent {
	private WallComment postComment;

	public VKPostReplyActionEvent(WallComment postComment, Integer groupId) {
		super(groupId);
		setPostComment(postComment);
	}

	public WallComment getPostComment() {
		return this.postComment;
	}

	public void setPostComment(WallComment postComment) {
		this.postComment = postComment;
	}
}
