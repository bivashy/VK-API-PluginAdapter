package com.ubivashka.vk.bungee.providers;

import com.ubivashka.vk.api.config.PluginConfig;
import com.ubivashka.vk.api.providers.AbstractVkApiProvider;
import com.ubivashka.vk.bungee.BungeeVkApiPlugin;
import com.ubivashka.vk.bungee.parsers.BungeeLongpoolEventParser;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

import net.md_5.bungee.api.ChatColor;

public class BungeeVkApiProvider extends AbstractVkApiProvider {
    public BungeeVkApiProvider(PluginConfig pluginConfig) {
        super(pluginConfig, new BungeeLongpoolEventParser(
                BungeeVkApiPlugin.getInstance()));

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
