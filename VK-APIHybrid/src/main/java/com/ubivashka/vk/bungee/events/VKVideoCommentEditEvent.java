package com.ubivashka.vk.bungee.events;

import com.ubivashka.vk.VKEvent;
import com.vk.api.sdk.objects.callback.VideoComment;

import net.md_5.bungee.api.plugin.Event;

public class VKVideoCommentEditEvent extends Event implements VKEvent {
	private VideoComment videoComment;

	public VKVideoCommentEditEvent(VideoComment videoComment) {
		setVideoComment(videoComment);
	}

	public VideoComment getVideoComment() {
		return this.videoComment;
	}

	public void setVideoComment(VideoComment videoComment) {
		this.videoComment = videoComment;
	}
}
