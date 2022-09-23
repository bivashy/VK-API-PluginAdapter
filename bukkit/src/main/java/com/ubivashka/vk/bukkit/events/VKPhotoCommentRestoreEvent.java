package com.ubivashka.vk.bukkit.events;

import com.vk.api.sdk.objects.callback.PhotoComment;

public class VKPhotoCommentRestoreEvent extends VKPhotoCommentActionEvent {

	

	public VKPhotoCommentRestoreEvent(PhotoComment photoComment, Integer groupId) {
		super(photoComment, groupId);
	}
}
