package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.wall.WallComment;

public class VKPostReplyRestoreEvent extends VKPostReplyActionEvent {

	public VKPostReplyRestoreEvent(WallComment postComment, Integer groupId) {
		super(postComment, groupId);
	}

}
