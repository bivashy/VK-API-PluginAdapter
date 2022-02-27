package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.callback.VideoCommentDelete;

public class VKVideoCommentDeleteEvent extends AbstractVkEvent {
	private static final HandlerList handlers = new HandlerList();
	private VideoCommentDelete videoCommentDelete;

	public VKVideoCommentDeleteEvent(VideoCommentDelete videoCommentDelete, Integer groupId) {
		super(groupId);
		setVideoCommentDelete(videoCommentDelete);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}

	public VideoCommentDelete getVideoCommentDelete() {
		return videoCommentDelete;
	}

	public void setVideoCommentDelete(VideoCommentDelete videoCommentDelete) {
		this.videoCommentDelete = videoCommentDelete;
	}
}
