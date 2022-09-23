package com.ubivashka.vk.bukkit.events;

import com.vk.api.sdk.objects.photos.Photo;

public class VKPhotoNewEvent extends AbstractVkEvent {
	
	private Photo photo;

	public VKPhotoNewEvent(Photo photo, Integer groupId) {
		super(groupId);
		setPhoto(photo);
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
}
