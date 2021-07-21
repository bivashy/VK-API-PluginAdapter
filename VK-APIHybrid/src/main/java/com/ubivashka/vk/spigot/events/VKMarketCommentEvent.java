package com.ubivashka.vk.spigot.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.ubivashka.vk.VKEvent;
import com.vk.api.sdk.objects.callback.MarketComment;

public class VKMarketCommentEvent extends Event implements VKEvent {
	private static final HandlerList handlers = new HandlerList();
	private MarketComment comment;

	public VKMarketCommentEvent(MarketComment comment) {
		super(true);
		setComment(comment);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}

	public MarketComment getComment() {
		return comment;
	}

	public void setComment(MarketComment comment) {
		this.comment = comment;
	}
}
