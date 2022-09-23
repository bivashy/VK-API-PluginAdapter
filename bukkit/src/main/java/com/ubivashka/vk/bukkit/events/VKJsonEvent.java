package com.ubivashka.vk.bukkit.events;

import com.google.gson.JsonObject;

public class VKJsonEvent extends AbstractVkEvent {
	
	private JsonObject jsonObject;

	public VKJsonEvent(JsonObject jsonObject, Integer groupId) {
		super(groupId);
		setJsonObject(jsonObject);
	}

	public JsonObject getJsonObject() {
		return jsonObject;
	}

	public String getType() {
		return jsonObject.get("type").getAsString();
	}

	private void setJsonObject(JsonObject jsonObject) {
		this.jsonObject = jsonObject;
	}
}
