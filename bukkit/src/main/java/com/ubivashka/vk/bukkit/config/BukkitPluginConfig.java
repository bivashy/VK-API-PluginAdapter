package com.ubivashka.vk.bukkit.config;

import java.util.Optional;
import java.util.stream.IntStream;

import org.bukkit.configuration.ConfigurationSection;

import com.ubivashka.vk.api.config.PluginConfig;
import com.ubivashka.vk.bukkit.BukkitVkApiPlugin;

public class BukkitPluginConfig implements PluginConfig {

    private ConfigurationSection configuration;
    private Integer longpoolSchedulerDelay, groupId, proxyPort;
    private String groupToken, proxyType, proxyHost;

    public BukkitPluginConfig(BukkitVkApiPlugin plugin) {
        plugin.saveDefaultConfig();
        configuration = plugin.getConfig();
        longpoolSchedulerDelay = getInt(SETTINGS_KEY, SCHEDULER_DELAY_KEY);
        groupId = getInt(DEFAULT_GROUP_ID, GROUP_INFO_KEY, GROUP_ID_KEY);
        groupToken = getString(GROUP_INFO_KEY, GROUP_TOKEN_KEY);
        proxyType = getStringDefault(DEFAULT_PORT_TYPE, PROXY_KEY, PROXY_TYPE_KEY);
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
        return getSection(path).map(section -> section.getString(path[path.length - 1], def)).orElse(def);
    }

    @Override
    public int getInt(int def, String... path) {
        return getSection(path).map(section -> section.getInt(path[path.length - 1], def)).orElse(def);
    }

    private Optional<ConfigurationSection> getSection(String... path) {
        return Optional.ofNullable(IntStream.range(0, path.length - 1)
                .boxed()
                .map(index -> path[index])
                .reduce(configuration, (section, key) -> {
                    if (section == null)
                        return null;
                    return section.getConfigurationSection(key);
                }, (first, second) -> first));
    }

    @Override
    public Object getConfiguration() {
        return configuration;
    }

}
