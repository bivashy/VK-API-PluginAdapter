package com.ubivashka.vk;

import com.google.gson.JsonObject;

public interface CallbackLongpoolAPI {

	public boolean parse(String json);
	
	public boolean parse(JsonObject json);
	
}
