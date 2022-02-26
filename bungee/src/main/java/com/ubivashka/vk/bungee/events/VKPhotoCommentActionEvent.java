package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.PhotoComment;

import net.md_5.bungee.api.plugin.Event;

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
