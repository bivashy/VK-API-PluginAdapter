package com.ubivashka.vk.velocity.events;

import com.vk.api.sdk.objects.callback.WallCommentDelete;

public class VKPostReplyDeleteEvent extends AbstractVkEvent {
	private WallCommentDelete postCommentDelete;

	public VKPostReplyDeleteEvent(WallCommentDelete postCommentDelete, Integer groupId) {
		super(groupId);
		setPostCommentDelete(postCommentDelete);
	}

	public WallCommentDelete getPostCommentDelete() {
		return this.postCommentDelete;
	}

	public void setPostCommentDelete(WallCommentDelete postCommentDelete) {
		this.postCommentDelete = postCommentDelete;
	}
}
