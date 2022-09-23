package com.ubivashka.vk.bukkit.events;

import com.vk.api.sdk.objects.callback.MarketComment;

public class VKMarketCommentEditEvent extends VKMarketCommentActionEvent {
	

	public VKMarketCommentEditEvent(MarketComment comment, Integer groupId) {
		super(comment, groupId);
	}
}
