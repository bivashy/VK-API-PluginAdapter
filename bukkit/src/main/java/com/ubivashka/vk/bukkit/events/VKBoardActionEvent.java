package com.ubivashka.vk.bukkit.events;

import com.vk.api.sdk.objects.board.TopicComment;

public abstract class VKBoardActionEvent extends AbstractVkEvent {
	protected TopicComment topicComment;

	public VKBoardActionEvent(TopicComment topicComment, Integer groupId) {
		super(groupId);
		setTopicComment(topicComment);
	}

	public TopicComment getTopicComment() {
		return this.topicComment;
	}

	public void setTopicComment(TopicComment topicComment) {
		this.topicComment = topicComment;
	}
}
