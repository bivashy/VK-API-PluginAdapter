package com.ubivashka.vk.spigot.events;

import org.bukkit.event.Event;

import com.vk.api.sdk.objects.callback.LikeAddRemove;

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
