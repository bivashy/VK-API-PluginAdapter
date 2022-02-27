package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.wall.WallComment;

public class VKPostReplyEditEvent extends VKPostReplyActionEvent {

	public VKPostReplyEditEvent(WallComment postComment, Integer groupId) {
		super(postComment, groupId);
	}

}
