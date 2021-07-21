package com.ubivashka.vk.bungee.events;

import com.ubivashka.vk.VKEvent;
import com.vk.api.sdk.objects.callback.MarketCommentDelete;

import net.md_5.bungee.api.plugin.Event;

public class VKMarketCommentDeleteEvent extends Event implements VKEvent {
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
