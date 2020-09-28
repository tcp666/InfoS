package com.keshe.chat;

import com.keshe.entity.ChatRecord;
import com.keshe.service.impl.ChatRecordServiceimpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * chatController
 * author:tcp
 */
@RequestMapping("/chat")
@RestController
public class Chat {
    @Resource
    private ChatRecordServiceimpl chatRecordServiceimpl;
    @RequestMapping("/getRecords")
    public List<ChatRecord> getRecords(@NotNull String comUserId, @NotNull String toUserId){
        System.out.println(comUserId+"::"+toUserId);
        return chatRecordServiceimpl.findAll(comUserId,toUserId);

    }
}
