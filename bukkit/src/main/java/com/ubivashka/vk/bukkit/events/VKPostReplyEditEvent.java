package com.ubivashka.vk.bukkit.events;

import com.vk.api.sdk.objects.wall.WallComment;

public class VKPostReplyEditEvent extends VKPostReplyActionEvent {
	

	public VKPostReplyEditEvent(WallComment postComment, Integer groupId) {
		super(postComment, groupId);
	}
}
