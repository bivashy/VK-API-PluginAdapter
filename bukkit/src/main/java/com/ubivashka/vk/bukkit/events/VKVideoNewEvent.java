package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.video.Video;

public class VKVideoNewEvent extends AbstractVkEvent {
	private static final HandlerList handlers = new HandlerList();
	private Video video;

	public VKVideoNewEvent(Video video, Integer groupId) {
		super(groupId);
		setVideo(video);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}
}
