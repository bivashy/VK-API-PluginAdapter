package com.ubivashka.vk.bukkit.events;

import com.vk.api.sdk.objects.callback.UserUnblock;

public class VKUserUnblockEvent extends AbstractVkEvent {
	
	private UserUnblock unblock;

	public VKUserUnblockEvent(UserUnblock unblock, Integer groupId) {
		super(groupId);
		setUnblock(unblock);
	}

	public UserUnblock getUnblock() {
		return unblock;
	}

	public void setUnblock(UserUnblock block) {
		this.unblock = block;
	}
}
