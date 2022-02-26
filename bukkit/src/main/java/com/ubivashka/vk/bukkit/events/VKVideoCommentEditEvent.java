package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.callback.VideoComment;

public class VKVideoCommentEditEvent extends VKVideoCommentActionEvent {
	private static final HandlerList handlers = new HandlerList();

	public VKVideoCommentEditEvent(VideoComment videoComment) {
		super(videoComment);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}
}
