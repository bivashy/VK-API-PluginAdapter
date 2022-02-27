package com.ubivashka.vk.velocity.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import com.ubivashka.vk.api.config.PluginConfig;
import com.ubivashka.vk.velocity.VelocityVkApiPlugin;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;

public class VelocityPluginConfig implements PluginConfig<ConfigurationNode> {
	private static final String CONFIGURATION_FILE_NAME = "config.yml";

	private final VelocityVkApiPlugin plugin;
	private final ConfigurationNode configuration;
	private Integer longpoolSchedulerDelay = 15, groupId = -1;
	private String groupToken = "";

	public VelocityPluginConfig(VelocityVkApiPlugin plugin) {
		this.plugin = plugin;
		configuration = saveDefaultConfiguration();
		longpoolSchedulerDelay = configuration.getNode("settings").getNode("delay").getInt(longpoolSchedulerDelay);
		groupId = configuration.getNode("groupInfo").getNode("groupID").getInt(groupId);
		groupToken = configuration.getNode("groupInfo").getNode("groupToken").getString(groupToken);
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
	public ConfigurationNode getConfiguration() {
		return configuration;
	}

	private ConfigurationNode saveDefaultConfiguration() {
		try {
			return YAMLConfigurationLoader.builder().setFile(getBundledFile(CONFIGURATION_FILE_NAME)).build().load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private File getBundledFile(String name) {
		File file = new File(plugin.getPluginPath().toFile(), name);

		if (!file.exists()) {
			plugin.getPluginPath().toFile().mkdir();
			try (InputStream in = VelocityVkApiPlugin.class.getResourceAsStream("/" + name)) {
				Files.copy(in, file.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return file;
	}
}
