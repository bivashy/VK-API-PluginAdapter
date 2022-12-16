package com.ubivashka.vk.api;

import com.ubivashka.vk.api.config.PluginConfig;
import com.ubivashka.vk.api.providers.VkApiProvider;
import com.ubivashka.vk.http.proxy.DefaultSystemProxyApplier;
import com.ubivashka.vk.http.proxy.ProxyApplier;

public interface VkApiPlugin {
    VkApiProvider getVkApiProvider();

    PluginConfig getPluginConfig();

    void callEvent(Object event);

    default ProxyApplier getProxyApplier(String rawType) {
        return DefaultSystemProxyApplier.valueOf(rawType);
    }
}
