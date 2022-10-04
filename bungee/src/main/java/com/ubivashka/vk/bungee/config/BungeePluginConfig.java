package com.ubivashka.vk.bungee.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Optional;
import java.util.stream.IntStream;

import com.ubivashka.vk.api.config.PluginConfig;
import com.ubivashka.vk.bungee.BungeeVkApiPlugin;
import com.ubivashka.vk.http.proxy.DefaultSystemProxyApplier;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class BungeePluginConfig implements PluginConfig {

    private Configuration configuration;
    private Integer longpoolSchedulerDelay, groupId, proxyPort;
    private String groupToken, proxyType, proxyHost;

    public BungeePluginConfig(BungeeVkApiPlugin plugin) {
        configuration = loadConfiguration(plugin.getDataFolder(), plugin.getResourceAsStream("config.yml"));
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
        return getSection(path).map(section -> section.getString(path[path.length - 1], def)).orElse(def);
    }

    @Override
    public int getInt(int def, String... path) {
        return getSection(path).map(section -> section.getInt(path[path.length - 1], def)).orElse(def);
    }

    private Optional<Configuration> getSection(String... path) {
        return Optional.ofNullable(IntStream.range(0, path.length - 1).boxed().map(index -> path[index]).reduce(configuration, (section, key) -> {
            if (section == null)
                return null;
            return section.getSection(key);
        }, (first, second) -> first));
    }

    @Override
    public Object getConfiguration() {
        return configuration;
    }

    private Configuration loadConfiguration(File folder, InputStream resourceAsStream) {
        try {
            if (!folder.exists())
                folder.mkdir();

            File config = new File(folder + File.separator + "config.yml");
            if (!config.exists())
                Files.copy(resourceAsStream, config.toPath());
            Configuration defaults = ConfigurationProvider.getProvider(YamlConfiguration.class).load(resourceAsStream);
            return ConfigurationProvider.getProvider(YamlConfiguration.class).load(config, defaults);
        } catch(IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

}
