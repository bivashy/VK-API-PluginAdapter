package com.ubivashka.vk.bungee.events;

import com.ubivashka.vk.VKEvent;
import com.vk.api.sdk.objects.board.TopicComment;

import net.md_5.bungee.api.plugin.Event;

public class VKBoardEditEvent extends Event implements VKEvent {
	private TopicComment topicComment;

	public VKBoardEditEvent(TopicComment topicComment) {
		setTopicComment(topicComment);
	}

	public TopicComment getTopicComment() {
		return this.topicComment;
	}

	public void setTopicComment(TopicComment topicComment) {
		this.topicComment = topicComment;
	}
}
