package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.callback.PhotoCommentDelete;

public class VKPhotoCommentDeleteEvent extends AbstractVkEvent {
	private static final HandlerList handlers = new HandlerList();
	private PhotoCommentDelete photoCommentDelete;

	public VKPhotoCommentDeleteEvent(PhotoCommentDelete photoCommentDelete, Integer groupId) {
		super(groupId);
		setPhotoCommentDelete(photoCommentDelete);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}

	public PhotoCommentDelete getPhotoCommentDelete() {
		return photoCommentDelete;
	}

	public void setPhotoCommentDelete(PhotoCommentDelete photoCommentDelete) {
		this.photoCommentDelete = photoCommentDelete;
	}
}
