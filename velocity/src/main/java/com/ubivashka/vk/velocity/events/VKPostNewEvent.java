package com.ubivashka.vk.velocity.events;

import com.vk.api.sdk.objects.wall.Wallpost;

public class VKPostNewEvent extends AbstractVkEvent {
	private Wallpost post;

	public VKPostNewEvent(Wallpost post, Integer groupId) {
		super(groupId);
		setPost(post);
	}

	public Wallpost getPost() {
		return this.post;
	}

	public void setPost(Wallpost post) {
		this.post = post;
	}
}
