package com.ubivashka.vk.bukkit.config;

import org.bukkit.configuration.ConfigurationSection;

import com.ubivashka.vk.api.config.PluginConfig;
import com.ubivashka.vk.bukkit.BukkitVkApiPlugin;

public class BukkitPluginConfig implements PluginConfig {

	private ConfigurationSection configuration;
	private Integer longpoolSchedulerDelay, groupId;
	private String groupToken;

	public BukkitPluginConfig(BukkitVkApiPlugin plugin) {
		plugin.saveDefaultConfig();
		configuration = plugin.getConfig();
		longpoolSchedulerDelay = configuration.getInt("settings.delay");
		groupId = configuration.getInt("groupInfo.groupID");
		groupToken = configuration.getString("groupInfo.groupToken");
	}

	@Override
	public Integer getLongpoolSchedulerDelay() {
		return longpoolSchedulerDelay;
	}

	@Override
	public Integer getGroupId() {
		return groupId;
	}

	@Override
	public String getGroupToken() {
		return groupToken;
	}

	@Override
	public Object getConfiguration() {
		return configuration;
	}

}
