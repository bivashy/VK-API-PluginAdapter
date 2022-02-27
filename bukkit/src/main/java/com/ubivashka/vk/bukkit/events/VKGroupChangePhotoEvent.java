package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.callback.GroupChangePhoto;

public class VKGroupChangePhotoEvent extends AbstractVkEvent {
	private static final HandlerList handlers = new HandlerList();
	private GroupChangePhoto photoChange;

	public VKGroupChangePhotoEvent(GroupChangePhoto photoChange, Integer groupId) {
		super(groupId);
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
