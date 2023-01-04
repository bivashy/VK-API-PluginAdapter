package com.ubivashka.vk.bungee.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;

import com.ubivashka.vk.api.config.PluginConfig;
import com.ubivashka.vk.bungee.BungeeVkApiPlugin;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class BungeePluginConfig implements PluginConfig {

    private Configuration configuration;
    private int longpoolSchedulerDelay, groupId, proxyPort;
    private String groupToken, proxyType, proxyHost;
    private boolean loggingEnabled;

    public BungeePluginConfig(BungeeVkApiPlugin plugin) {
        configuration = loadConfiguration(plugin.getDataFolder(), plugin.getResourceAsStream("config.yml"));
        longpoolSchedulerDelay = getInt(SETTINGS_KEY, SCHEDULER_DELAY_KEY);
        groupId = getInt(GROUP_INFO_KEY, GROUP_ID_KEY);
        groupToken = getString(GROUP_INFO_KEY, GROUP_TOKEN_KEY);
        proxyType = getStringDefault(DEFAULT_PORT_TYPE, PROXY_KEY, PROXY_TYPE_KEY);
        proxyHost = getString(PROXY_KEY, PROXY_HOST_KEY);
        proxyPort = getInt(PROXY_KEY, PROXY_PORT_KEY);
        loggingEnabled = getBoolean(SETTINGS_KEY, LOGGING_ENABLED_KEY);
    }

    @Override
    public int getLongpoolSchedulerDelay() {
        return longpoolSchedulerDelay;
    }

    @Override
    public int getGroupId() {
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
    public int getProxyPort() {
        return proxyPort;
    }

    @Override
    public boolean isLoggingEnabled() {
        return loggingEnabled;
    }

    private boolean getBoolean(String... path) {
        return getSection((sectionOptional, key) -> sectionOptional.map(section -> section.getBoolean(key)), path).orElse(false);
    }

    private String getString(String... path) {
        return getStringDefault("", path);
    }

    private String getStringDefault(String def, String... path) {
        return getSection((sectionOptional, key) -> sectionOptional.map(section -> section.getString(key)), path).orElse(def);
    }

    private int getInt(String... path) {
        return getInt(-1, path);
    }

    private int getInt(int def, String... path) {
        return getSection((sectionOptional, key) -> sectionOptional.map(section -> section.getInt(key)), path).orElse(def);
    }

    private <T> Optional<T> getSection(BiFunction<Optional<Configuration>, String, Optional<T>> mapper, String... path) {
        int limit = path.length - 1;
        String valueKey = path[limit];
        return mapper.apply(Optional.ofNullable(Arrays.stream(path).limit(limit).reduce(configuration, Configuration::getSection, (first, second) -> first)),
                valueKey);
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
