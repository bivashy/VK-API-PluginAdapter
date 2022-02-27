package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.photos.Photo;

public class VKPhotoNewEvent extends AbstractVkEvent {
	private static final HandlerList handlers = new HandlerList();
	private Photo photo;

	public VKPhotoNewEvent(Photo photo, Integer groupId) {
		super(groupId);
		setPhoto(photo);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
}
