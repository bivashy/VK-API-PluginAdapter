package com.ubivashka.vk.bukkit.events;

import com.vk.api.sdk.objects.callback.PollVoteNew;

public class VKPollVoteNewEvent extends AbstractVkEvent {
	
	private PollVoteNew pollVote;

	public VKPollVoteNewEvent(PollVoteNew pollVote, Integer groupId) {
		super(groupId);
		setPollVote(pollVote);
	}

	public PollVoteNew getPollVote() {
		return pollVote;
	}

	public void setPollVote(PollVoteNew pollVote) {
		this.pollVote = pollVote;
	}

}
