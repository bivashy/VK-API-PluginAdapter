package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.board.TopicComment;

import net.md_5.bungee.api.plugin.Event;

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
