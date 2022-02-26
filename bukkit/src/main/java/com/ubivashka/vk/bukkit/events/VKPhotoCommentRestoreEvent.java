package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.callback.PhotoComment;

public class VKPhotoCommentRestoreEvent extends VKPhotoCommentActionEvent {

	private static final HandlerList handlers = new HandlerList();

	public VKPhotoCommentRestoreEvent(PhotoComment photoComment) {
		super(photoComment);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}
}
