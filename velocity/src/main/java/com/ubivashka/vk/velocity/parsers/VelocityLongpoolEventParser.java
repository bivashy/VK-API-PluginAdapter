package com.ubivashka.vk.velocity.parsers;

import com.google.gson.JsonObject;
import com.ubivashka.vk.api.parsers.AbstractLongpoolEventParser;
import com.ubivashka.vk.api.parsers.objects.CallbackButtonEvent;
import com.ubivashka.vk.api.parsers.objects.MessageTyping;
import com.ubivashka.vk.velocity.VelocityVkApiPlugin;
import com.ubivashka.vk.velocity.events.VKAudioNewEvent;
import com.ubivashka.vk.velocity.events.VKBoardDeleteEvent;
import com.ubivashka.vk.velocity.events.VKBoardEditEvent;
import com.ubivashka.vk.velocity.events.VKBoardNewEvent;
import com.ubivashka.vk.velocity.events.VKBoardRestoreEvent;
import com.ubivashka.vk.velocity.events.VKCallbackButtonPressEvent;
import com.ubivashka.vk.velocity.events.VKGroupChangePhotoEvent;
import com.ubivashka.vk.velocity.events.VKGroupChangeSettingsEvent;
import com.ubivashka.vk.velocity.events.VKGroupOfficersEditEvent;
import com.ubivashka.vk.velocity.events.VKJsonEvent;
import com.ubivashka.vk.velocity.events.VKLikeAddEvent;
import com.ubivashka.vk.velocity.events.VKLikeRemoveEvent;
import com.ubivashka.vk.velocity.events.VKMarketCommentDeleteEvent;
import com.ubivashka.vk.velocity.events.VKMarketCommentEditEvent;
import com.ubivashka.vk.velocity.events.VKMarketCommentEvent;
import com.ubivashka.vk.velocity.events.VKMarketCommentRestoreEvent;
import com.ubivashka.vk.velocity.events.VKMessageAllowEvent;
import com.ubivashka.vk.velocity.events.VKMessageDenyEvent;
import com.ubivashka.vk.velocity.events.VKMessageEditEvent;
import com.ubivashka.vk.velocity.events.VKMessageEvent;
import com.ubivashka.vk.velocity.events.VKMessageReplyEvent;
import com.ubivashka.vk.velocity.events.VKMessageTypingEvent;
import com.ubivashka.vk.velocity.events.VKPhotoCommentDeleteEvent;
import com.ubivashka.vk.velocity.events.VKPhotoCommentEditEvent;
import com.ubivashka.vk.velocity.events.VKPhotoCommentEvent;
import com.ubivashka.vk.velocity.events.VKPhotoCommentRestoreEvent;
import com.ubivashka.vk.velocity.events.VKPhotoNewEvent;
import com.ubivashka.vk.velocity.events.VKPollVoteNewEvent;
import com.ubivashka.vk.velocity.events.VKPostNewEvent;
import com.ubivashka.vk.velocity.events.VKPostReplyDeleteEvent;
import com.ubivashka.vk.velocity.events.VKPostReplyEditEvent;
import com.ubivashka.vk.velocity.events.VKPostReplyEvent;
import com.ubivashka.vk.velocity.events.VKPostReplyRestoreEvent;
import com.ubivashka.vk.velocity.events.VKPostRepostEvent;
import com.ubivashka.vk.velocity.events.VKUserBlockEvent;
import com.ubivashka.vk.velocity.events.VKUserGroupJoinEvent;
import com.ubivashka.vk.velocity.events.VKUserGroupLeaveEvent;
import com.ubivashka.vk.velocity.events.VKUserUnblockEvent;
import com.ubivashka.vk.velocity.events.VKVideoCommentDeleteEvent;
import com.ubivashka.vk.velocity.events.VKVideoCommentEditEvent;
import com.ubivashka.vk.velocity.events.VKVideoCommentEvent;
import com.ubivashka.vk.velocity.events.VKVideoCommentRestoreEvent;
import com.ubivashka.vk.velocity.events.VKVideoNewEvent;
import com.vk.api.sdk.objects.audio.Audio;
import com.vk.api.sdk.objects.board.TopicComment;
import com.vk.api.sdk.objects.callback.BoardPostDelete;
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
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.photos.Photo;
import com.vk.api.sdk.objects.video.Video;
import com.vk.api.sdk.objects.wall.WallComment;
import com.vk.api.sdk.objects.wall.Wallpost;

public class VelocityLongpoolEventParser extends AbstractLongpoolEventParser {
	private final VelocityVkApiPlugin plugin;

	public VelocityLongpoolEventParser(VelocityVkApiPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public void messageNew(Integer groupId, String secret, Message message) {
		VKMessageEvent messageEvent = new VKMessageEvent(message, groupId);
		plugin.getProxyServer().getEventManager().fire(messageEvent).thenAccept(event -> {
			if (!event.getResult().isAllowed())
				plugin.getVkApiProvider().deleteMessage(message);
		});
	}

	@Override
	public void messageReply(Integer groupId, String secret, Message message) {
		VKMessageReplyEvent replyEvent = new VKMessageReplyEvent(message, groupId);
		plugin.getProxyServer().getEventManager().fire(replyEvent).thenAccept(event -> {
			if (!event.getResult().isAllowed())
				plugin.getVkApiProvider().deleteMessage(message);
		});
	}

	@Override
	public void messageEdit(Integer groupId, String secret, Message messageEdit) {
		new VKMessageEditEvent(messageEdit, groupId).callEvent();
	}

	@Override
	public void messageAllow(Integer groupId, String secret, MessageAllow messageAllow) {
		new VKMessageAllowEvent(messageAllow, groupId).callEvent();
	}

	@Override
	public void messageDeny(Integer groupId, String secret, MessageDeny messageDeny) {
		new VKMessageDenyEvent(messageDeny, groupId).callEvent();
	}

	@Override
	public void messageTyping(Integer groupId, String secret, MessageTyping messageTyping) {
		new VKMessageTypingEvent(messageTyping, groupId).callEvent();
	}

	@Override
	public void messageEvent(Integer groupId, String secret, CallbackButtonEvent buttonEvent) {
		new VKCallbackButtonPressEvent(buttonEvent, groupId).callEvent();
	}

	@Override
	public void photoNew(Integer groupId, String secret, Photo photo) {
		new VKPhotoNewEvent(photo, groupId).callEvent();
	}

	@Override
	public void photoCommentNew(Integer groupId, String secret, PhotoComment comment) {
		new VKPhotoCommentEvent(comment, groupId).callEvent();
	}

	@Override
	public void photoCommentEdit(Integer groupId, String secret, PhotoComment comment) {
		new VKPhotoCommentEditEvent(comment, groupId).callEvent();
	}

	@Override
	public void photoCommentRestore(Integer groupId, String secret, PhotoComment comment) {
		new VKPhotoCommentRestoreEvent(comment, groupId).callEvent();
	}

	@Override
	public void photoCommentDelete(Integer groupId, String secret, PhotoCommentDelete commentDelete) {
		new VKPhotoCommentDeleteEvent(commentDelete, groupId).callEvent();
	}

	@Override
	public void audioNew(Integer groupId, String secret, Audio audio) {
		new VKAudioNewEvent(audio, groupId).callEvent();
	}

	@Override
	public void videoNew(Integer groupId, String secret, Video video) {
		new VKVideoNewEvent(video, groupId).callEvent();
	}

	@Override
	public void videoCommentNew(Integer groupId, String secret, VideoComment videoComment) {
		new VKVideoCommentEvent(videoComment, groupId).callEvent();
	}

	@Override
	public void videoCommentEdit(Integer groupId, String secret, VideoComment videoComment) {
		new VKVideoCommentEditEvent(videoComment, groupId).callEvent();
	}

	@Override
	public void videoCommentRestore(Integer groupId, String secret, VideoComment videoComment) {
		new VKVideoCommentRestoreEvent(videoComment, groupId).callEvent();
	}

	@Override
	public void videoCommentDelete(Integer groupId, String secret, VideoCommentDelete videoCommentDelete) {
		new VKVideoCommentDeleteEvent(videoCommentDelete, groupId).callEvent();
	}

	@Override
	public void wallPostNew(Integer groupId, String secret, Wallpost post) {
		new VKPostNewEvent(post, groupId).callEvent();
	}

	@Override
	public void wallRepost(Integer groupId, String secret, Wallpost post) {
		new VKPostRepostEvent(post, groupId).callEvent();
	}

	@Override
	public void wallReplyNew(Integer groupId, String secret, WallComment postComment) {
		new VKPostReplyEvent(postComment, groupId).callEvent();
	}

	@Override
	public void wallReplyEdit(Integer groupId, String secret, WallComment postComment) {
		new VKPostReplyEditEvent(postComment, groupId).callEvent();
	}

	@Override
	public void wallReplyRestore(Integer groupId, String secret, WallComment postComment) {
		new VKPostReplyRestoreEvent(postComment, groupId).callEvent();
	}

	@Override
	public void wallReplyDelete(Integer groupId, String secret, WallCommentDelete postCommentDelete) {
		new VKPostReplyDeleteEvent(postCommentDelete, groupId).callEvent();
	}

	@Override
	public void likeAdd(Integer groupId, String secret, LikeAddRemove likeAdd) {
		new VKLikeAddEvent(likeAdd, groupId).callEvent();
	}

	@Override
	public void likeRemove(Integer groupId, String secret, LikeAddRemove likeRemove) {
		new VKLikeRemoveEvent(likeRemove, groupId).callEvent();
	}

	@Override
	public void boardPostNew(Integer groupId, String secret, TopicComment topicComment) {
		new VKBoardNewEvent(topicComment, groupId).callEvent();
	}

	@Override
	public void boardPostEdit(Integer groupId, String secret, TopicComment topicComment) {
		new VKBoardEditEvent(topicComment, groupId).callEvent();
	}

	@Override
	public void boardPostRestore(Integer groupId, String secret, TopicComment topicComment) {
		new VKBoardRestoreEvent(topicComment, groupId).callEvent();
	}

	@Override
	public void boardPostDelete(Integer groupId, String secret, BoardPostDelete boardDelete) {
		new VKBoardDeleteEvent(boardDelete, groupId).callEvent();
	}

	@Override
	public void marketCommentNew(Integer groupId, String secret, MarketComment comment) {
		new VKMarketCommentEvent(comment, groupId).callEvent();
	}

	@Override
	public void marketCommentEdit(Integer groupId, String secret, MarketComment comment) {
		new VKMarketCommentEditEvent(comment, groupId).callEvent();
	}

	@Override
	public void marketCommentRestore(Integer groupId, String secret, MarketComment comment) {
		new VKMarketCommentRestoreEvent(comment, groupId).callEvent();
	}

	@Override
	public void marketCommentDelete(Integer groupId, String secret, MarketCommentDelete commentDelete) {
		new VKMarketCommentDeleteEvent(commentDelete, groupId).callEvent();
	}

	@Override
	public void groupLeave(Integer groupId, String secret, GroupLeave groupLeave) {
		new VKUserGroupLeaveEvent(groupLeave, groupId).callEvent();
	}

	@Override
	public void groupJoin(Integer groupId, String secret, GroupJoin groupJoin) {
		new VKUserGroupJoinEvent(groupJoin, groupId).callEvent();
	}

	@Override
	public void groupChangeSettings(Integer groupId, String secret, GroupChangeSettings changeSettings) {
		new VKGroupChangeSettingsEvent(changeSettings, groupId).callEvent();
	}

	@Override
	public void groupChangePhoto(Integer groupId, String secret, GroupChangePhoto changePhoto) {
		new VKGroupChangePhotoEvent(changePhoto, groupId).callEvent();
	}

	@Override
	public void groupOfficersEdit(Integer groupId, String secret, GroupOfficersEdit officersEdit) {
		new VKGroupOfficersEditEvent(officersEdit, groupId).callEvent();
	}

	@Override
	public void pollVoteNew(Integer groupId, String secret, PollVoteNew vote) {
		new VKPollVoteNewEvent(vote, groupId).callEvent();
	}

	@Override
	public void userBlock(Integer groupId, String secret, UserBlock block) {
		new VKUserBlockEvent(block, groupId).callEvent();
	}

	@Override
	public void userUnblock(Integer groupId, String secret, UserUnblock unblock) {
		new VKUserUnblockEvent(unblock, groupId).callEvent();
	}

	@Override
	public void confirmation(Integer groupId, String secret) {
	}

	@Override
	public void jsonEvent(Integer groupId, String secret, JsonObject json) {
		new VKJsonEvent(json, groupId).callEvent();
	}
}