package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.MarketComment;

public class VKMarketCommentRestoreEvent extends VKMarketCommentActionEvent {

	public VKMarketCommentRestoreEvent(MarketComment comment, Integer groupId) {
		super(comment, groupId);
	}

}
