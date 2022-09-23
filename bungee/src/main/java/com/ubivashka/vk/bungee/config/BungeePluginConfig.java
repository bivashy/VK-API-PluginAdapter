package com.ubivashka.vk.bungee.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import com.ubivashka.vk.api.config.PluginConfig;
import com.ubivashka.vk.bungee.BungeeVkApiPlugin;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class BungeePluginConfig implements PluginConfig {

    private Configuration configuration;
    private Integer longpoolSchedulerDelay, groupId;
    private String groupToken;

    public BungeePluginConfig(BungeeVkApiPlugin plugin) {
        configuration = loadConfiguration(plugin.getDataFolder(), plugin.getResourceAsStream("config.yml"));
        longpoolSchedulerDelay = configuration.getInt("settings.delay");
        groupId = configuration.getInt("groupInfo.groupID", -1);
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
