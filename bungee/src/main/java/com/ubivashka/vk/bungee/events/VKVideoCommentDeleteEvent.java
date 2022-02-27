package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.VideoCommentDelete;

public class VKVideoCommentDeleteEvent extends AbstractVkEvent {
	private VideoCommentDelete videoCommentDelete;

	public VKVideoCommentDeleteEvent(VideoCommentDelete videoCommentDelete, Integer groupId) {
		super(groupId);
		setVideoCommentDelete(videoCommentDelete);
	}

	public VideoCommentDelete getVideoCommentDelete() {
		return this.videoCommentDelete;
	}

	public void setVideoCommentDelete(VideoCommentDelete videoCommentDelete) {
		this.videoCommentDelete = videoCommentDelete;
	}
}
