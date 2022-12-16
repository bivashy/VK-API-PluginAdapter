package com.ubivashka.vk.api.config;

import com.ubivashka.vk.http.proxy.DefaultSystemProxyApplier;

public interface PluginConfig {
    String SETTINGS_KEY = "settings", GROUP_INFO_KEY = "groupInfo", PROXY_KEY = "proxy", SCHEDULER_DELAY_KEY = "delay", LOGGING_ENABLED_KEY = "logging",
            GROUP_ID_KEY = "groupID", GROUP_TOKEN_KEY = "groupToken", PROXY_TYPE_KEY = "type", PROXY_HOST_KEY = "host", PROXY_PORT_KEY = "port";
    String DEFAULT_PORT_TYPE = DefaultSystemProxyApplier.NONE.name();
    int DEFAULT_GROUP_ID = -1;

    int getLongpoolSchedulerDelay();

    int getGroupId();

    String getGroupToken();

    boolean isLoggingEnabled();

    String getProxyType();

    String getProxyHost();

    int getProxyPort();

}
