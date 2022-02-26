package com.ubivashka.vk.spigot.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.callback.PhotoComment;

@Deprecated
public class VKPhotoCommentEditEvent extends VKPhotoCommentActionEvent {
	private static final HandlerList handlers = new HandlerList();

	public VKPhotoCommentEditEvent(PhotoComment photoComment) {
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
