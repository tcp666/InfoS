package com.keshe.tools;

import com.keshe.entity.*;
import org.springframework.stereotype.Component;

import javax.xml.soap.SAAJResult;
import java.util.Date;


/**
 * @ClassName Pack
 * @Description TODO   打包类
 * @Author Haining
 * @Date 2020/5/27 15:41
 * @Version 1.0
 */
@Component
public class Pack {

    /**
     * 用户类进行打包
     * @param userName
     * @param password
     * @param email
     * @return
     */
    public User packUser(String userName, String password, String email){
        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(password);
        user.setUserEmail(email);
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        user.setUserMsgcount("0");
        user.setUserRealname(null);
        user.setUserCtime(new Date());
        //头像数据
        user.setUserImg("http://qb5b2c5x3.bkt.clouddn.com/defult_6884.JPG");
        user.setUserStatus("1");
        user.setUserPersonalized("");
        user.setUserFans("0");
        return user;
    }
    /**
     * 关注类进行打包
     * @param userId
     * @param userById
     * @param lable
     * @return
     */
    public Relation packRelation(String userId, String userById, String lable){
        Relation relation = new Relation();
        relation.setUserId(userId);
        relation.setUserById(userById);
        relation.setRelationType(lable);
        relation.setRelationTime(new Date());
        return relation;
    }


    /**
     * 收藏类进行打包
     * @param userId
     * @param messageId
     * @return
     */
    public Collections packCollection(String userId, String messageId){
        Collections collections = new Collections();
        collections.setCollectionTime(new Date());
        collections.setUserId(userId);
        collections.setMessageId(messageId);
        collections.setCollectionStatus("1");
        return collections;
    }


    /**
     * 点赞类进行打包
     * @param userId
     * @param messageId
     * @return
     */
    public Agree packAgree(String userId, String messageId){
        Agree agree = new Agree();
        agree.setMessageId(messageId);
        agree.setUserId(userId);
        return agree;
    }


    /**
     * 动态类进行打包
     * @param userId
     * @param messageInfo
     * @param lable
     * @return
     */
    public Message packMessage(String userId, String messageInfo, String lable){
        Message message = new Message();
        message.setUserId(userId);
        message.setMessageInfo(messageInfo);
        message.setMessageLable(lable);
        message.setMessageCtime(new Date());
        message.setMessageAgreeNum("0");
        message.setMessageCollectNum("0");
        message.setMessageCommentNum("0");
        message.setMessageReadNum("0");
        message.setMessageTranspondNum("0");
        message.setMessageState("1");
        return message;
    }


    /**
     * 图片类进行打包
     * @param messageId
     * @param url
     * @return
     */
    public Img packImg(String messageId, String url){
        Img img = new Img();
        img.setMessageId(messageId);
        img.setImgUrl(url);
        return img;
    }


    /**
     * 视频类进行打包
     * @param messageId
     * @param url
     * @return
     */
    public Video packVideo(String messageId, String url){
        Video video = new Video();
        video.setMessageId(messageId);
        video.setVideoUrl(url);
        return video;
    }


    /**
     * 打包主评论类
     * @param userId
     * @param messageId
     * @param commentInfo
     * @return
     */
    public Comment packComment(String userId, String messageId, String commentInfo){
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setMessageId(messageId);
        comment.setCommentTime(new Date());
        comment.setCommentInfo(commentInfo);
        return comment;
    }


    /**
     * 回复类进行打包
     * @param userId
     * @param userById
     * @param parentId
     * @param replyInfo
     * @return
     */
    public Reply packReply(String userId, String userById, String parentId, String replyInfo){
        Reply reply = new Reply();
        reply.setReplyUserId(userId);
        reply.setReplyByUserId(userById);
        reply.setCommentParent(parentId);
        reply.setReplyInfo(replyInfo);
        reply.setReplyTime(new Date());
        return reply;
    }

}
