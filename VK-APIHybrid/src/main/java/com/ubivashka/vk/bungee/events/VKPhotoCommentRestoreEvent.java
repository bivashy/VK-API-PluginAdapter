package com.ubivashka.vk.bungee.events;

import com.ubivashka.vk.VKEvent;
import com.vk.api.sdk.objects.callback.PhotoComment;

import net.md_5.bungee.api.plugin.Event;

public class VKPhotoCommentRestoreEvent extends Event implements VKEvent {
	private PhotoComment photoComment;

	public VKPhotoCommentRestoreEvent(PhotoComment photoComment) {
		setPhotoComment(photoComment);
	}

	public PhotoComment getPhotoComment() {
		return this.photoComment;
	}

	public void setPhotoComment(PhotoComment photoComment) {
		this.photoComment = photoComment;
	}
}
