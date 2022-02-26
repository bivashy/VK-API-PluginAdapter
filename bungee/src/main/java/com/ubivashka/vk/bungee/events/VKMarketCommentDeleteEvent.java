package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.MarketCommentDelete;

import net.md_5.bungee.api.plugin.Event;

public class VKMarketCommentDeleteEvent extends Event {
	private MarketCommentDelete comment;

	public VKMarketCommentDeleteEvent(MarketCommentDelete comment) {
		setComment(comment);
	}

	public MarketCommentDelete getComment() {
		return this.comment;
	}

	public void setComment(MarketCommentDelete comment) {
		this.comment = comment;
	}
}
