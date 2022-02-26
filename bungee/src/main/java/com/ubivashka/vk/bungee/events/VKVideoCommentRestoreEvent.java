package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.VideoComment;

public class VKVideoCommentRestoreEvent extends VKVideoCommentActionEvent {

	public VKVideoCommentRestoreEvent(VideoComment videoComment) {
		super(videoComment);
	}

}
