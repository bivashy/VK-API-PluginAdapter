package com.ubivashka.vk.bungee;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.ubivashka.vk.api.VkApiPlugin;
import com.ubivashka.vk.api.config.PluginConfig;
import com.ubivashka.vk.api.listeners.LongpoolAPIListener;
import com.ubivashka.vk.api.parsers.LongpoolEventParser;
import com.ubivashka.vk.api.providers.VkApiProvider;
import com.ubivashka.vk.bungee.config.BungeePluginConfig;
import com.ubivashka.vk.bungee.providers.BungeeVkApiProvider;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Event;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeVkApiPlugin extends Plugin implements VkApiPlugin {
    private static BungeeVkApiPlugin instance;
    private BungeeVkApiProvider vkApiProvider;
    private BungeePluginConfig pluginConfig;

    @Override
    public void onEnable() {
        instance = this;
        enableLogFiltering();

        pluginConfig = new BungeePluginConfig(this);
        getProxyApplier(pluginConfig.getProxyType()).apply(pluginConfig.getProxyHost(), pluginConfig.getProxyPort());
        vkApiProvider = new BungeeVkApiProvider(pluginConfig);

        new LongpoolAPIListener(this);

        System.out.println("\r\n\r\n" + ChatColor.DARK_AQUA
                + "█████╗█████╗█████╗█████╗█████╗█████╗█████╗\r\n".replaceAll("╗",
                ChatColor.AQUA + "╗" + ChatColor.DARK_AQUA)
                + ChatColor.AQUA + "╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝\r\n" + ChatColor.BLUE
                + "██╗░░░██╗██╗░░██╗░░░░░░░█████╗░██████╗░██╗\r\n" + ChatColor.BLUE
                + "██║░░░██║██║░██╔╝░░░░░░██╔══██╗██╔══██╗██║\r\n" + ChatColor.BLUE
                + "╚██╗░██╔╝█████═╝░█████╗███████║██████╔╝██║\r\n" + ChatColor.BLUE
                + "░╚████╔╝░██╔═██╗░╚════╝██╔══██║██╔═══╝░██║\r\n" + ChatColor.BLUE
                + "░░╚██╔╝░░██║░╚██╗░░░░░░██║░░██║██║░░░░░██║\r\n" + ChatColor.BLUE
                + "░░░╚═╝░░░╚═╝░░╚═╝░░░░░░╚═╝░░╚═╝╚═╝░░░░░╚═╝\r\n" + ChatColor.DARK_AQUA
                + "█████╗█████╗█████╗█████╗█████╗█████╗█████╗\r\n".replaceAll("╗",
                ChatColor.AQUA + "╗" + ChatColor.DARK_AQUA)
                + ChatColor.AQUA + "╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝\r\n");

    }

    @Deprecated
    @Override
    public GroupActor getActor() {
        return vkApiProvider.getActor();
    }

    @Deprecated
    @Override
    public VkApiClient getVK() {
        return vkApiProvider.getVkApiClient();
    }

    @Deprecated
    @Override
    public LongpoolEventParser getLongpoolParser() {
        return vkApiProvider.getLongpoolParser();
    }

    @Override
    public VkApiProvider getVkApiProvider() {
        return vkApiProvider;
    }

    @Override
    public PluginConfig getPluginConfig() {
        return pluginConfig;
    }

    @Override
    public void callEvent(Object event) {
        if (!(event instanceof Event))
            return;
        Event bungeeEvent = (Event) event;
        ProxyServer.getInstance().getPluginManager().callEvent(bungeeEvent);
    }

    private void enableLogFiltering() {
        Logger logger = ProxyServer.getInstance().getLogger();
        logger.setFilter(record -> {
            String message = record.getMessage();
            return !message.startsWith("Request: https://api.vk.com/")
                    && !message.startsWith("Request: https://lp.vk.com/") && !message.startsWith("ERROR StatusLogger")
                    && !message.startsWith("SLF4J: Failed to load class")
                    && !message.startsWith("SLF4J: Defaulting to no-operation")
                    && !message.startsWith("SLF4J: See http:")
                    && (!message.contains("lp.vk.com") || record.getLevel() != Level.SEVERE)
                    && (!message.startsWith("Event {0}") || record.getParameters().length <= 0
                    || !record.getParameters()[0].toString().startsWith("com.ubivashka.vk.bungee.events")
                    || record.getLevel() != Level.WARNING);
        });
    }

    public static BungeeVkApiPlugin getInstance() {
        return instance;
    }
}
