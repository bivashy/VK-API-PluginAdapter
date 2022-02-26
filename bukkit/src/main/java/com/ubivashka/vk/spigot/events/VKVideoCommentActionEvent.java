package com.ubivashka.vk.spigot.events;

import org.bukkit.event.Event;

import com.vk.api.sdk.objects.callback.VideoComment;

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
