package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.callback.MarketComment;

public class VKMarketCommentEditEvent extends VKMarketCommentActionEvent {
	private static final HandlerList handlers = new HandlerList();

	public VKMarketCommentEditEvent(MarketComment comment) {
		super(comment);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}
}
