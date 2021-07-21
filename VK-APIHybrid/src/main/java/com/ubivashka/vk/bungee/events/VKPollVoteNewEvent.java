package com.ubivashka.vk.bungee.events;

import com.ubivashka.vk.VKEvent;
import com.vk.api.sdk.objects.callback.PollVoteNew;

import net.md_5.bungee.api.plugin.Event;

public class VKPollVoteNewEvent extends Event implements VKEvent {
	private PollVoteNew pollVote;

	public VKPollVoteNewEvent(PollVoteNew pollVote) {
		setPollVote(pollVote);
	}

	public PollVoteNew getPollVote() {
		return this.pollVote;
	}

	public void setPollVote(PollVoteNew pollVote) {
		this.pollVote = pollVote;
	}
}
