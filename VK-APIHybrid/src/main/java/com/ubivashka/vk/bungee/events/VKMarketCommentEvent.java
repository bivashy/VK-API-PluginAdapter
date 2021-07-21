package com.ubivashka.vk.bungee.events;

import com.ubivashka.vk.VKEvent;
import com.vk.api.sdk.objects.callback.MarketComment;

import net.md_5.bungee.api.plugin.Event;

public class VKMarketCommentEvent extends Event implements VKEvent {
	private MarketComment comment;

	public VKMarketCommentEvent(MarketComment comment) {
		setComment(comment);
	}

	public MarketComment getComment() {
		return this.comment;
	}

	public void setComment(MarketComment comment) {
		this.comment = comment;
	}
}
