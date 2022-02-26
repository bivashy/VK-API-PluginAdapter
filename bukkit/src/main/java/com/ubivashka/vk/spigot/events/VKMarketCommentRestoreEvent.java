package com.ubivashka.vk.spigot.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.callback.MarketComment;

@Deprecated
public class VKMarketCommentRestoreEvent extends VKMarketCommentActionEvent {
	private static final HandlerList handlers = new HandlerList();

	public VKMarketCommentRestoreEvent(MarketComment comment) {
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
