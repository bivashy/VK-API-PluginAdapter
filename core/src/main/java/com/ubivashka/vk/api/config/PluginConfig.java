package com.ubivashka.vk.api.config;

public interface PluginConfig<T> {
	Integer getLongpoolSchedulerDelay();

	Integer getGroupId();

	String getGroupToken();

	T getConfiguration();
}
