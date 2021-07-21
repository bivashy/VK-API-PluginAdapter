package com.ubivashka.vk.spigot.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.ubivashka.vk.VKEvent;
import com.vk.api.sdk.objects.messages.Message;

public class VKMessageEvent extends Event implements VKEvent, Cancellable {
	private static final HandlerList handlers = new HandlerList();
	private boolean cancel = false;
	private Message message;

	public VKMessageEvent(Message message) {
		super(true);
		this.setMessage(message);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}

	public final void setCancelled(final boolean cancel) {
		this.cancel = cancel;
	}

	public final boolean isCancelled() {
		return cancel;
	}

	public Message getMessage() {
		return message;
	}

	public Integer getUserId() {
		return message.getFromId();
	}

	public Integer getPeer() {
		return message.getPeerId();
	}

	private void setMessage(Message message) {
		this.message = message;
	}

}
