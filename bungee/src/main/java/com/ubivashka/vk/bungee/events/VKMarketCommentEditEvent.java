package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.MarketComment;

public class VKMarketCommentEditEvent extends VKMarketCommentActionEvent {

	public VKMarketCommentEditEvent(MarketComment comment) {
		super(comment);
	}

}