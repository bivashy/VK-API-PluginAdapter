package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.photos.Photo;

import net.md_5.bungee.api.plugin.Event;

public class VKPhotoNewEvent extends Event {
	private Photo photo;

	public VKPhotoNewEvent(Photo photo) {
		setPhoto(photo);
	}

	public Photo getPhoto() {
		return this.photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
}
