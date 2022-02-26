package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.callback.BoardPostDelete;

public class VKBoardDeleteEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private BoardPostDelete boardDelete;

	public VKBoardDeleteEvent(BoardPostDelete boardDelete) {
		super(true);
		setBoardDelete(boardDelete);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}

	public BoardPostDelete getBoardDelete() {
		return boardDelete;
	}

	public void setBoardDelete(BoardPostDelete boardDelete) {
		this.boardDelete = boardDelete;
	}
}
