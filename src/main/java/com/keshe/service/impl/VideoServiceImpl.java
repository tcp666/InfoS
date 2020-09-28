package com.keshe.service.impl;

import com.keshe.entity.Message;
import com.keshe.entity.RetJsonData;
import com.keshe.entity.User;
import com.keshe.entity.Video;
import com.keshe.mapper.MessageMapper;
import com.keshe.mapper.UserMapper;
import com.keshe.mapper.VideoMapper;
import com.keshe.service.VideoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName VideoServiceImpl
 * @Description TODO
 * @Author Haining
 * @Date 2020/5/27 9:56
 * @Version 1.0
 */

@Service
public class VideoServiceImpl implements VideoService {
    @Resource
    private VideoMapper videoMapper;
    @Resource
    private MessageMapper messageMapper;
    @Resource
    private UserMapper userMapper;


    @Override
    public RetJsonData videoInfoPage(String type) {
//        System.out.println (type);
        List<Message> messages = messageMapper.messageByType(type);
//        System.out.println (messages);
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < messages.size(); i++){
            Map<String, Object> map = new HashMap<>();
            Video video = videoMapper.videoByMessageId(messages.get(i).getMessageId());
            if (video != null){  //如果当前message有存储的视频在进行封装
                User user = userMapper.userByUserId(messages.get(i).getUserId());
                user.setUserPassword(null);
                user.setUserRealname(null);
                user.setUserEmail(null);
                map.put("user", user);
                map.put("video", video);
                map.put("messageInfo", messages.get(i).getMessageInfo());
                list.add(map);
            }
        }
        if (list.size() != 0){
            return new RetJsonData(true, list, null);
        }else{
            return new RetJsonData(false,"查询失败");
        }
    }


    @Override
    public Video videoByMessageId(String messageId) {
        return null;
    }
}
