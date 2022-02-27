package com.ubivashka.vk.bukkit.events;

import org.bukkit.event.HandlerList;

import com.vk.api.sdk.objects.callback.PollVoteNew;

public class VKPollVoteNewEvent extends AbstractVkEvent {
	private static final HandlerList handlers = new HandlerList();
	private PollVoteNew pollVote;

	public VKPollVoteNewEvent(PollVoteNew pollVote, Integer groupId) {
		super(groupId);
		setPollVote(pollVote);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}

	public PollVoteNew getPollVote() {
		return pollVote;
	}

	public void setPollVote(PollVoteNew pollVote) {
		this.pollVote = pollVote;
	}

}
