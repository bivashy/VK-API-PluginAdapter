package com.ubivashka.vk.bukkit.providers;

import com.ubivashka.vk.api.config.PluginConfig;
import com.ubivashka.vk.api.providers.AbstractVkApiProvider;
import com.ubivashka.vk.bukkit.BukkitVkApiPlugin;
import com.ubivashka.vk.bukkit.parsers.BukkitLongpoolEventParser;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

import net.md_5.bungee.api.ChatColor;

public class BukkitVkApiProvider extends AbstractVkApiProvider {
    public BukkitVkApiProvider(PluginConfig pluginConfig) {
        super(pluginConfig, new BukkitLongpoolEventParser(
                BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class)));

        try {
            vkApiClient.messages().getLongPollServer(groupActor).execute();
        } catch(ApiException e) {
            System.out.println(ChatColor.RED + "Код ошибки: " + e.getCode());
            System.out.println(ChatColor.RED + "В сайте https://vk.com/dev/errors описаны все ошибки связанные с ВК");
            e.printStackTrace();
        } catch(ClientException e) {
            e.printStackTrace();
        }
    }
}