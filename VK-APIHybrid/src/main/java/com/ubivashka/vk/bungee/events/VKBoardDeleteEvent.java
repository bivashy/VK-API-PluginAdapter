package com.ubivashka.vk.bungee.events;

import com.ubivashka.vk.VKEvent;
import com.vk.api.sdk.objects.callback.BoardPostDelete;

import net.md_5.bungee.api.plugin.Event;

public class VKBoardDeleteEvent extends Event implements VKEvent {
	private BoardPostDelete boardDelete;

	public VKBoardDeleteEvent(BoardPostDelete boardDelete) {
		setBoardDelete(boardDelete);
	}

	public BoardPostDelete getBoardDelete() {
		return this.boardDelete;
	}

	public void setBoardDelete(BoardPostDelete boardDelete) {
		this.boardDelete = boardDelete;
	}
}
