package com.keshe.service.impl;

import com.keshe.entity.*;
import com.keshe.mapper.*;
import com.keshe.service.CollectionsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CollectionsServiceImpl
 * @Description TODO
 * @Author Haining
 * @Date 2020/5/27 9:43
 * @Version 1.0
 */

@Service
public class CollectionsServiceImpl implements CollectionsService {
    @Resource
    private CollectionsMapper collectionsMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private ImgMapper imgMapper;
    @Resource
    private VideoMapper videoMapper;
    @Resource
    private MessageMapper messageMapper;


    @Override
    public RetJsonData getAllCollection() {
        List<Collections> list = collectionsMapper.getAllCollections();
        if (list != null){
            return new RetJsonData(true, list, null);
        }else{
            return new RetJsonData(false, "查询失败");
        }
    }

    @Override
    public RetJsonData addCollection(Collections collections) {
        if (collectionsMapper.verifyCollection(collections.getUserId(), collections.getMessageId()) == null) {
            if (collectionsMapper.addCollection(collections) > 0 && messageMapper.addCollectnum(collections.getMessageId()) > 0) {
                return new RetJsonData(true, "收藏成功", null);
            } else {
                return new RetJsonData(false, "收藏失败");
            }
        }else{
            return new RetJsonData(false,"已经收藏了");
        }
    }

    @Override
    public RetJsonData delCollection(String userId, String messageId) {
        if (collectionsMapper.delCollection(userId, messageId) > 0 && messageMapper.redCollectnum(messageId) > 0){
            return new RetJsonData(true,"删除成功", null);
        }else{
            return new RetJsonData(false, "删除失败");
        }
    }

    @Override
    public RetJsonData collectionInfoPage(String userId) {
        List<Message> messages = collectionsMapper.collecMessageByUserId(userId);
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < messages.size(); i++){
            User user = userMapper.userByUserId(messages.get(i).getUserId());
            List<Img> listImgs = imgMapper.imgByMessageId(messages.get(i).getMessageId());
            Video video = videoMapper.videoByMessageId(messages.get(i).getMessageId());
            MessageAndAllInfo messageAndAllInfo = new MessageAndAllInfo();
            Map<String, Object> map = new HashMap<>();
            messageAndAllInfo.setMessage(messages.get(i));
            messageAndAllInfo.setImgs(listImgs);
            messageAndAllInfo.setVideo(video);
            user.setUserEmail(null);
            user.setUserPassword(null);
            user.setUserRealname(null);
            map.put("user", user);
            map.put("messageInfo", messageAndAllInfo);
            list.add(map);
        }
        if (list.size() != 0){
            System.out.println(list.size());
            return new RetJsonData(true, list, null);
        }else{
            return new RetJsonData(false, "查询失败");
        }
    }

}
