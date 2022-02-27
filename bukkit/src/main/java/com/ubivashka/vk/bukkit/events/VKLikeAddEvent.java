package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.callback.LikeAddRemove;

public class VKLikeAddEvent extends VKLikeUpdateEvent {

	private static final HandlerList handlers = new HandlerList();

	public VKLikeAddEvent(LikeAddRemove likeAdd, Integer groupId) {
		super(likeAdd, groupId);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}

}
