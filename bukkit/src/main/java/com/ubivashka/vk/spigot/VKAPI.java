package com.ubivashka.vk.spigot;

import java.util.Random;

import com.google.gson.JsonObject;
import com.ubivashka.vk.CallbackLongpoolAPI;
import com.ubivashka.vk.bukkit.BukkitVkApiPlugin;
import com.ubivashka.vk.utils.VKUtil;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;

@Deprecated
public class VKAPI {

	private static final BukkitVkApiPlugin PLUGIN = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class);
	private static final VKUtil VK_UTIL = new VKUtil(PLUGIN);
	private static final Random RANDOM = new Random();
	private static final VKAPI INSTANCE = new VKAPI();

	public static VKAPI getInstance() {
		return INSTANCE;
	}

	public void setTS() {
		throw new UnsupportedOperationException();
	}

	public VKUtil getVKUtil() {
		return VK_UTIL;
	}

	public Random getRandom() {
		return RANDOM;
	}

	public Integer getTs() {
		return -1;
	}

	public GroupActor getActor() {
		return PLUGIN.getVkApiProvider().getActor();
	}

	public VkApiClient getVK() {
		return PLUGIN.getVkApiProvider().getVkApiClient();
	}

	public CallbackLongpoolAPI getCallbackAPI() {
		return new CallbackLongpoolAPI() {

			@Override
			public boolean parse(JsonObject json) {
				return PLUGIN.getLongpoolParser().parse(json);
			}

			@Override
			public boolean parse(String json) {
				return PLUGIN.getLongpoolParser().parse(json);
			}
		};
	}

	public void callEvent(JsonObject json) {
		PLUGIN.getLongpoolParser().parse(json);
	}

	public int getDelay() {
		return -1;
	}
}
