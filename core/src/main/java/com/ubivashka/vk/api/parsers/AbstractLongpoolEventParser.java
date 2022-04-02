package com.ubivashka.vk.api.parsers;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.ubivashka.vk.api.parsers.objects.CallbackButtonEvent;
import com.ubivashka.vk.api.parsers.objects.MessageTyping;
import com.vk.api.sdk.objects.audio.Audio;
import com.vk.api.sdk.objects.board.TopicComment;
import com.vk.api.sdk.objects.callback.BoardPostDelete;
import com.vk.api.sdk.objects.callback.ConfirmationMessage;
import com.vk.api.sdk.objects.callback.GroupChangePhoto;
import com.vk.api.sdk.objects.callback.GroupChangeSettings;
import com.vk.api.sdk.objects.callback.GroupJoin;
import com.vk.api.sdk.objects.callback.GroupLeave;
import com.vk.api.sdk.objects.callback.GroupOfficersEdit;
import com.vk.api.sdk.objects.callback.LikeAddRemove;
import com.vk.api.sdk.objects.callback.MarketComment;
import com.vk.api.sdk.objects.callback.MarketCommentDelete;
import com.vk.api.sdk.objects.callback.MessageAllow;
import com.vk.api.sdk.objects.callback.MessageDeny;
import com.vk.api.sdk.objects.callback.PhotoComment;
import com.vk.api.sdk.objects.callback.PhotoCommentDelete;
import com.vk.api.sdk.objects.callback.PollVoteNew;
import com.vk.api.sdk.objects.callback.UserBlock;
import com.vk.api.sdk.objects.callback.UserUnblock;
import com.vk.api.sdk.objects.callback.VideoComment;
import com.vk.api.sdk.objects.callback.VideoCommentDelete;
import com.vk.api.sdk.objects.callback.WallCommentDelete;
import com.vk.api.sdk.objects.callback.messages.CallbackMessage;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.photos.Photo;
import com.vk.api.sdk.objects.video.Video;
import com.vk.api.sdk.objects.wall.WallComment;
import com.vk.api.sdk.objects.wall.Wallpost;
import com.vk.api.sdk.queries.oauth.OAuthQueryBuilder;

public abstract class AbstractLongpoolEventParser implements LongpoolEventParser {
	private static final Logger LOG = LogManager.getLogger(OAuthQueryBuilder.class);
	private static final String CALLBACK_EVENT_MESSAGE_NEW = "message_new";
	private static final String CALLBACK_EVENT_MESSAGE_REPLY = "message_reply";
	private static final String CALLBACK_EVENT_MESSAGE_ALLOW = "message_allow";
	private static final String CALLBACK_EVENT_MESSAGE_DENY = "message_deny";
	private static final String CALLBACK_EVENT_MESSAGE_TYPING_STATE = "message_typing_state";
	private static final String CALLBACK_EVENT_MESSAGE_EDIT = "message_edit";
	private static final String CALLBACK_EVENT_MESSAGE_EVENT = "message_event";
	private static final String CALLBACK_EVENT_PHOTO_NEW = "photo_new";
	private static final String CALLBACK_EVENT_PHOTO_COMMENT_NEW = "photo_comment_new";
	private static final String CALLBACK_EVENT_PHOTO_COMMENT_EDIT = "photo_comment_edit";
	private static final String CALLBACK_EVENT_PHOTO_COMMENT_RESTORE = "photo_comment_restore";
	private static final String CALLBACK_EVENT_PHOTO_COMMENT_DELETE = "photo_comment_delete";
	private static final String CALLBACK_EVENT_AUDIO_NEW = "audio_new";
	private static final String CALLBACK_EVENT_VIDEO_NEW = "video_new";
	private static final String CALLBACK_EVENT_VIDEO_COMMENT_NEW = "video_comment_new";
	private static final String CALLBACK_EVENT_VIDEO_COMMENT_EDIT = "video_comment_edit";
	private static final String CALLBACK_EVENT_VIDEO_COMMENT_RESTORE = "video_comment_restore";
	private static final String CALLBACK_EVENT_VIDEO_COMMENT_DELETE = "video_comment_delete";
	private static final String CALLBACK_EVENT_WALL_POST_NEW = "wall_post_new";
	private static final String CALLBACK_EVENT_WALL_REPOST = "wall_repost";
	private static final String CALLBACK_EVENT_WALL_REPLY_NEW = "wall_reply_new";
	private static final String CALLBACK_EVENT_WALL_REPLY_EDIT = "wall_reply_edit";
	private static final String CALLBACK_EVENT_WALL_REPLY_RESTORE = "wall_reply_restore";
	private static final String CALLBACK_EVENT_WALL_REPLY_DELETE = "wall_reply_delete";
	private static final String CALLBACK_EVENT_LIKE_ADD = "like_add";
	private static final String CALLBACK_EVENT_LIKE_REMOVE = "like_remove";
	private static final String CALLBACK_EVENT_BOARD_POST_NEW = "board_post_new";
	private static final String CALLBACK_EVENT_BOARD_POST_EDIT = "board_post_edit";
	private static final String CALLBACK_EVENT_BOARD_POST_RESTORE = "board_post_restore";
	private static final String CALLBACK_EVENT_BOARD_POST_DELETE = "board_post_delete";
	private static final String CALLBACK_EVENT_MARKET_COMMENT_NEW = "market_comment_new";
	private static final String CALLBACK_EVENT_MARKET_COMMENT_EDIT = "market_comment_edit";
	private static final String CALLBACK_EVENT_MARKET_COMMENT_RESTORE = "market_comment_restore";
	private static final String CALLBACK_EVENT_MARKET_COMMENT_DELETE = "market_comment_delete";
	private static final String CALLBACK_EVENT_GROUP_LEAVE = "group_leave";
	private static final String CALLBACK_EVENT_GROUP_JOIN = "group_join";
	private static final String CALLBACK_EVENT_GROUP_CHANGE_SETTINGS = "group_change_settings";
	private static final String CALLBACK_EVENT_GROUP_CHANGE_PHOTO = "group_change_photo";
	private static final String CALLBACK_EVENT_GROUP_OFFICERS_EDIT = "group_officers_edit";
	private static final String CALLBACK_EVENT_POLL_VOTE_NEW = "poll_vote_new";
	private static final String CALLBACK_EVENT_USER_BLOCK = "user_block";
	private static final String CALLBACK_EVENT_USER_UNBLOCK = "user_unblock";
	private static final String CALLBACK_EVENT_CONFIRMATION = "confirmation";
	private static final Map<String, Type> CALLBACK_TYPES;

	static {
		Objects.requireNonNull(org.apache.commons.logging.impl.LogFactoryImpl.class);
		Objects.requireNonNull(org.apache.commons.logging.impl.Log4JLogger.class);
		Objects.requireNonNull(org.apache.commons.logging.impl.Jdk14Logger.class);
		Objects.requireNonNull(org.apache.commons.logging.impl.Jdk13LumberjackLogger.class);
		Objects.requireNonNull(org.apache.commons.logging.impl.SimpleLog.class);
	}
	
	static {
		Map<String, Type> types = new HashMap<>();
		types.put(CALLBACK_EVENT_MESSAGE_NEW, new TypeToken<CallbackMessage<Message>>() {
		}.getType());
		types.put(CALLBACK_EVENT_MESSAGE_REPLY, new TypeToken<CallbackMessage<Message>>() {
		}.getType());
		types.put(CALLBACK_EVENT_MESSAGE_EDIT, new TypeToken<CallbackMessage<Message>>() {
		}.getType());
		types.put(CALLBACK_EVENT_MESSAGE_ALLOW, new TypeToken<CallbackMessage<MessageAllow>>() {
		}.getType());
		types.put(CALLBACK_EVENT_MESSAGE_DENY, new TypeToken<CallbackMessage<MessageDeny>>() {
		}.getType());
		types.put(CALLBACK_EVENT_MESSAGE_TYPING_STATE, new TypeToken<CallbackMessage<MessageTyping>>() {
		}.getType());
		types.put(CALLBACK_EVENT_MESSAGE_EVENT, new TypeToken<CallbackMessage<CallbackButtonEvent>>() {
		}.getType());

		types.put(CALLBACK_EVENT_PHOTO_NEW, new TypeToken<CallbackMessage<Photo>>() {
		}.getType());
		types.put(CALLBACK_EVENT_PHOTO_COMMENT_NEW, new TypeToken<CallbackMessage<PhotoComment>>() {
		}.getType());
		types.put(CALLBACK_EVENT_PHOTO_COMMENT_EDIT, new TypeToken<CallbackMessage<PhotoComment>>() {
		}.getType());
		types.put(CALLBACK_EVENT_PHOTO_COMMENT_RESTORE, new TypeToken<CallbackMessage<PhotoComment>>() {
		}.getType());
		types.put(CALLBACK_EVENT_PHOTO_COMMENT_DELETE, new TypeToken<CallbackMessage<PhotoCommentDelete>>() {
		}.getType());

		types.put(CALLBACK_EVENT_AUDIO_NEW, new TypeToken<CallbackMessage<Audio>>() {
		}.getType());

		types.put(CALLBACK_EVENT_VIDEO_NEW, new TypeToken<CallbackMessage<Video>>() {
		}.getType());
		types.put(CALLBACK_EVENT_VIDEO_COMMENT_NEW, new TypeToken<CallbackMessage<VideoComment>>() {
		}.getType());
		types.put(CALLBACK_EVENT_VIDEO_COMMENT_EDIT, new TypeToken<CallbackMessage<VideoComment>>() {
		}.getType());
		types.put(CALLBACK_EVENT_VIDEO_COMMENT_RESTORE, new TypeToken<CallbackMessage<VideoComment>>() {
		}.getType());
		types.put(CALLBACK_EVENT_VIDEO_COMMENT_DELETE, new TypeToken<CallbackMessage<VideoCommentDelete>>() {
		}.getType());

		types.put(CALLBACK_EVENT_WALL_POST_NEW, new TypeToken<CallbackMessage<Wallpost>>() {
		}.getType());
		types.put(CALLBACK_EVENT_WALL_REPOST, new TypeToken<CallbackMessage<Wallpost>>() {
		}.getType());

		types.put(CALLBACK_EVENT_WALL_REPLY_NEW, new TypeToken<CallbackMessage<WallComment>>() {
		}.getType());
		types.put(CALLBACK_EVENT_WALL_REPLY_EDIT, new TypeToken<CallbackMessage<WallComment>>() {
		}.getType());
		types.put(CALLBACK_EVENT_WALL_REPLY_RESTORE, new TypeToken<CallbackMessage<WallComment>>() {
		}.getType());
		types.put(CALLBACK_EVENT_WALL_REPLY_DELETE, new TypeToken<CallbackMessage<WallCommentDelete>>() {
		}.getType());

		types.put(CALLBACK_EVENT_LIKE_ADD, new TypeToken<CallbackMessage<LikeAddRemove>>() {
		}.getType());
		types.put(CALLBACK_EVENT_LIKE_REMOVE, new TypeToken<CallbackMessage<LikeAddRemove>>() {
		}.getType());

		types.put(CALLBACK_EVENT_BOARD_POST_NEW, new TypeToken<CallbackMessage<TopicComment>>() {
		}.getType());
		types.put(CALLBACK_EVENT_BOARD_POST_EDIT, new TypeToken<CallbackMessage<TopicComment>>() {
		}.getType());
		types.put(CALLBACK_EVENT_BOARD_POST_RESTORE, new TypeToken<CallbackMessage<TopicComment>>() {
		}.getType());
		types.put(CALLBACK_EVENT_BOARD_POST_DELETE, new TypeToken<CallbackMessage<BoardPostDelete>>() {
		}.getType());

		types.put(CALLBACK_EVENT_MARKET_COMMENT_NEW, new TypeToken<CallbackMessage<MarketComment>>() {
		}.getType());
		types.put(CALLBACK_EVENT_MARKET_COMMENT_EDIT, new TypeToken<CallbackMessage<MarketComment>>() {
		}.getType());
		types.put(CALLBACK_EVENT_MARKET_COMMENT_RESTORE, new TypeToken<CallbackMessage<MarketComment>>() {
		}.getType());
		types.put(CALLBACK_EVENT_MARKET_COMMENT_DELETE, new TypeToken<CallbackMessage<MarketCommentDelete>>() {
		}.getType());

		types.put(CALLBACK_EVENT_GROUP_LEAVE, new TypeToken<CallbackMessage<GroupLeave>>() {
		}.getType());
		types.put(CALLBACK_EVENT_GROUP_JOIN, new TypeToken<CallbackMessage<GroupJoin>>() {
		}.getType());
		types.put(CALLBACK_EVENT_GROUP_CHANGE_SETTINGS, new TypeToken<CallbackMessage<GroupChangeSettings>>() {
		}.getType());
		types.put(CALLBACK_EVENT_GROUP_CHANGE_PHOTO, new TypeToken<CallbackMessage<GroupChangePhoto>>() {
		}.getType());
		types.put(CALLBACK_EVENT_GROUP_OFFICERS_EDIT, new TypeToken<CallbackMessage<GroupOfficersEdit>>() {
		}.getType());
		types.put(CALLBACK_EVENT_USER_BLOCK, new TypeToken<CallbackMessage<UserBlock>>() {
		}.getType());
		types.put(CALLBACK_EVENT_USER_UNBLOCK, new TypeToken<CallbackMessage<UserUnblock>>() {
		}.getType());

		types.put(CALLBACK_EVENT_POLL_VOTE_NEW, new TypeToken<CallbackMessage<PollVoteNew>>() {
		}.getType());

		CALLBACK_TYPES = Collections.unmodifiableMap(types);
	}

	protected static final Gson GSON = new Gson();

	@Override
	public boolean parse(String json) {
		JsonObject jsonObject = (JsonObject) GSON.fromJson(json, JsonObject.class);
		return parse(jsonObject);
	}

	@Override
	public boolean parse(JsonObject json) {
		String type = json.get("type").getAsString();
		if (type.equalsIgnoreCase(CALLBACK_EVENT_CONFIRMATION)) {
			ConfirmationMessage message = GSON.fromJson(json, ConfirmationMessage.class);
			confirmation(message.getGroupId(), message.getSecret());
			return true;
		}

		Type typeOfClass = CALLBACK_TYPES.get(type);
		if (typeOfClass == null) {
			LOG.warn("Unsupported callback event: " + type, type);
			return false;
		}
		if (json != null && json.has("object") && json.getAsJsonObject("object").has("client_info"))
			if (type.equalsIgnoreCase(CALLBACK_EVENT_MESSAGE_NEW)) {
				JsonElement messageElement = json.getAsJsonObject("object").get("message");
				json.getAsJsonObject("object").remove("client_info");
				json.getAsJsonObject("object").remove("message");
				json.add("object", messageElement);
			}

		CallbackMessage<Object> message = GSON.fromJson(json, typeOfClass);

		jsonEvent(message.getGroupId(), message.getSecret(), json);

		switch (type) {
		case CALLBACK_EVENT_MESSAGE_NEW:
			messageNew(message.getGroupId(), message.getSecret(), (Message) message.getObject());
			break;

		case CALLBACK_EVENT_MESSAGE_REPLY:
			messageReply(message.getGroupId(), message.getSecret(), (Message) message.getObject());
			break;

		case CALLBACK_EVENT_MESSAGE_EDIT:
			messageEdit(message.getGroupId(), message.getSecret(), (Message) message.getObject());
			break;

		case CALLBACK_EVENT_MESSAGE_ALLOW:
			messageAllow(message.getGroupId(), message.getSecret(), (MessageAllow) message.getObject());
			break;

		case CALLBACK_EVENT_MESSAGE_DENY:
			messageDeny(message.getGroupId(), message.getSecret(), (MessageDeny) message.getObject());
			break;
		case CALLBACK_EVENT_MESSAGE_TYPING_STATE:
			messageTyping(message.getGroupId(), message.getSecret(), (MessageTyping) message.getObject());
			break;
		case CALLBACK_EVENT_MESSAGE_EVENT:
			messageEvent(message.getGroupId(), message.getSecret(), (CallbackButtonEvent) message.getObject());
			break;
		case CALLBACK_EVENT_PHOTO_NEW:
			photoNew(message.getGroupId(), message.getSecret(), (Photo) message.getObject());
			break;

		case CALLBACK_EVENT_PHOTO_COMMENT_NEW:
			photoCommentNew(message.getGroupId(), message.getSecret(), (PhotoComment) message.getObject());
			break;

		case CALLBACK_EVENT_PHOTO_COMMENT_EDIT:
			photoCommentEdit(message.getGroupId(), message.getSecret(), (PhotoComment) message.getObject());
			break;

		case CALLBACK_EVENT_PHOTO_COMMENT_RESTORE:
			photoCommentRestore(message.getGroupId(), message.getSecret(), (PhotoComment) message.getObject());
			break;

		case CALLBACK_EVENT_PHOTO_COMMENT_DELETE:
			photoCommentDelete(message.getGroupId(), message.getSecret(), (PhotoCommentDelete) message.getObject());
			break;

		case CALLBACK_EVENT_AUDIO_NEW:
			audioNew(message.getGroupId(), message.getSecret(), (Audio) message.getObject());
			break;

		case CALLBACK_EVENT_VIDEO_NEW:
			videoNew(message.getGroupId(), message.getSecret(), (Video) message.getObject());
			break;

		case CALLBACK_EVENT_VIDEO_COMMENT_NEW:
			videoCommentNew(message.getGroupId(), message.getSecret(), (VideoComment) message.getObject());
			break;

		case CALLBACK_EVENT_VIDEO_COMMENT_EDIT:
			videoCommentEdit(message.getGroupId(), message.getSecret(), (VideoComment) message.getObject());
			break;

		case CALLBACK_EVENT_VIDEO_COMMENT_RESTORE:
			videoCommentRestore(message.getGroupId(), message.getSecret(), (VideoComment) message.getObject());
			break;

		case CALLBACK_EVENT_VIDEO_COMMENT_DELETE:
			videoCommentDelete(message.getGroupId(), message.getSecret(), (VideoCommentDelete) message.getObject());
			break;

		case CALLBACK_EVENT_WALL_POST_NEW:
			wallPostNew(message.getGroupId(), message.getSecret(), (Wallpost) message.getObject());
			break;

		case CALLBACK_EVENT_WALL_REPOST:
			wallRepost(message.getGroupId(), message.getSecret(), (Wallpost) message.getObject());
			break;

		case CALLBACK_EVENT_WALL_REPLY_NEW:
			wallReplyNew(message.getGroupId(), message.getSecret(), (WallComment) message.getObject());
			break;

		case CALLBACK_EVENT_WALL_REPLY_EDIT:
			wallReplyEdit(message.getGroupId(), message.getSecret(), (WallComment) message.getObject());
			break;

		case CALLBACK_EVENT_WALL_REPLY_RESTORE:
			wallReplyRestore(message.getGroupId(), message.getSecret(), (WallComment) message.getObject());
			break;
		case CALLBACK_EVENT_WALL_REPLY_DELETE:
			wallReplyDelete(message.getGroupId(), message.getSecret(), (WallCommentDelete) message.getObject());
			break;

		case CALLBACK_EVENT_LIKE_ADD:
			likeAdd(message.getGroupId(), message.getSecret(), (LikeAddRemove) message.getObject());
			break;
		case CALLBACK_EVENT_LIKE_REMOVE:
			likeRemove(message.getGroupId(), message.getSecret(), (LikeAddRemove) message.getObject());
			break;

		case CALLBACK_EVENT_BOARD_POST_NEW:
			boardPostNew(message.getGroupId(), message.getSecret(), (TopicComment) message.getObject());
			break;

		case CALLBACK_EVENT_BOARD_POST_EDIT:
			boardPostEdit(message.getGroupId(), message.getSecret(), (TopicComment) message.getObject());
			break;

		case CALLBACK_EVENT_BOARD_POST_RESTORE:
			boardPostRestore(message.getGroupId(), message.getSecret(), (TopicComment) message.getObject());
			break;

		case CALLBACK_EVENT_BOARD_POST_DELETE:
			boardPostDelete(message.getGroupId(), message.getSecret(), (BoardPostDelete) message.getObject());
			break;

		case CALLBACK_EVENT_MARKET_COMMENT_NEW:
			marketCommentNew(message.getGroupId(), message.getSecret(), (MarketComment) message.getObject());
			break;

		case CALLBACK_EVENT_MARKET_COMMENT_EDIT:
			marketCommentEdit(message.getGroupId(), message.getSecret(), (MarketComment) message.getObject());
			break;

		case CALLBACK_EVENT_MARKET_COMMENT_RESTORE:
			marketCommentRestore(message.getGroupId(), message.getSecret(), (MarketComment) message.getObject());
			break;

		case CALLBACK_EVENT_MARKET_COMMENT_DELETE:
			marketCommentDelete(message.getGroupId(), message.getSecret(), (MarketCommentDelete) message.getObject());
			break;

		case CALLBACK_EVENT_GROUP_LEAVE:
			groupLeave(message.getGroupId(), message.getSecret(), (GroupLeave) message.getObject());
			break;

		case CALLBACK_EVENT_GROUP_JOIN:
			groupJoin(message.getGroupId(), message.getSecret(), (GroupJoin) message.getObject());
			break;

		case CALLBACK_EVENT_GROUP_CHANGE_SETTINGS:
			groupChangeSettings(message.getGroupId(), message.getSecret(), (GroupChangeSettings) message.getObject());
			break;

		case CALLBACK_EVENT_GROUP_CHANGE_PHOTO:
			groupChangePhoto(message.getGroupId(), message.getSecret(), (GroupChangePhoto) message.getObject());
			break;

		case CALLBACK_EVENT_GROUP_OFFICERS_EDIT:
			groupOfficersEdit(message.getGroupId(), message.getSecret(), (GroupOfficersEdit) message.getObject());
			break;

		case CALLBACK_EVENT_USER_BLOCK:
			userBlock(message.getGroupId(), message.getSecret(), (UserBlock) message.getObject());
			break;

		case CALLBACK_EVENT_USER_UNBLOCK:
			userUnblock(message.getGroupId(), message.getSecret(), (UserUnblock) message.getObject());
			break;

		case CALLBACK_EVENT_POLL_VOTE_NEW:
			pollVoteNew(message.getGroupId(), message.getSecret(), (PollVoteNew) message.getObject());
			break;

		default:
			LOG.warn("Unsupported callback event", type);
			return false;
		}

		return true;
	}
}
