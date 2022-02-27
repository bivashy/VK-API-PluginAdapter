package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.LikeAddRemove;

public class VKLikeRemoveEvent extends VKLikeUpdateEvent {

	public VKLikeRemoveEvent(LikeAddRemove likeAdd, Integer groupId) {
		super(likeAdd, groupId);
	}

}
