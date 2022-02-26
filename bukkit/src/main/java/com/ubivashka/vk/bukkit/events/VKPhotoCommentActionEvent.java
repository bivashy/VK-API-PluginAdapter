package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.Event;

import com.vk.api.sdk.objects.callback.PhotoComment;

public abstract class VKPhotoCommentActionEvent extends Event {
	protected PhotoComment photoComment;

	public VKPhotoCommentActionEvent(PhotoComment photoComment) {
		setPhotoComment(photoComment);
	}

	public PhotoComment getPhotoComment() {
		return this.photoComment;
	}

	public void setPhotoComment(PhotoComment photoComment) {
		this.photoComment = photoComment;
	}
}
