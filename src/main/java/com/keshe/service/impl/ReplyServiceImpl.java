package com.keshe.service.impl;

import com.keshe.entity.Comment;
import com.keshe.entity.Reply;
import com.keshe.entity.RetJsonData;
import com.keshe.mapper.CommentMapper;
import com.keshe.mapper.ReplyMapper;
import com.keshe.mapper.UserMapper;
import com.keshe.service.ReplyService;
import com.keshe.tools.Pack;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ReplyServiceImpl
 * @Description TODO
 * @Author Haining
 * @Date 2020/5/27 9:51
 * @Version 1.0
 */

@Service
public class ReplyServiceImpl implements ReplyService {
    @Resource
    private ReplyMapper replyMapper;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private Pack packReply;


    @Override
    public RetJsonData saveReply(String userId, String parentId, String replyInfo) {
        if (parentId.contains("comment")){
//            System.out.println(parentId.length());
            String commentId = parentId.substring(7, parentId.length());
//            System.out.println(commentId);
            Comment comment = commentMapper.commentByCommentId(commentId);
            if (comment != null){
                String userById = comment.getUserId();
                Reply reply = packReply.packReply(userId, userById, parentId, replyInfo);
                if (replyMapper.saveReply(reply) > 0){
                    Reply reply2 = replyMapper.getReply(reply);
                    reply2.setReplyId("reply"+reply2.getReplyId());
                    if (reply2 != null){
                        return new RetJsonData(true, reply2, null);
                    }
                    return new RetJsonData(false, "查询失败");
                }
                return new RetJsonData(false, "回复失败");
            }
            return new RetJsonData(false, "评论已被删除");
        }else{

//            System.out.println("parentId:"+parentId);
            String replyId = parentId.substring(5, parentId.length());
//            System.out.println(replyId);
            Reply reply = replyMapper.replyByreplyId(replyId);
            if (reply != null){
                String userById = reply.getReplyUserId();
                Reply reply1 = packReply.packReply(userId, userById, parentId, replyInfo);
                if (replyMapper.saveReply(reply1) > 0){
                    Reply reply2 = replyMapper.getReply(reply1);
                    reply2.setReplyId("reply"+reply2.getReplyId());
                    if (reply2 != null){
                        return new RetJsonData(true, reply2, null);
                    }
                    return new RetJsonData(false, "查询失败");
                }
                return new RetJsonData(false, "回复失败");
            }
            return new RetJsonData(false, "评论已被删除");
        }
    }

    @Override
    public RetJsonData getReplysById(String parentId) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<Reply> replies = replyMapper.getReplyById(parentId);
        for (int i = 0; i < replies.size(); i++){
            String userName = userMapper.userByUserId(replies.get(i).getReplyUserId()).getUserName();
            String byUserName = userMapper.userByUserId(replies.get(i).getReplyByUserId()).getUserName();
            replies.get(i).setReplyId("reply"+replies.get(i).getReplyId());
            Map<String, Object> map = new HashMap<>();
            map.put("userName", userName);
            map.put("byUserName", byUserName);
            map.put("reply", replies.get(i));
            list.add(map);
        }
        if (list.size() > 0){
            return new RetJsonData(true, list, null);
        }
        return new RetJsonData(true, "没有数据", null);
    }


}
