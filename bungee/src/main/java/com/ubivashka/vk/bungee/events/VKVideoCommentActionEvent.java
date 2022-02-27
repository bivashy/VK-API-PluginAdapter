package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.VideoComment;

public abstract class VKVideoCommentActionEvent extends AbstractVkEvent {
	private VideoComment videoComment;

	public VKVideoCommentActionEvent(VideoComment videoComment, Integer groupId) {
		super(groupId);
		setVideoComment(videoComment);
	}

	public VideoComment getVideoComment() {
		return this.videoComment;
	}

	public void setVideoComment(VideoComment videoComment) {
		this.videoComment = videoComment;
	}
}
