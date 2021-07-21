package com.ubivashka.vk.bungee.events;

import com.ubivashka.vk.VKEvent;
import com.vk.api.sdk.objects.video.Video;

import net.md_5.bungee.api.plugin.Event;

public class VKVideoNewEvent extends Event implements VKEvent {
	private Video video;

	public VKVideoNewEvent(Video video) {
		setVideo(video);
	}

	public Video getVideo() {
		return this.video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}
}
