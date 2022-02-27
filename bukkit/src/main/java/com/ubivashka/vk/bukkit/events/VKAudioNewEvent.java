package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.audio.Audio;

public class VKAudioNewEvent extends AbstractVkEvent {
	private static final HandlerList handlers = new HandlerList();
	private Audio audio;

	public VKAudioNewEvent(Audio audio, Integer groupId) {
		super(groupId);
		setAudio(audio);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}

	public Audio getAudio() {
		return audio;
	}

	public void setAudio(Audio audio) {
		this.audio = audio;
	}
}
