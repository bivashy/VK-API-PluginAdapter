package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.VideoComment;

public class VKVideoCommentEditEvent extends VKVideoCommentActionEvent {

	public VKVideoCommentEditEvent(VideoComment videoComment, Integer groupId) {
		super(videoComment, groupId);
	}

}
