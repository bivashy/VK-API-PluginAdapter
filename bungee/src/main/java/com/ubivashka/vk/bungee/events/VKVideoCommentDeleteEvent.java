package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.VideoCommentDelete;

import net.md_5.bungee.api.plugin.Event;

public class VKVideoCommentDeleteEvent extends Event {
	private VideoCommentDelete videoCommentDelete;

	public VKVideoCommentDeleteEvent(VideoCommentDelete videoCommentDelete) {
		setVideoCommentDelete(videoCommentDelete);
	}

	public VideoCommentDelete getVideoCommentDelete() {
		return this.videoCommentDelete;
	}

	public void setVideoCommentDelete(VideoCommentDelete videoCommentDelete) {
		this.videoCommentDelete = videoCommentDelete;
	}
}
