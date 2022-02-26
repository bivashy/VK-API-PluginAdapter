package com.ubivashka.vk.spigot.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.board.TopicComment;

@Deprecated
public class VKBoardEditEvent extends VKBoardActionEvent {

	private static final HandlerList handlers = new HandlerList();

	public VKBoardEditEvent(TopicComment topicComment) {
		super(topicComment);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}
}
