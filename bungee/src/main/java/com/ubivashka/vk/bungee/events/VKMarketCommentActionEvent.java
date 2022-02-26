package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.MarketComment;

import net.md_5.bungee.api.plugin.Event;

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
