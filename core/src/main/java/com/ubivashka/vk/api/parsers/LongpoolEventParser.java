package com.ubivashka.vk.api.parsers;

import com.google.gson.JsonObject;
import com.ubivashka.vk.api.parsers.objects.CallbackButtonEvent;
import com.ubivashka.vk.api.parsers.objects.MessageTyping;
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
	void messageNew(Integer groupId, String secret, Message message);

	void messageReply(Integer groupId, String secret, Message message);

	void messageEdit(Integer groupId, String secret, Message messageEdit);

	void messageAllow(Integer groupId, String secret, MessageAllow messageAllow);

	void messageDeny(Integer groupId, String secret, MessageDeny messageDeny);

	void messageTyping(Integer groupId, String secret, MessageTyping messageTyping);

	void messageEvent(Integer groupId, String secret, CallbackButtonEvent buttonEvent);

	void photoNew(Integer groupId, String secret, Photo photo);

	void photoCommentNew(Integer groupId, String secret, PhotoComment comment);

	void photoCommentEdit(Integer groupId, String secret, PhotoComment comment);

	void photoCommentRestore(Integer groupId, String secret, PhotoComment comment);

	void photoCommentDelete(Integer groupId, String secret, PhotoCommentDelete commentDelete);

	void audioNew(Integer groupId, String secret, Audio audio);

	void videoNew(Integer groupId, String secret, Video video);

	void videoCommentNew(Integer groupId, String secret, VideoComment videoComment);

	void videoCommentEdit(Integer groupId, String secret, VideoComment videoComment);

	void videoCommentRestore(Integer groupId, String secret, VideoComment videoComment);

	void videoCommentDelete(Integer groupId, String secret, VideoCommentDelete videoCommentDelete);

	void wallPostNew(Integer groupId, String secret, Wallpost post);

	void wallRepost(Integer groupId, String secret, Wallpost post);

	void wallReplyNew(Integer groupId, String secret, WallComment postComment);

	void wallReplyEdit(Integer groupId, String secret, WallComment postComment);

	void wallReplyRestore(Integer groupId, String secret, WallComment postComment);

	void wallReplyDelete(Integer groupId, String secret, WallCommentDelete postCommentDelete);

	void likeAdd(Integer groupId, String secret, LikeAddRemove likeAdd);

	void likeRemove(Integer groupId, String secret, LikeAddRemove likeRemove);

	void boardPostNew(Integer groupId, String secret, TopicComment topicComment);

	void boardPostEdit(Integer groupId, String secret, TopicComment topicComment);

	void boardPostRestore(Integer groupId, String secret, TopicComment topicComment);

	void boardPostDelete(Integer groupId, String secret, BoardPostDelete boardDelete);

	void marketCommentNew(Integer groupId, String secret, MarketComment comment);

	void marketCommentEdit(Integer groupId, String secret, MarketComment comment);

	void marketCommentRestore(Integer groupId, String secret, MarketComment comment);

	void marketCommentDelete(Integer groupId, String secret, MarketCommentDelete commentDelete);

	void groupLeave(Integer groupId, String secret, GroupLeave groupLeave);

	void groupJoin(Integer groupId, String secret, GroupJoin groupJoin);

	void groupChangeSettings(Integer groupId, String secret, GroupChangeSettings changeSettings);

	void groupChangePhoto(Integer groupId, String secret, GroupChangePhoto changePhoto);

	void groupOfficersEdit(Integer groupId, String secret, GroupOfficersEdit officersEdit);

	void pollVoteNew(Integer groupId, String secret, PollVoteNew vote);

	void userBlock(Integer groupId, String secret, UserBlock block);

	void userUnblock(Integer groupId, String secret, UserUnblock unblock);

	void confirmation(Integer groupId, String secret);

	void jsonEvent(Integer groupId, String secret, JsonObject json);

	boolean parse(String json);

	boolean parse(JsonObject json);
}
