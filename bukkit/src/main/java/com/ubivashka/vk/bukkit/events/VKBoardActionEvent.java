package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.Event;

import com.vk.api.sdk.objects.board.TopicComment;

public abstract class VKBoardActionEvent extends Event {
	protected TopicComment topicComment;

	public VKBoardActionEvent(TopicComment topicComment) {
		setTopicComment(topicComment);
	}

	public TopicComment getTopicComment() {
		return this.topicComment;
	}

	public void setTopicComment(TopicComment topicComment) {
		this.topicComment = topicComment;
	}
}
