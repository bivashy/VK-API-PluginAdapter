package com.ubivashka.vk.bungee.events;

import com.ubivashka.vk.VKEvent;
import com.vk.api.sdk.objects.callback.GroupChangePhoto;

import net.md_5.bungee.api.plugin.Event;

public class VKGroupChangePhotoEvent extends Event implements VKEvent {
	private GroupChangePhoto photoChange;

	public VKGroupChangePhotoEvent(GroupChangePhoto photoChange) {
		setPhotoChange(photoChange);
	}

	public GroupChangePhoto getPhotoChange() {
		return this.photoChange;
	}

	public void setPhotoChange(GroupChangePhoto photoChange) {
		this.photoChange = photoChange;
	}
}
