package com.ubivashka.vk.bukkit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

import com.ubivashka.vk.api.VkApiPlugin;
import com.ubivashka.vk.api.config.PluginConfig;
import com.ubivashka.vk.api.listeners.LongpoolAPIListener;
import com.ubivashka.vk.api.providers.VkApiProvider;
import com.ubivashka.vk.bukkit.config.BukkitPluginConfig;
import com.ubivashka.vk.bukkit.filters.LogFilter;
import com.ubivashka.vk.bukkit.providers.BukkitVkApiProvider;

import net.md_5.bungee.api.ChatColor;

public class BukkitVkApiPlugin extends JavaPlugin implements VkApiPlugin {
    private BukkitVkApiProvider vkApiProvider;
    private BukkitPluginConfig pluginConfig;

    @Override
    public void onEnable() {
        Logger coreLogger = (Logger) LogManager.getRootLogger();
        pluginConfig = new BukkitPluginConfig(this);
        coreLogger.addFilter(new LogFilter(this));

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
