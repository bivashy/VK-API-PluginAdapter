package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.VideoComment;

import net.md_5.bungee.api.plugin.Event;

public abstract class VKVideoCommentActionEvent extends Event {
	private VideoComment videoComment;

	public VKVideoCommentActionEvent(VideoComment videoComment) {
		setVideoComment(videoComment);
	}

	public VideoComment getVideoComment() {
		return this.videoComment;
	}

	public void setVideoComment(VideoComment videoComment) {
		this.videoComment = videoComment;
	}
}
