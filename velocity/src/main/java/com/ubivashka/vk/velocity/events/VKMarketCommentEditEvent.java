package com.ubivashka.vk.velocity.events;

import com.vk.api.sdk.objects.callback.MarketComment;

public class VKMarketCommentEditEvent extends VKMarketCommentActionEvent {

	public VKMarketCommentEditEvent(MarketComment comment, Integer groupId) {
		super(comment, groupId);
	}

}
