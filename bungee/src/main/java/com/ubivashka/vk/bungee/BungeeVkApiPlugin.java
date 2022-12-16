package com.ubivashka.vk.bungee;

import java.util.logging.Logger;

import com.ubivashka.vk.api.VkApiPlugin;
import com.ubivashka.vk.api.config.PluginConfig;
import com.ubivashka.vk.api.listeners.LongpoolAPIListener;
import com.ubivashka.vk.api.providers.VkApiProvider;
import com.ubivashka.vk.bungee.config.BungeePluginConfig;
import com.ubivashka.vk.bungee.logging.filter.VkLoggingFilter;
import com.ubivashka.vk.bungee.providers.BungeeVkApiProvider;

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
        pluginConfig = new BungeePluginConfig(this);

        enableLogFiltering();
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
        Logger logger = getLogger();
        logger.setFilter(new VkLoggingFilter(this));
    }

    public static BungeeVkApiPlugin getInstance() {
        return instance;
    }
}
