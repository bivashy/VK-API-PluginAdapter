package com.ubivashka.vk.bukkit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

import com.ubivashka.vk.api.VkApiPlugin;
import com.ubivashka.vk.api.config.PluginConfig;
import com.ubivashka.vk.api.listeners.LongpoolAPIListener;
import com.ubivashka.vk.api.parsers.LongpoolEventParser;
import com.ubivashka.vk.api.providers.VkApiProvider;
import com.ubivashka.vk.bukkit.config.BukkitPluginConfig;
import com.ubivashka.vk.bukkit.filters.LogFilter;
import com.ubivashka.vk.bukkit.providers.BukkitVkApiProvider;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;

import net.md_5.bungee.api.ChatColor;

public class BukkitVkApiPlugin extends JavaPlugin implements VkApiPlugin {
    private BukkitVkApiProvider vkApiProvider;
    private BukkitPluginConfig pluginConfig;

    @Override
    public void onEnable() {
        Logger coreLogger = (Logger) LogManager.getRootLogger();
        coreLogger.addFilter(new LogFilter());

        pluginConfig = new BukkitPluginConfig(this);
        getProxyApplier(pluginConfig.getProxyType()).apply(pluginConfig.getProxyHost(), pluginConfig.getProxyPort());
        vkApiProvider = new BukkitVkApiProvider(pluginConfig);

        new LongpoolAPIListener(this);

        Bukkit.getConsoleSender()
                .sendMessage("\n\r" + ChatColor.BLUE + " ##      ## ##   ##           ##     #######  ##\r\n"
                        + ChatColor.BLUE + "/##     /##/##  ##           ####   /##////##/##\r\n" + ChatColor.BLUE
                        + "/##     /##/## ##           ##//##  /##   /##/##\r\n" + ChatColor.BLUE
                        + "//##    ## /####    #####  ##  //## /####### /##\r\n" + ChatColor.BLUE
                        + " //##  ##  /##/##  /////  ##########/##////  /##\r\n" + ChatColor.BLUE
                        + "  //####   /##//##       /##//////##/##      /##\r\n" + ChatColor.BLUE
                        + "   //##    /## //##      /##     /##/##      /##\r\n" + ChatColor.BLUE
                        + "    //     //   //       //      // //       // \r\n");

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
        Event bukkitEvent = (Event) event;
        Bukkit.getPluginManager().callEvent(bukkitEvent);
    }
}
