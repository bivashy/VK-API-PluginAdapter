package com.ubivashka.vk.velocity.events;

import com.vk.api.sdk.objects.callback.GroupChangePhoto;

public class VKGroupChangePhotoEvent extends AbstractVkEvent {
	private GroupChangePhoto photoChange;

	public VKGroupChangePhotoEvent(GroupChangePhoto photoChange, Integer groupId) {
		super(groupId);
		setPhotoChange(photoChange);
	}

	public GroupChangePhoto getPhotoChange() {
		return this.photoChange;
	}

	public void setPhotoChange(GroupChangePhoto photoChange) {
		this.photoChange = photoChange;
	}
}
