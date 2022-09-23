package com.ubivashka.vk.bukkit.events;

import com.vk.api.sdk.objects.wall.WallComment;

public class VKPostReplyEvent extends VKPostReplyActionEvent {

	

	public VKPostReplyEvent(WallComment postComment, Integer groupId) {
		super(postComment, groupId);
	}
}
