package com.ubivashka.vk.bukkit.events;

import com.vk.api.sdk.objects.callback.MarketComment;

public class VKMarketCommentEvent extends VKMarketCommentActionEvent {
	

	public VKMarketCommentEvent(MarketComment comment, Integer groupId) {
		super(comment, groupId);
	}
}
