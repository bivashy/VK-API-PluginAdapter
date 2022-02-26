package com.ubivashka.vk.api.parsers;

import com.google.gson.JsonObject;

public interface LongpoolEventParser {
	boolean parse(String json);

	boolean parse(JsonObject json);
}
