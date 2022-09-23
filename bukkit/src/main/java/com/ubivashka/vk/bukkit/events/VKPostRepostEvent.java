package com.ubivashka.vk.bukkit.events;

import com.vk.api.sdk.objects.wall.Wallpost;

public class VKPostRepostEvent extends AbstractVkEvent {
	
	private Wallpost post;

	public VKPostRepostEvent(Wallpost post, Integer groupId) {
		super(groupId);
		setPost(post);
	}

	public Wallpost getPost() {
		return post;
	}

	public void setPost(Wallpost post) {
		this.post = post;
	}
}
