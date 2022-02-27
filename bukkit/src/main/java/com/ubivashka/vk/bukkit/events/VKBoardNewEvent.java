package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.board.TopicComment;

public class VKBoardNewEvent extends VKBoardActionEvent {

	private static final HandlerList handlers = new HandlerList();

	public VKBoardNewEvent(TopicComment topicComment, Integer groupId) {
		super(topicComment, groupId);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}
}
