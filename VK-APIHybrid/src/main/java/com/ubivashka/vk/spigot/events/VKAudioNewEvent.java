package com.ubivashka.vk.spigot.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.ubivashka.vk.VKEvent;
import com.vk.api.sdk.objects.audio.Audio;

public class VKAudioNewEvent extends Event implements VKEvent {
	private static final HandlerList handlers = new HandlerList();
	private Audio audio;

	public VKAudioNewEvent(Audio audio) {
		super(true);
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
