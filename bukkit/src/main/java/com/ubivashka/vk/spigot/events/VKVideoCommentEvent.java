package com.ubivashka.vk.spigot.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.callback.VideoComment;

@Deprecated
public class VKVideoCommentEvent extends VKVideoCommentActionEvent {
	private static final HandlerList handlers = new HandlerList();

	public VKVideoCommentEvent(VideoComment videoComment) {
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
