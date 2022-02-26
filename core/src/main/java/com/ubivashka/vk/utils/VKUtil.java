package com.ubivashka.vk.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.ubivashka.vk.api.VkApiPlugin;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.users.UserFull;
import com.vk.api.sdk.queries.messages.MessagesSendQuery;

@Deprecated
public class VKUtil {
	private static final Random RANDOM = new Random();

	private final GroupActor actor;
	private final VkApiClient client;

	public VKUtil(VkApiPlugin<?, ?> plugin) {
		this.actor = plugin.getVkApiProvider().getActor();
		this.client = plugin.getVkApiProvider().getVkApiClient();
	}

	private MessagesSendQuery buildMSG(String text) {
		MessagesSendQuery query = client.messages().send(actor);
		query = query.randomId(RANDOM.nextInt());
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
			return client.messages().getConversationMembers(actor, message.getPeerId()).execute().getProfiles();
		} catch (ApiException | ClientException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void deleteMessage(Message message) {
		try {
			client.messages().delete(actor).messageIds(Arrays.asList(message.getId())).deleteForAll(true).execute();
		} catch (ApiException | ClientException e) {
			e.printStackTrace();
		}
	}

}
