package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.LikeAddRemove;

import net.md_5.bungee.api.plugin.Event;

public abstract class VKLikeUpdateEvent extends Event {
	protected LikeAddRemove likeAdd;

	public VKLikeUpdateEvent(LikeAddRemove likeAdd) {
		setLikeAdd(likeAdd);
	}

	public LikeAddRemove getLikeAdd() {
		return this.likeAdd;
	}

	public void setLikeAdd(LikeAddRemove likeAdd) {
		this.likeAdd = likeAdd;
	}
}
