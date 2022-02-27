package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.PhotoComment;

public abstract class VKPhotoCommentActionEvent extends AbstractVkEvent {
	protected PhotoComment photoComment;

	public VKPhotoCommentActionEvent(PhotoComment photoComment, Integer groupId) {
		super(groupId);
		setPhotoComment(photoComment);
	}

	public PhotoComment getPhotoComment() {
		return this.photoComment;
	}

	public void setPhotoComment(PhotoComment photoComment) {
		this.photoComment = photoComment;
	}
}
