package com.ubivashka.vk.api.providers;

import com.ubivashka.vk.api.config.PluginConfig;
import com.ubivashka.vk.api.parsers.LongpoolEventParser;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;

public abstract class AbstractVkApiProvider implements VkApiProvider {
    protected final GroupActor groupActor;
    protected final VkApiClient vkApiClient;
    protected final LongpoolEventParser longpoolEventParser;

    public AbstractVkApiProvider(PluginConfig pluginConfig, LongpoolEventParser longpoolEventParser) {
        this.vkApiClient = new VkApiClient(HttpTransportClient.getInstance());

        int groupId =
                pluginConfig.getGroupId() < 0 ?
                        getGroupId(pluginConfig.getGroupToken()) :
                        pluginConfig.getGroupId();
        this.groupActor = new GroupActor(groupId, pluginConfig.getGroupToken());
        this.longpoolEventParser = longpoolEventParser;
    }

    @Override
    public GroupActor getActor() {
        return groupActor;
    }

    @Override
    public VkApiClient getVkApiClient() {
        return vkApiClient;
    }

    @Override
    public LongpoolEventParser getLongpoolParser() {
        return longpoolEventParser;
    }

    private int getGroupId(String accessToken) {
        try {
            return vkApiClient.groups().getByIdLegacy(new GroupActor(null, accessToken)).execute().get(0).getId();
        } catch(ApiException | ClientException e) {
            e.printStackTrace();
        }
        throw new UnsupportedOperationException("Cannot get group id from invalid access token");
    }
}
