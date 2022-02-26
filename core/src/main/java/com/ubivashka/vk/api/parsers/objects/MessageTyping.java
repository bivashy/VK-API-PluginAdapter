package com.ubivashka.vk.api.parsers.objects;

import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class MessageTyping {

	@SerializedName("state")
	private String state;

	@SerializedName("from_id")
	private Integer fromId;

	@SerializedName("to_id")
	private Integer toId;

	public String getState() {
		return state;
	}

	public MessageTyping setState(String state) {
		this.state = state;
		return this;
	}

	public Integer getFromId() {
		return fromId;
	}

	public MessageTyping setFromId(Integer fromId) {
		this.fromId = fromId;
		return this;
	}

	public Integer getToId() {
		return toId;
	}

	public MessageTyping setToId(Integer toId) {
		this.toId = toId;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(state, fromId, toId);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		MessageTyping messageTyping = (MessageTyping) o;
		return Objects.equals(state, messageTyping.state) && Objects.equals(fromId, messageTyping.fromId)
				&& Objects.equals(toId, messageTyping.toId);
	}

	@Override
	public String toString() {
		final Gson gson = new Gson();
		return gson.toJson(this);
	}

	public String toPrettyString() {
		final StringBuilder sb = new StringBuilder("MessageTyping{");
		sb.append("state='").append(state).append("'");
		sb.append(", fromId=").append(fromId);
		sb.append(", toId=").append(toId);
		sb.append('}');
		return sb.toString();
	}
}
