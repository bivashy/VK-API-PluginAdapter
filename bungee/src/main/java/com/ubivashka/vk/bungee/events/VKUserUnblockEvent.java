package com.ubivashka.vk.bungee.events;

import com.vk.api.sdk.objects.callback.UserUnblock;

import net.md_5.bungee.api.plugin.Event;

public class VKUserUnblockEvent extends Event {
	private UserUnblock unblock;

	public VKUserUnblockEvent(UserUnblock unblock) {
		setUnblock(unblock);
	}

	public UserUnblock getUnblock() {
		return this.unblock;
	}

	public void setUnblock(UserUnblock block) {
		this.unblock = block;
	}
}
