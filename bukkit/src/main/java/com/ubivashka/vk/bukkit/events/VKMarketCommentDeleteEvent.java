package com.ubivashka.vk.bukkit.events;

import com.vk.api.sdk.objects.callback.MarketCommentDelete;

public class VKMarketCommentDeleteEvent extends AbstractVkEvent {
	
	private MarketCommentDelete comment;

	public VKMarketCommentDeleteEvent(MarketCommentDelete comment, Integer groupId) {
		super(groupId);
		setComment(comment);
	}

	public MarketCommentDelete getComment() {
		return comment;
	}

	public void setComment(MarketCommentDelete comment) {
		this.comment = comment;
	}
}
