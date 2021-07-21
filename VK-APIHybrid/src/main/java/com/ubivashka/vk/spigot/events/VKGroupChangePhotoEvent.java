package com.ubivashka.vk.spigot.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.ubivashka.vk.VKEvent;
import com.vk.api.sdk.objects.callback.GroupChangePhoto;

public class VKGroupChangePhotoEvent extends Event implements VKEvent {
	private static final HandlerList handlers = new HandlerList();
	private GroupChangePhoto photoChange;

	public VKGroupChangePhotoEvent(GroupChangePhoto photoChange) {
		super(true);
		setPhotoChange(photoChange);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}

	public GroupChangePhoto getPhotoChange() {
		return photoChange;
	}

	public void setPhotoChange(GroupChangePhoto photoChange) {
		this.photoChange = photoChange;
	}

}
