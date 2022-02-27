package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.PhotoComment;

public class VKPhotoCommentEvent extends VKPhotoCommentActionEvent {

	public VKPhotoCommentEvent(PhotoComment photoComment, Integer groupId) {
		super(photoComment, groupId);
	}

}
