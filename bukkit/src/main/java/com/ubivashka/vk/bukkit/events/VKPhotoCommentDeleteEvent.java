package com.ubivashka.vk.bukkit.events;

import com.vk.api.sdk.objects.callback.PhotoCommentDelete;

public class VKPhotoCommentDeleteEvent extends AbstractVkEvent {
	
	private PhotoCommentDelete photoCommentDelete;

	public VKPhotoCommentDeleteEvent(PhotoCommentDelete photoCommentDelete, Integer groupId) {
		super(groupId);
		setPhotoCommentDelete(photoCommentDelete);
	}

	public PhotoCommentDelete getPhotoCommentDelete() {
		return photoCommentDelete;
	}

	public void setPhotoCommentDelete(PhotoCommentDelete photoCommentDelete) {
		this.photoCommentDelete = photoCommentDelete;
	}
}
