package com.ubivashka.vk.velocity.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import com.ubivashka.vk.api.config.PluginConfig;
import com.ubivashka.vk.http.proxy.DefaultSystemProxyApplier;
import com.ubivashka.vk.velocity.VelocityVkApiPlugin;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;

public class VelocityPluginConfig implements PluginConfig {
    private static final String CONFIGURATION_FILE_NAME = "config.yml";

    private final VelocityVkApiPlugin plugin;
    private final ConfigurationNode configuration;
    private Integer longpoolSchedulerDelay, groupId, proxyPort;
    private String groupToken, proxyType, proxyHost;

    public VelocityPluginConfig(VelocityVkApiPlugin plugin) {
        this.plugin = plugin;
        configuration = saveDefaultConfiguration();
        longpoolSchedulerDelay = getInt(SETTINGS_KEY, SCHEDULER_DELAY_KEY);
        groupId = getInt(-1, GROUP_INFO_KEY, GROUP_ID_KEY);
        groupToken = getString(GROUP_INFO_KEY, GROUP_TOKEN_KEY);
        proxyType = getStringDefault(DefaultSystemProxyApplier.NONE.name(), PROXY_KEY, PROXY_TYPE_KEY);
        proxyHost = getString(PROXY_KEY, PROXY_HOST_KEY);
        proxyPort = getInt(PROXY_KEY, PROXY_PORT_KEY);
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
    public String getProxyType() {
        return proxyType;
    }

    @Override
    public String getProxyHost() {
        return proxyHost;
    }

    @Override
    public Integer getProxyPort() {
        return proxyPort;
    }

    @Override
    public String getStringDefault(String def, String... path) {
        return configuration.getNode(path).getString(def);
    }

    @Override
    public int getInt(int def, String... path) {
        return configuration.getNode(path).getInt(def);
    }

    @Override
    public Object getConfiguration() {
        return configuration;
    }

    private ConfigurationNode saveDefaultConfiguration() {
        try {
            return YAMLConfigurationLoader.builder().setFile(getBundledFile(CONFIGURATION_FILE_NAME)).build().load();
        } catch(IOException e) {
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
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        return file;
    }
}
