package com.ubivashka.vk.bukkit.events;

import com.vk.api.sdk.objects.board.TopicComment;

public class VKBoardEditEvent extends VKBoardActionEvent {

	

	public VKBoardEditEvent(TopicComment topicComment, Integer groupId) {
		super(topicComment, groupId);
	}
}
