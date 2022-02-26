package com.ubivashka.vk.spigot.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.photos.Photo;

@Deprecated
public class VKPhotoNewEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private Photo photo;

	public VKPhotoNewEvent(Photo photo) {
		super(true);
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
