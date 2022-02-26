package com.ubivashka.vk.bukkit.parsers;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.Event;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.ubivashka.vk.api.VkApiPlugin;
import com.ubivashka.vk.api.parsers.LongpoolEventParser;
import com.ubivashka.vk.api.parsers.objects.CallbackButtonEvent;
import com.ubivashka.vk.api.parsers.objects.MessageTyping;
import com.ubivashka.vk.bukkit.events.VKAudioNewEvent;
import com.ubivashka.vk.bukkit.events.VKBoardDeleteEvent;
import com.ubivashka.vk.bukkit.events.VKBoardEditEvent;
import com.ubivashka.vk.bukkit.events.VKBoardNewEvent;
import com.ubivashka.vk.bukkit.events.VKBoardRestoreEvent;
import com.ubivashka.vk.bukkit.events.VKCallbackButtonPressEvent;
import com.ubivashka.vk.bukkit.events.VKGroupChangePhotoEvent;
import com.ubivashka.vk.bukkit.events.VKGroupChangeSettingsEvent;
import com.ubivashka.vk.bukkit.events.VKGroupOfficersEditEvent;
import com.ubivashka.vk.bukkit.events.VKJsonEvent;
import com.ubivashka.vk.bukkit.events.VKLikeAddEvent;
import com.ubivashka.vk.bukkit.events.VKLikeRemoveEvent;
import com.ubivashka.vk.bukkit.events.VKMarketCommentDeleteEvent;
import com.ubivashka.vk.bukkit.events.VKMarketCommentEditEvent;
import com.ubivashka.vk.bukkit.events.VKMarketCommentEvent;
import com.ubivashka.vk.bukkit.events.VKMarketCommentRestoreEvent;
import com.ubivashka.vk.bukkit.events.VKMessageAllowEvent;
import com.ubivashka.vk.bukkit.events.VKMessageDenyEvent;
import com.ubivashka.vk.bukkit.events.VKMessageEditEvent;
import com.ubivashka.vk.bukkit.events.VKMessageEvent;
import com.ubivashka.vk.bukkit.events.VKMessageReplyEvent;
import com.ubivashka.vk.bukkit.events.VKMessageTypingEvent;
import com.ubivashka.vk.bukkit.events.VKPhotoCommentDeleteEvent;
import com.ubivashka.vk.bukkit.events.VKPhotoCommentEditEvent;
import com.ubivashka.vk.bukkit.events.VKPhotoCommentEvent;
import com.ubivashka.vk.bukkit.events.VKPhotoCommentRestoreEvent;
import com.ubivashka.vk.bukkit.events.VKPhotoNewEvent;
import com.ubivashka.vk.bukkit.events.VKPollVoteNewEvent;
import com.ubivashka.vk.bukkit.events.VKPostNewEvent;
import com.ubivashka.vk.bukkit.events.VKPostReplyDeleteEvent;
import com.ubivashka.vk.bukkit.events.VKPostReplyEditEvent;
import com.ubivashka.vk.bukkit.events.VKPostReplyEvent;
import com.ubivashka.vk.bukkit.events.VKPostReplyRestoreEvent;
import com.ubivashka.vk.bukkit.events.VKPostRepostEvent;
import com.ubivashka.vk.bukkit.events.VKUserBlockEvent;
import com.ubivashka.vk.bukkit.events.VKUserGroupJoinEvent;
import com.ubivashka.vk.bukkit.events.VKUserGroupLeaveEvent;
import com.ubivashka.vk.bukkit.events.VKUserUnblockEvent;
import com.ubivashka.vk.bukkit.events.VKVideoCommentDeleteEvent;
import com.ubivashka.vk.bukkit.events.VKVideoCommentEditEvent;
import com.ubivashka.vk.bukkit.events.VKVideoCommentEvent;
import com.ubivashka.vk.bukkit.events.VKVideoCommentRestoreEvent;
import com.ubivashka.vk.bukkit.events.VKVideoNewEvent;
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

public class BukkitLongpoolEventParser implements LongpoolEventParser {
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
	private final static Map<String, Type> CALLBACK_TYPES;

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

	private static final Gson GSON = new Gson();

	private VkApiPlugin<Event, ConfigurationSection> plugin;

	public BukkitLongpoolEventParser(VkApiPlugin<Event, ConfigurationSection> plugin) {
		this.plugin = plugin;
	}

	public void messageNew(Integer groupId, Message message) {
		VKMessageEvent messageEvent = new VKMessageEvent(message);
		plugin.callEvent(messageEvent);
		if (messageEvent.isCancelled())
			plugin.getVkApiProvider().deleteMessage(message);
	}

	public void messageNew(Integer groupId, String secret, Message message) {
		messageNew(groupId, message);
	}

	public void messageReply(Integer groupId, Message message) {
		VKMessageReplyEvent replyEvent = new VKMessageReplyEvent(message);
		plugin.callEvent(replyEvent);
		if (replyEvent.isCancelled())
			plugin.getVkApiProvider().deleteMessage(message);

	}

	public void messageReply(Integer groupId, String secret, Message message) {
		messageReply(groupId, message);
	}

	public void messageEdit(Integer groupId, Message messageEdit) {
		VKMessageEditEvent editEvent = new VKMessageEditEvent(messageEdit);
		plugin.callEvent(editEvent);
	}

	public void messageEdit(Integer groupId, String secret, Message messageEdit) {
		messageEdit(groupId, messageEdit);
	}

	public void messageAllow(Integer groupId, MessageAllow messageAllow) {
		VKMessageAllowEvent allowEvent = new VKMessageAllowEvent(messageAllow);
		plugin.callEvent(allowEvent);
	}

	public void messageAllow(Integer groupId, String secret, MessageAllow messageAllow) {
		messageAllow(groupId, messageAllow);
	}

	public void messageDeny(Integer groupId, MessageDeny messageDeny) {
		VKMessageDenyEvent denyEvent = new VKMessageDenyEvent(messageDeny);
		plugin.callEvent(denyEvent);
	}

	public void messageDeny(Integer groupId, String secret, MessageDeny messageDeny) {
		messageDeny(groupId, messageDeny);
	}

	public void messageTyping(Integer groupId, MessageTyping messageTyping) {
		VKMessageTypingEvent typingEvent = new VKMessageTypingEvent(messageTyping);
		plugin.callEvent(typingEvent);
	}

	public void messageTyping(Integer groupId, String secret, MessageTyping messageTyping) {
		messageTyping(groupId, messageTyping);
	}

	public void messageEvent(Integer groupId, CallbackButtonEvent buttonEvent) {
		VKCallbackButtonPressEvent buttonPressEvent = new VKCallbackButtonPressEvent(buttonEvent);
		plugin.callEvent(buttonPressEvent);
	}

	public void messageEvent(Integer groupId, String secret, CallbackButtonEvent buttonEvent) {
		messageEvent(groupId, buttonEvent);
	}

	public void photoNew(Integer groupId, Photo photo) {
		VKPhotoNewEvent newPhotoEvent = new VKPhotoNewEvent(photo);
		plugin.callEvent(newPhotoEvent);
	}

	public void photoNew(Integer groupId, String secret, Photo photo) {
		photoNew(groupId, photo);
	}

	public void photoCommentNew(Integer groupId, PhotoComment comment) {
		VKPhotoCommentEvent photoCommentEvent = new VKPhotoCommentEvent(comment);
		plugin.callEvent(photoCommentEvent);
	}

	public void photoCommentNew(Integer groupId, String secret, PhotoComment comment) {
		photoCommentNew(groupId, comment);
	}

	public void photoCommentEdit(Integer groupId, PhotoComment comment) {
		VKPhotoCommentEditEvent photoCommentEditEvent = new VKPhotoCommentEditEvent(comment);
		plugin.callEvent(photoCommentEditEvent);
	}

	public void photoCommentEdit(Integer groupId, String secret, PhotoComment comment) {
		photoCommentEdit(groupId, comment);
	}

	public void photoCommentRestore(Integer groupId, PhotoComment comment) {
		VKPhotoCommentRestoreEvent photoCommentRestoreEvent = new VKPhotoCommentRestoreEvent(comment);
		plugin.callEvent(photoCommentRestoreEvent);
	}

	public void photoCommentRestore(Integer groupId, String secret, PhotoComment comment) {
		photoCommentRestore(groupId, comment);
	}

	public void photoCommentDelete(Integer groupId, PhotoCommentDelete commentDelete) {
		VKPhotoCommentDeleteEvent photoCommentDeleteEvent = new VKPhotoCommentDeleteEvent(commentDelete);
		plugin.callEvent(photoCommentDeleteEvent);
	}

	public void photoCommentDelete(Integer groupId, String secret, PhotoCommentDelete commentDelete) {
		photoCommentDelete(groupId, commentDelete);
	}

	public void audioNew(Integer groupId, Audio audio) {
		VKAudioNewEvent newAudioEvent = new VKAudioNewEvent(audio);
		plugin.callEvent(newAudioEvent);
	}

	public void audioNew(Integer groupId, String secret, Audio audio) {
		audioNew(groupId, audio);
	}

	public void videoNew(Integer groupId, Video video) {
		VKVideoNewEvent newVideoEvent = new VKVideoNewEvent(video);
		plugin.callEvent(newVideoEvent);
	}

	public void videoNew(Integer groupId, String secret, Video video) {
		videoNew(groupId, video);
	}

	public void videoCommentNew(Integer groupId, VideoComment videoComment) {
		VKVideoCommentEvent videoCommentEvent = new VKVideoCommentEvent(videoComment);
		plugin.callEvent(videoCommentEvent);
	}

	public void videoCommentNew(Integer groupId, String secret, VideoComment videoComment) {
		videoCommentNew(groupId, videoComment);
	}

	public void videoCommentEdit(Integer groupId, VideoComment videoComment) {
		VKVideoCommentEditEvent videoCommentEditEvent = new VKVideoCommentEditEvent(videoComment);
		plugin.callEvent(videoCommentEditEvent);
	}

	public void videoCommentEdit(Integer groupId, String secret, VideoComment videoComment) {
		videoCommentEdit(groupId, videoComment);
	}

	public void videoCommentRestore(Integer groupId, VideoComment videoComment) {
		VKVideoCommentRestoreEvent videoCommentRestoreEvent = new VKVideoCommentRestoreEvent(videoComment);
		plugin.callEvent(videoCommentRestoreEvent);
	}

	public void videoCommentRestore(Integer groupId, String secret, VideoComment videoComment) {
		videoCommentRestore(groupId, videoComment);
	}

	public void videoCommentDelete(Integer groupId, VideoCommentDelete videoCommentDelete) {
		VKVideoCommentDeleteEvent videoCommentDeleteEvent = new VKVideoCommentDeleteEvent(videoCommentDelete);
		plugin.callEvent(videoCommentDeleteEvent);
	}

	public void videoCommentDelete(Integer groupId, String secret, VideoCommentDelete videoCommentDelete) {
		videoCommentDelete(groupId, videoCommentDelete);
	}

	public void wallPostNew(Integer groupId, Wallpost post) {
		VKPostNewEvent postEvent = new VKPostNewEvent(post);
		plugin.callEvent(postEvent);
	}

	public void wallPostNew(Integer groupId, String secret, Wallpost post) {
		wallPostNew(groupId, post);
	}

	public void wallRepost(Integer groupId, Wallpost post) {
		VKPostRepostEvent postRepostEvent = new VKPostRepostEvent(post);
		plugin.callEvent(postRepostEvent);
	}

	public void wallRepost(Integer groupId, String secret, Wallpost post) {
		wallRepost(groupId, post);
	}

	public void wallReplyNew(Integer groupId, WallComment postComment) {
		VKPostReplyEvent postReplyEvent = new VKPostReplyEvent(postComment);
		plugin.callEvent(postReplyEvent);
	}

	public void wallReplyNew(Integer groupId, String secret, WallComment postComment) {
		wallReplyNew(groupId, postComment);
	}

	public void wallReplyEdit(Integer groupId, WallComment postComment) {
		VKPostReplyEditEvent postReplyEditEvent = new VKPostReplyEditEvent(postComment);
		plugin.callEvent(postReplyEditEvent);
	}

	public void wallReplyEdit(Integer groupId, String secret, WallComment postComment) {
		wallReplyEdit(groupId, postComment);
	}

	public void wallReplyRestore(Integer groupId, WallComment postComment) {
		VKPostReplyRestoreEvent postReplyRestoreEvent = new VKPostReplyRestoreEvent(postComment);
		plugin.callEvent(postReplyRestoreEvent);
	}

	public void wallReplyRestore(Integer groupId, String secret, WallComment postComment) {
		wallReplyRestore(groupId, postComment);
	}

	public void wallReplyDelete(Integer groupId, WallCommentDelete postCommentDelete) {
		VKPostReplyDeleteEvent postReplyDeleteEvent = new VKPostReplyDeleteEvent(postCommentDelete);
		plugin.callEvent(postReplyDeleteEvent);
	}

	public void wallReplyDelete(Integer groupId, String secret, WallCommentDelete postCommentDelete) {
		wallReplyDelete(groupId, postCommentDelete);
	}

	public void likeAdd(Integer groupId, LikeAddRemove likeAdd) {
		VKLikeAddEvent likeAddEvent = new VKLikeAddEvent(likeAdd);
		plugin.callEvent(likeAddEvent);
	}

	public void likeAdd(Integer groupId, String secret, LikeAddRemove likeAdd) {
		likeAdd(groupId, likeAdd);
	}

	public void likeRemove(Integer groupId, LikeAddRemove likeRemove) {
		VKLikeRemoveEvent likeRemoveEvent = new VKLikeRemoveEvent(likeRemove);
		plugin.callEvent(likeRemoveEvent);
	}

	public void likeRemove(Integer groupId, String secret, LikeAddRemove likeRemove) {
		likeRemove(groupId, likeRemove);
	}

	public void boardPostNew(Integer groupId, TopicComment topicComment) {
		VKBoardNewEvent boardNewEvent = new VKBoardNewEvent(topicComment);
		plugin.callEvent(boardNewEvent);
	}

	public void boardPostNew(Integer groupId, String secret, TopicComment topicComment) {
		boardPostNew(groupId, topicComment);
	}

	public void boardPostEdit(Integer groupId, TopicComment topicComment) {
		VKBoardEditEvent boardEditEvent = new VKBoardEditEvent(topicComment);
		plugin.callEvent(boardEditEvent);
	}

	public void boardPostEdit(Integer groupId, String secret, TopicComment topicComment) {
		boardPostEdit(groupId, topicComment);
	}

	public void boardPostRestore(Integer groupId, TopicComment topicComment) {
		VKBoardRestoreEvent boardRestoreEvent = new VKBoardRestoreEvent(topicComment);
		plugin.callEvent(boardRestoreEvent);
	}

	public void boardPostRestore(Integer groupId, String secret, TopicComment topicComment) {
		boardPostRestore(groupId, topicComment);
	}

	public void boardPostDelete(Integer groupId, BoardPostDelete boardDelete) {
		VKBoardDeleteEvent boardDeleteEvent = new VKBoardDeleteEvent(boardDelete);
		plugin.callEvent(boardDeleteEvent);
	}

	public void boardPostDelete(Integer groupId, String secret, BoardPostDelete boardDelete) {
		boardPostDelete(groupId, boardDelete);
	}

	public void marketCommentNew(Integer groupId, MarketComment comment) {
		VKMarketCommentEvent marketCommentEvent = new VKMarketCommentEvent(comment);
		plugin.callEvent(marketCommentEvent);
	}

	public void marketCommentNew(Integer groupId, String secret, MarketComment comment) {
		marketCommentNew(groupId, comment);
	}

	public void marketCommentEdit(Integer groupId, MarketComment comment) {
		VKMarketCommentEditEvent marketCommentEditEvent = new VKMarketCommentEditEvent(comment);
		plugin.callEvent(marketCommentEditEvent);
	}

	public void marketCommentEdit(Integer groupId, String secret, MarketComment comment) {
		marketCommentEdit(groupId, comment);
	}

	public void marketCommentRestore(Integer groupId, MarketComment comment) {
		VKMarketCommentRestoreEvent marketCommentRestoreEvent = new VKMarketCommentRestoreEvent(comment);
		plugin.callEvent(marketCommentRestoreEvent);
	}

	public void marketCommentRestore(Integer groupId, String secret, MarketComment comment) {
		marketCommentRestore(groupId, comment);
	}

	public void marketCommentDelete(Integer groupId, MarketCommentDelete commentDelete) {
		VKMarketCommentDeleteEvent marketCommentDeleteEvent = new VKMarketCommentDeleteEvent(commentDelete);
		plugin.callEvent(marketCommentDeleteEvent);
	}

	public void marketCommentDelete(Integer groupId, String secret, MarketCommentDelete commentDelete) {
		marketCommentDelete(groupId, commentDelete);
	}

	public void groupLeave(Integer groupId, GroupLeave groupLeave) {
		VKUserGroupLeaveEvent leaveEvent = new VKUserGroupLeaveEvent(groupLeave);
		plugin.callEvent(leaveEvent);
	}

	public void groupLeave(Integer groupId, String secret, GroupLeave groupLeave) {
		groupLeave(groupId, groupLeave);
	}

	public void groupJoin(Integer groupId, GroupJoin groupJoin) {
		VKUserGroupJoinEvent joinEvent = new VKUserGroupJoinEvent(groupJoin);
		plugin.callEvent(joinEvent);
	}

	public void groupJoin(Integer groupId, String secret, GroupJoin groupJoin) {
		groupJoin(groupId, groupJoin);
	}

	public void groupChangeSettings(Integer groupId, GroupChangeSettings changeSettings) {
		VKGroupChangeSettingsEvent groupEvent = new VKGroupChangeSettingsEvent(changeSettings);
		plugin.callEvent(groupEvent);
	}

	public void groupChangeSettings(Integer groupId, String secret, GroupChangeSettings changeSettings) {
		groupChangeSettings(groupId, changeSettings);
	}

	public void groupChangePhoto(Integer groupId, GroupChangePhoto changePhoto) {
		VKGroupChangePhotoEvent groupEvent = new VKGroupChangePhotoEvent(changePhoto);
		plugin.callEvent(groupEvent);
	}

	public void groupChangePhoto(Integer groupId, String secret, GroupChangePhoto changePhoto) {
		groupChangePhoto(groupId, changePhoto);
	}

	public void groupOfficersEdit(Integer groupId, GroupOfficersEdit officersEdit) {
		VKGroupOfficersEditEvent groupEvent = new VKGroupOfficersEditEvent(officersEdit);
		plugin.callEvent(groupEvent);
	}

	public void groupOfficersEdit(Integer groupId, String secret, GroupOfficersEdit officersEdit) {
		groupOfficersEdit(groupId, officersEdit);
	}

	public void pollVoteNew(Integer groupId, PollVoteNew vote) {
		VKPollVoteNewEvent pollVoteEvent = new VKPollVoteNewEvent(vote);
		plugin.callEvent(pollVoteEvent);
	}

	public void pollVoteNew(Integer groupId, String secret, PollVoteNew vote) {
		pollVoteNew(groupId, vote);
	}

	public void userBlock(Integer groupId, UserBlock block) {
		VKUserBlockEvent blockEvent = new VKUserBlockEvent(block);
		plugin.callEvent(blockEvent);
	}

	public void userBlock(Integer groupId, String secret, UserBlock block) {
		userBlock(groupId, block);
	}

	public void userUnblock(Integer groupId, UserUnblock unblock) {
		VKUserUnblockEvent blockEvent = new VKUserUnblockEvent(unblock);
		plugin.callEvent(blockEvent);
	}

	public void userUnblock(Integer groupId, String secret, UserUnblock unblock) {
		userUnblock(groupId, unblock);
	}

	public void confirmation(Integer groupId) {
	}

	public void confirmation(Integer groupId, String secret) {
		confirmation(groupId);
	}

	public void jsonEvent(Integer groupId, JsonObject json) {
		VKJsonEvent jsonEvent = new VKJsonEvent(json);
		plugin.callEvent(jsonEvent);
	}

	public void jsonEvent(Integer groupId, String secret, JsonObject json) {
		jsonEvent(groupId, json);
	}

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
