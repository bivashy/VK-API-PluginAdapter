package com.ubivashka.vk.bungee.logging.filter;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

import com.ubivashka.vk.bungee.BungeeVkApiPlugin;

public class VkLoggingFilter implements Filter {
    private final BungeeVkApiPlugin plugin;

    public VkLoggingFilter(BungeeVkApiPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean isLoggable(LogRecord logRecord) {
        String message = logRecord.getMessage();
        if (isSlf4jMessage(message))
            return false;
        return plugin.getPluginConfig().isLoggingEnabled() || !isVkMessage(message);
    }

    private boolean isVkMessage(String message) {
        return message.contains("https://api.vk.com/") || message.contains("https://lp.vk.com/");
    }

    private boolean isSlf4jMessage(String message) {
        return message.startsWith("SLF4J");
    }
}
