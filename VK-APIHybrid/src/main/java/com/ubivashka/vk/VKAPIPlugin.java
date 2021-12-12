package com.ubivashka.vk;

import java.util.Random;

import com.google.gson.JsonObject;
import com.ubivashka.vk.utils.VKUtil;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;

public interface VKAPIPlugin {
	public void setTS();

	public VKUtil getVKUtil();

	public Random getRandom();

	public Integer getTs();

	public GroupActor getActor();

	public VkApiClient getVK();
	
	public CallbackLongpoolAPI getCallbackAPI();

	public void callEvent(VKEvent event);

	public void callEvent(JsonObject json);

	public int getDelay();
}
