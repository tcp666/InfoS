package com.keshe.service;

import com.keshe.entity.ChatRecord;

import java.util.List;

public interface ChatRecordService {

    Integer save(ChatRecord chatRecord);


    List<ChatRecord> findAll(String comUserId, String toUserId);
}
