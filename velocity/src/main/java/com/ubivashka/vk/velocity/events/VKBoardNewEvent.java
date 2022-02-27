package com.ubivashka.vk.velocity.events;

import com.vk.api.sdk.objects.board.TopicComment;

public class VKBoardNewEvent extends VKBoardActionEvent {

	public VKBoardNewEvent(TopicComment topicComment, Integer groupId) {
		super(topicComment, groupId);
	}

}
