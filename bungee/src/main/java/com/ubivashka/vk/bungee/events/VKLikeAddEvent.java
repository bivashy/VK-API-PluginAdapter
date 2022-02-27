package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.LikeAddRemove;

public class VKLikeAddEvent extends VKLikeUpdateEvent {

	public VKLikeAddEvent(LikeAddRemove likeAdd, Integer groupId) {
		super(likeAdd, groupId);
	}

}
