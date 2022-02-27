package com.ubivashka.vk.api.parsers;

import com.google.gson.JsonObject;
import com.ubivashka.vk.api.parsers.objects.CallbackButtonEvent;
import com.ubivashka.vk.api.parsers.objects.MessageTyping;
import com.ubivashka.vk.bungee.events.VKMessageEvent;
import com.ubivashka.vk.bungee.events.VKMessageReplyEvent;
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

public interface LongpoolEventParser {
	public void messageNew(Integer groupId, String secret, Message message);

	public void messageReply(Integer groupId, String secret, Message message);

	public void messageEdit(Integer groupId, String secret, Message messageEdit);

	public void messageAllow(Integer groupId, String secret, MessageAllow messageAllow);

	public void messageDeny(Integer groupId, String secret, MessageDeny messageDeny);

	public void messageTyping(Integer groupId, String secret, MessageTyping messageTyping);

	public void messageEvent(Integer groupId, String secret, CallbackButtonEvent buttonEvent);

	public void photoNew(Integer groupId, String secret, Photo photo);

	public void photoCommentNew(Integer groupId, String secret, PhotoComment comment);

	public void photoCommentEdit(Integer groupId, String secret, PhotoComment comment);

	public void photoCommentRestore(Integer groupId, String secret, PhotoComment comment);

	public void photoCommentDelete(Integer groupId, String secret, PhotoCommentDelete commentDelete);

	public void audioNew(Integer groupId, String secret, Audio audio);

	public void videoNew(Integer groupId, String secret, Video video);

	public void videoCommentNew(Integer groupId, String secret, VideoComment videoComment);

	public void videoCommentEdit(Integer groupId, String secret, VideoComment videoComment);

	public void videoCommentRestore(Integer groupId, String secret, VideoComment videoComment);

	public void videoCommentDelete(Integer groupId, String secret, VideoCommentDelete videoCommentDelete);

	public void wallPostNew(Integer groupId, String secret, Wallpost post);

	public void wallRepost(Integer groupId, String secret, Wallpost post);

	public void wallReplyNew(Integer groupId, String secret, WallComment postComment);

	public void wallReplyEdit(Integer groupId, String secret, WallComment postComment);

	public void wallReplyRestore(Integer groupId, String secret, WallComment postComment);

	public void wallReplyDelete(Integer groupId, String secret, WallCommentDelete postCommentDelete);

	public void likeAdd(Integer groupId, String secret, LikeAddRemove likeAdd);

	public void likeRemove(Integer groupId, String secret, LikeAddRemove likeRemove);

	public void boardPostNew(Integer groupId, String secret, TopicComment topicComment);

	public void boardPostEdit(Integer groupId, String secret, TopicComment topicComment);

	public void boardPostRestore(Integer groupId, String secret, TopicComment topicComment);

	public void boardPostDelete(Integer groupId, String secret, BoardPostDelete boardDelete);

	public void marketCommentNew(Integer groupId, String secret, MarketComment comment);

	public void marketCommentEdit(Integer groupId, String secret, MarketComment comment);

	public void marketCommentRestore(Integer groupId, String secret, MarketComment comment);

	public void marketCommentDelete(Integer groupId, String secret, MarketCommentDelete commentDelete);

	public void groupLeave(Integer groupId, String secret, GroupLeave groupLeave);

	public void groupJoin(Integer groupId, String secret, GroupJoin groupJoin);

	public void groupChangeSettings(Integer groupId, String secret, GroupChangeSettings changeSettings);

	public void groupChangePhoto(Integer groupId, String secret, GroupChangePhoto changePhoto);

	public void groupOfficersEdit(Integer groupId, String secret, GroupOfficersEdit officersEdit);

	public void pollVoteNew(Integer groupId, String secret, PollVoteNew vote);

	public void userBlock(Integer groupId, String secret, UserBlock block);

	public void userUnblock(Integer groupId, String secret, UserUnblock unblock);

	public void confirmation(Integer groupId, String secret);

	public void jsonEvent(Integer groupId, String secret, JsonObject json);

	boolean parse(String json);

	boolean parse(JsonObject json);
}
