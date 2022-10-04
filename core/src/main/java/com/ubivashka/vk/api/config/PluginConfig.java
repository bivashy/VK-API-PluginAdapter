package com.ubivashka.vk.api.config;

import com.ubivashka.vk.http.proxy.DefaultSystemProxyApplier;

public interface PluginConfig {
    String SETTINGS_KEY = "settings", GROUP_INFO_KEY = "groupInfo", PROXY_KEY = "proxy", SCHEDULER_DELAY_KEY = "delay", GROUP_ID_KEY = "groupID",
            GROUP_TOKEN_KEY = "groupToken", PROXY_TYPE_KEY = "type", PROXY_HOST_KEY = "host", PROXY_PORT_KEY = "port";
    String DEFAULT_PORT_TYPE = DefaultSystemProxyApplier.NONE.name();
    int DEFAULT_GROUP_ID = -1;

    Integer getLongpoolSchedulerDelay();

    Integer getGroupId();

    String getGroupToken();

    String getProxyType();

    String getProxyHost();

    Integer getProxyPort();

    default String getString(String... path) {
        return getStringDefault(null, path);
    }

    String getStringDefault(String def, String... path);

    default int getInt(String... path) {
        return getInt(-1, path);
    }

    int getInt(int def, String... path);

    Object getConfiguration();
}
