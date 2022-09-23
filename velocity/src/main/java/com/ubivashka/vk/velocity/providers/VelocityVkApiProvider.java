package com.ubivashka.vk.velocity.providers;

import com.ubivashka.vk.api.config.PluginConfig;
import com.ubivashka.vk.api.providers.AbstractVkApiProvider;
import com.ubivashka.vk.velocity.VelocityVkApiPlugin;
import com.ubivashka.vk.velocity.parsers.VelocityLongpoolEventParser;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

public class VelocityVkApiProvider extends AbstractVkApiProvider {
    public VelocityVkApiProvider(PluginConfig pluginConfig) {
        super(pluginConfig, new VelocityLongpoolEventParser(
                VelocityVkApiPlugin.getInstance()));

        try {
            vkApiClient.messages().getLongPollServer(groupActor).execute();
        } catch(ApiException e) {
            System.out.println("Код ошибки: " + e.getCode());
            System.out.println("В сайте https://vk.com/dev/errors описаны все ошибки связанные с ВК");
            e.printStackTrace();
        } catch(ClientException e) {
            e.printStackTrace();
        }
    }
}
