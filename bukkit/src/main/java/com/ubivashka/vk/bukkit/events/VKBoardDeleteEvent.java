package com.ubivashka.vk.bukkit.events;

import com.vk.api.sdk.objects.callback.BoardPostDelete;

public class VKBoardDeleteEvent extends AbstractVkEvent {
	
	private BoardPostDelete boardDelete;

	public VKBoardDeleteEvent(BoardPostDelete boardDelete, Integer groupId) {
		super(groupId);
		setBoardDelete(boardDelete);
	}

	public BoardPostDelete getBoardDelete() {
		return boardDelete;
	}

	public void setBoardDelete(BoardPostDelete boardDelete) {
		this.boardDelete = boardDelete;
	}
}
