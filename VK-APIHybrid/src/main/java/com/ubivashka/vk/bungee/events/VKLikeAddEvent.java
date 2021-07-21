package com.ubivashka.vk.bungee.events;

import com.ubivashka.vk.VKEvent;
import com.vk.api.sdk.objects.callback.LikeAddRemove;

import net.md_5.bungee.api.plugin.Event;

public class VKLikeAddEvent extends Event implements VKEvent {
	private LikeAddRemove likeAdd;

	public VKLikeAddEvent(LikeAddRemove likeAdd) {
		setLikeAdd(likeAdd);
	}

	public LikeAddRemove getLikeAdd() {
		return this.likeAdd;
	}

	public void setLikeAdd(LikeAddRemove likeAdd) {
		this.likeAdd = likeAdd;
	}
}
