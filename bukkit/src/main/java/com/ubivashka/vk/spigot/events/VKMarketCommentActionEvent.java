package com.ubivashka.vk.spigot.events;

import org.bukkit.event.Event;

import com.vk.api.sdk.objects.callback.MarketComment;

public abstract class VKMarketCommentActionEvent extends Event {
	private MarketComment comment;

	public VKMarketCommentActionEvent(MarketComment comment) {
		setComment(comment);
	}

	public MarketComment getComment() {
		return this.comment;
	}

	public void setComment(MarketComment comment) {
		this.comment = comment;
	}
}
