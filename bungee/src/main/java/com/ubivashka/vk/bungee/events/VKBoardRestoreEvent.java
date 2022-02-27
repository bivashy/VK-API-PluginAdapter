package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.board.TopicComment;

public class VKBoardRestoreEvent extends VKBoardActionEvent {

	public VKBoardRestoreEvent(TopicComment topicComment, Integer groupId) {
		super(topicComment, groupId);
	}

}
