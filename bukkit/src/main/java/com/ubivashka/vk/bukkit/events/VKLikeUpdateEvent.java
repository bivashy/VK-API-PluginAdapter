package com.ubivashka.vk.bukkit.events;

import com.vk.api.sdk.objects.callback.LikeAddRemove;

public abstract class VKLikeUpdateEvent extends AbstractVkEvent {
	protected LikeAddRemove likeInfo;

	public VKLikeUpdateEvent(LikeAddRemove likeInfo, Integer groupId) {
		super(groupId);
		setLikeInfo(likeInfo);
	}

	public LikeAddRemove getLikeInfo() {
		return this.likeInfo;
	}

	public void setLikeInfo(LikeAddRemove likeInfo) {
		this.likeInfo = likeInfo;
	}
}
