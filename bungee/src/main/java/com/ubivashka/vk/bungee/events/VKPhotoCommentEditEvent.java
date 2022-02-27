package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.PhotoComment;

public class VKPhotoCommentEditEvent extends VKPhotoCommentActionEvent {

	public VKPhotoCommentEditEvent(PhotoComment photoComment, Integer groupId) {
		super(photoComment, groupId);
	}

}
