package com.ubivashka.vk.bungee.events;

import com.ubivashka.vk.VKEvent;
import com.vk.api.sdk.objects.callback.WallCommentDelete;

import net.md_5.bungee.api.plugin.Event;

public class VKPostReplyDeleteEvent extends Event implements VKEvent {
	private WallCommentDelete postCommentDelete;

	public VKPostReplyDeleteEvent(WallCommentDelete postCommentDelete) {
		setPostCommentDelete(postCommentDelete);
	}

	public WallCommentDelete getPostCommentDelete() {
		return this.postCommentDelete;
	}

	public void setPostCommentDelete(WallCommentDelete postCommentDelete) {
		this.postCommentDelete = postCommentDelete;
	}
}
