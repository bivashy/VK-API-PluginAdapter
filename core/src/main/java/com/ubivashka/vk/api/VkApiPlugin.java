package com.ubivashka.vk.api;

import com.ubivashka.vk.api.config.PluginConfig;
import com.ubivashka.vk.api.parsers.LongpoolEventParser;
import com.ubivashka.vk.api.providers.VkApiProvider;
import com.ubivashka.vk.http.proxy.DefaultSystemProxyApplier;
import com.ubivashka.vk.http.proxy.ProxyApplier;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;

public interface VkApiPlugin {
    /**
     * @return Actor of group Deprecated because this method moved to
     * {@link VkApiPlugin#getVkApiProvider()}
     */
    @Deprecated
    GroupActor getActor();

    /**
     * @return client of vk Deprecated because this method moved to
     * {@link VkApiPlugin#getVkApiProvider()}
     */
    @Deprecated
    VkApiClient getVK();

    /**
     * @return client of vk Deprecated because this method moved to
     * {@link VkApiPlugin#getVkApiProvider()}
     */
    @Deprecated
    LongpoolEventParser getLongpoolParser();

    VkApiProvider getVkApiProvider();

    PluginConfig getPluginConfig();

    void callEvent(Object event);

    default ProxyApplier getProxyApplier(String rawType) {
        return DefaultSystemProxyApplier.valueOf(rawType);
    }
}
