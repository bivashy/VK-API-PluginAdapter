package com.ubivashka.vk.velocity.events;

import com.vk.api.sdk.objects.callback.PhotoComment;

public class VKPhotoCommentRestoreEvent extends VKPhotoCommentActionEvent {

	public VKPhotoCommentRestoreEvent(PhotoComment photoComment, Integer groupId) {
		super(photoComment, groupId);
	}

}
