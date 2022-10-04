package com.ubivashka.vk.velocity;

import java.nio.file.Path;

import org.slf4j.Logger;

import com.google.inject.Inject;
import com.ubivashka.vk.api.VkApiPlugin;
import com.ubivashka.vk.api.config.PluginConfig;
import com.ubivashka.vk.api.listeners.LongpoolAPIListener;
import com.ubivashka.vk.api.parsers.LongpoolEventParser;
import com.ubivashka.vk.api.providers.VkApiProvider;
import com.ubivashka.vk.velocity.config.VelocityPluginConfig;
import com.ubivashka.vk.velocity.parsers.VelocityLongpoolEventParser;
import com.ubivashka.vk.velocity.providers.VelocityVkApiProvider;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;

@Plugin(id = "vk-api", name = "VK-API", version = "0.4.0", authors = "Ubivashka")
public class VelocityVkApiPlugin implements VkApiPlugin {

	private static VelocityVkApiPlugin instance;

	private final ProxyServer proxyServer;
	private final Logger logger;
	private final Path pluginPath;
	private VelocityPluginConfig pluginConfig;
	private VelocityLongpoolEventParser longpoolEventParser;
	private VelocityVkApiProvider velocityVkApiProvider;

	@Inject
	public VelocityVkApiPlugin(ProxyServer proxyServer, Logger logger, @DataDirectory Path pluginPath) {
		instance = this;
		this.proxyServer = proxyServer;
		this.logger = logger;
		this.pluginPath = pluginPath;
	}

	@Subscribe
	public void onEnable(ProxyInitializeEvent event) {
		this.pluginConfig = new VelocityPluginConfig(this);
		getProxyApplier(pluginConfig.getProxyType()).apply(pluginConfig.getProxyHost(), pluginConfig.getProxyPort());
		this.longpoolEventParser = new VelocityLongpoolEventParser(this);
		this.velocityVkApiProvider = new VelocityVkApiProvider(pluginConfig);
		new LongpoolAPIListener(this);
	}

	@Deprecated
	@Override
	public GroupActor getActor() {
		return velocityVkApiProvider.getActor();
	}

	@Deprecated
	@Override
	public VkApiClient getVK() {
		return velocityVkApiProvider.getVkApiClient();
	}

	@Deprecated
	@Override
	public LongpoolEventParser getLongpoolParser() {
		return longpoolEventParser;
	}

	@Override
	public VkApiProvider getVkApiProvider() {
		return velocityVkApiProvider;
	}

	@Override
	public PluginConfig getPluginConfig() {
		return pluginConfig;
	}

	@Override
	public void callEvent(Object event) {
		proxyServer.getEventManager().fire(event);
	}

	public ProxyServer getProxyServer() {
		return proxyServer;
	}

	public Logger getLogger() {
		return logger;
	}

	public Path getPluginPath() {
		return pluginPath;
	}

	public static VelocityVkApiPlugin getInstance() {
		return instance;
	}

}
