package com.keshe.service.impl;

import com.keshe.entity.ChatRecord;
import com.keshe.mapper.ChatRecordMapper;
import com.keshe.service.ChatRecordService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChatRecordServiceimpl implements ChatRecordService {

    @Resource
   private ChatRecordMapper chatRecordMapper;

    @Override
    public Integer save(ChatRecord chatRecord) {
        return chatRecordMapper.save(chatRecord);
    }

    @Override
    public List<ChatRecord> findAll(String comUserId, String toUserId) {
        return chatRecordMapper.findAll(comUserId, toUserId);
    }
}
