package com.ubivashka.vk;

import com.google.gson.JsonObject;

@Deprecated
public interface CallbackLongpoolAPI {

	boolean parse(String json);

	boolean parse(JsonObject json);

}
