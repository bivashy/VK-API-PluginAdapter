package com.ubivashka.vk.bukkit.events;

import com.vk.api.sdk.objects.callback.LikeAddRemove;

public abstract class VKLikeUpdateEvent extends AbstractVkEvent {
	protected LikeAddRemove likeAdd;

	public VKLikeUpdateEvent(LikeAddRemove likeAdd, Integer groupId) {
		super(groupId);
		setLikeAdd(likeAdd);
	}

	public LikeAddRemove getLikeAdd() {
		return this.likeAdd;
	}

	public void setLikeAdd(LikeAddRemove likeAdd) {
		this.likeAdd = likeAdd;
	}
}
