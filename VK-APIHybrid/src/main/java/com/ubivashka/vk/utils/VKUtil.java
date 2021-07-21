package com.ubivashka.vk.utils;

import java.util.Arrays;
import java.util.List;

import com.ubivashka.vk.VKAPIPlugin;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.users.UserFull;
import com.vk.api.sdk.queries.messages.MessagesSendQuery;

public class VKUtil {
	private VKAPIPlugin plugin;

	public VKUtil(VKAPIPlugin plugin) {
		this.plugin = plugin;
	}

	private MessagesSendQuery buildMSG(String text) {
		MessagesSendQuery query = plugin.getVK().messages().send(plugin.getActor());
		query = query.randomId(plugin.getRandom().nextInt(10000));
		query = query.message(text);
		return query;
	}

	public void sendAnswerMSG(Message message, String text, boolean replyTo) {
		MessagesSendQuery query = buildMSG(text);
		query = query.peerId(message.getPeerId());
		try {
			query.execute();
		} catch (ApiException | ClientException e) {
			e.printStackTrace();
		}
	}

	public void sendMSGtoUser(Integer id, String text) {
		MessagesSendQuery query = buildMSG(text);
		query = query.userId(id);
		try {
			query.execute();
		} catch (ApiException | ClientException e) {
			e.printStackTrace();
		}
	}

	public void sendMSGtoPeer(Integer peerID, String text) {
		MessagesSendQuery query = buildMSG(text);
		query = query.peerId(peerID);
		try {
			query.execute();
		} catch (ApiException | ClientException e) {
			e.printStackTrace();
		}
	}

	public List<UserFull> getMembers(Message message) {
		try {
			return plugin.getVK().messages().getConversationMembers(plugin.getActor(), message.getPeerId()).execute()
					.getProfiles();
		} catch (ApiException | ClientException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void deleteMessage(Message message) {
		try {
			plugin.getVK().messages().delete(plugin.getActor()).messageIds(Arrays.asList(message.getId()))
					.deleteForAll(true).execute();
		} catch (ApiException | ClientException e) {
			e.printStackTrace();
		}
	}

}
