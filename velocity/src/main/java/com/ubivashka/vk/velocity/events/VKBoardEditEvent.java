package com.ubivashka.vk.velocity.events;

import com.vk.api.sdk.objects.board.TopicComment;

public class VKBoardEditEvent extends VKBoardActionEvent {

	public VKBoardEditEvent(TopicComment topicComment, Integer groupId) {
		super(topicComment, groupId);
	}

}
