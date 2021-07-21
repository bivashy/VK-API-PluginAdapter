package com.ubivashka.vk.bungee.events;

import com.ubivashka.vk.VKEvent;
import com.vk.api.sdk.objects.callback.UserBlock;

import net.md_5.bungee.api.plugin.Event;

public class VKUserBlockEvent extends Event implements VKEvent {
	private UserBlock block;

	public VKUserBlockEvent(UserBlock block) {
		setBlock(block);
	}

	public UserBlock getBlock() {
		return this.block;
	}

	public void setBlock(UserBlock block) {
		this.block = block;
	}
}
