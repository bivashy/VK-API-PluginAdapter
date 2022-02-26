package com.ubivashka.vk.spigot.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.callback.LikeAddRemove;

@Deprecated
public class VKLikeRemoveEvent extends VKLikeUpdateEvent {

	private static final HandlerList handlers = new HandlerList();

	public VKLikeRemoveEvent(LikeAddRemove likeAdd) {
		super(likeAdd);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}
}
