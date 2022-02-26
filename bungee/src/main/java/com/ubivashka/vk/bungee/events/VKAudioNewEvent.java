package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.audio.Audio;

import net.md_5.bungee.api.plugin.Event;

public class VKAudioNewEvent extends Event {
	private Audio audio;

	public VKAudioNewEvent(Audio audio) {
		setAudio(audio);
	}

	public Audio getAudio() {
		return this.audio;
	}

	public void setAudio(Audio audio) {
		this.audio = audio;
	}
}
