package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.MarketComment;

public abstract class VKMarketCommentActionEvent extends AbstractVkEvent {
	private MarketComment comment;

	public VKMarketCommentActionEvent(MarketComment comment, Integer groupId) {
		super(groupId);
		setComment(comment);
	}

	public MarketComment getComment() {
		return this.comment;
	}

	public void setComment(MarketComment comment) {
		this.comment = comment;
	}
}
